package org.elpuentesearcy.controller;

import com.google.gson.reflect.TypeToken;
import org.elpuentesearcy.domain.TrelloCard;
import org.elpuentesearcy.service.TrelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@ConditionalOnProperty( "org.elpuentesearcy.testMode" )
public class ChangesController
{
    private static final Logger logger = LoggerFactory.getLogger( ChangesController.class );

    @Autowired
    private TrelloService trelloService;

    @Value( "${trello.approvalListId}" )
    private String trelloApprovalListId;
    @Value( "${trello.approvedListId}" )
    private String trelloApprovedListId;

    public static final String URL_BASE = "/changes";
    public static final String GO_LIVE = URL_BASE + "/goLive";

    @GetMapping( URL_BASE )
    public String home( Model model ) throws IOException
    {
        List<TrelloCard> trelloCards = trelloService.get( new TypeToken<List<TrelloCard>>(){}.getType(), "lists/" + trelloApprovedListId + "/cards/open", "fields", "url,name" );
        trelloCards.addAll( trelloService.get( new TypeToken<List<TrelloCard>>(){}.getType(), "lists/" + trelloApprovalListId + "/cards/open", "fields", "url,name" ) );

        model.addAttribute( "trelloCards", trelloCards );

        return "changes";
    }

    @PostMapping( GO_LIVE )
    @ResponseBody
    public void goLive()
    {
        try
        {
            List<TrelloCard> trelloCards = trelloService.get( new TypeToken<List<TrelloCard>>(){}.getType(), "lists/" + trelloApprovalListId + "/cards/open", "fields", "url,name" );
            for ( TrelloCard trelloCard : trelloCards )
            {
                trelloService.makeTrelloRequest( RequestMethod.PUT, "cards/" + trelloCard.id, "{}", "idList", trelloApprovedListId );
            }

            ProcessBuilder processBuilder = new ProcessBuilder( "/bin/bash", "/opt/elpuente/deploy/copyBetaToLive.sh" );
            processBuilder.start();
        }
        catch ( Exception exception )
        {
            logger.error( "Unable to Go Live", exception );
        }
    }
}
