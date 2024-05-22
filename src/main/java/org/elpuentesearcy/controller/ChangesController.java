package org.elpuentesearcy.controller;

import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elpuentesearcy.domain.TrelloCard;
import org.elpuentesearcy.service.TrelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Profile( "!production, trello" )
public class ChangesController extends BaseController
{
    private final TrelloService trelloService;

    @Value( "${trello.approvalListId}" )
    private String trelloApprovalListId;
    @Value( "${trello.approvedListId}" )
    private String trelloApprovedListId;

    public static final String URL_BASE_EN = "/changes";
    public static final String URL_BASE_ES = "/cambios";
    public static final String GO_LIVE = URL_BASE_EN + "/goLive";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String home( Model model ) throws IOException
    {
        List<TrelloCard> trelloCards = trelloService.get( new TypeToken<List<TrelloCard>>(){}.getType(), "lists/" + trelloApprovalListId + "/cards/open", "fields", "url,name" );

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

            //todo - call AWS to approve prod deployment
        }
        catch ( Exception exception )
        {
            log.error( "Unable to Go Live", exception );
        }
    }

    @Override
    public String getEnglishUrlBase()
    {
        return URL_BASE_EN;
    }

    @Override
    public String getSpanishUrlBase()
    {
        return URL_BASE_ES;
    }
}
