package org.elpuentesearcy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elpuentesearcy.domain.TrelloCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Profile( "trello" )
@RequiredArgsConstructor
public class TrelloService
{
    private final RestTemplate restTemplate;
    private final TaskExecutor taskExecutor;

    @Value( "${trello.api.baseUrl}" )
    private String trelloBaseUrl;
    @Value( "${trello.api.key}" )
    private String trelloKey;
    @Value( "${trello.api.token}" )
    private String trelloToken;
    @Value( "${trello.approvalListId}" )
    private String trelloApprovalListId;
    @Value( "${trello.approvedListId}" )
    private String trelloApprovedListId;

    public List<TrelloCard> getCardsThatNeedApproval()
    {
        List<TrelloCard> result = Collections.emptyList();

        try
        {
            TrelloCard[] cards = restTemplate.getForObject( createUri( "/lists/" + trelloApprovalListId + "/cards/open", "fields", "url,name" ), TrelloCard[].class );
            if ( cards != null && cards.length > 0 )
            {
                result = List.of( cards );
            }
        }
        catch ( Exception exception )
        {
            log.error( "Unable to get trello cards", exception );
        }

        return result;
    }

    public void approveAllCards()
    {
        for ( TrelloCard trelloCard : getCardsThatNeedApproval() )
        {
            taskExecutor.execute( () -> {
                try
                {
                    restTemplate.put( createUri( "/cards/" + trelloCard.id, "idList", trelloApprovedListId ), null );
                }
                catch ( Exception exception )
                {
                    log.error( "Unable to approve card", exception );
                }
            } );
        }
    }

    private URI createUri( String path, String... keyValues )
    {
        UriComponentsBuilder builder = UriComponentsBuilder
          .fromHttpUrl( trelloBaseUrl )
          .path( path )
          .queryParam( "key", trelloKey )
          .queryParam( "token", trelloToken );

        for ( int i = 0; i < keyValues.length; i += 2 )
        {
            builder.queryParam( keyValues[i], keyValues[i+1] );
        }

        return builder.build().toUri();
    }
}
