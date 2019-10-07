package org.elpuentesearcy.controller;

import org.elpuentesearcy.configuration.Properties;
import org.elpuentesearcy.domain.ChangeApproval;
import org.elpuentesearcy.domain.ChangeApprovalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@ConditionalOnProperty( "org.elpuentesearcy.isTestMode" )
public class ChangesController
{
    @Autowired
    private Properties properties;

    @Value( "${trello.api.baseUrl}" )
    private String trelloBaseUrl;
    @Value( "${trello.api.key}" )
    private String trelloKey;
    @Value( "${trello.api.token}" )
    private String trelloToken;
    @Value( "${trello.approvedListId}" )
    private String trelloApprovedListId;

    private static final List<ChangeApproval> CHANGE_APPROVALS = new ArrayList<>();

    public static final String URL_BASE = "/changes";
    public static final String APPROVE_CARD = URL_BASE + "/cards/{cardId}";

    //todo - moving cards via API:
    // "PUT", "https://api.trello.com/1/cards/5d93720d4a06b9017e866cd6?idList=5d9373776cd8888e4d4f5977&key=01a11e9b42955480d5ead65645b15bf1&token=d1082685399a92b420d25952835d4ec3cf81918cc031cd60baf670a658b0bb3a"

    @PostConstruct
    public void postConstruct()
    {
        for ( String siteChange : properties.getSiteChanges() )
        {
            CHANGE_APPROVALS.add( new ChangeApproval( siteChange ) );
        }
    }

    @GetMapping( URL_BASE )
    public String home()
    {
        String url = "https://api.trello.com/1/boards/{boardId}/cards";
        return "changes";
    }

    @PutMapping( APPROVE_CARD )
    public void approveCard( @PathVariable String cardId ) throws IOException
    {
        URL url = new URL(trelloBaseUrl + "cards/" + cardId +
                          "?idList=" + trelloApprovedListId +
                          "&key=" + trelloKey +
                          "&token=" + trelloToken);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        int responseCode = con.getResponseCode();
    }

    @GetMapping( "/changeApprovals" )
    @ResponseBody
    public List<ChangeApproval> getChangeApprovals()
    {
        return CHANGE_APPROVALS;
    }

    @MessageMapping( "/changeRequest" )
    @SendTo( "/topic/changeApprovals" )
    public List<ChangeApproval> changeApprovals( ChangeApprovalRequest request )
    {
        if ( request.getChangeIndex() >= 0 && !request.getAuthor().isEmpty() )
        {
            ChangeApproval changeApproval = CHANGE_APPROVALS.get( request.getChangeIndex() );
            if ( request.isApproved() )
            {
                if ( request.isUndo() )
                {
                    changeApproval.getApprovals().removeIf( approval -> approval.equals( request.getAuthor() ) );
                }
                else
                {
                    changeApproval.getApprovals().add( request.getAuthor() );
                }
            }
            else
            {
                if ( request.isUndo() )
                {
                    int index = changeApproval.getDenials().indexOf( request.getAuthor() );
                    changeApproval.getDenials().remove( index );
                    changeApproval.getDenialReasons().remove( index );
                }
                else
                {
                    changeApproval.getDenials().add( request.getAuthor() );
                    changeApproval.getDenialReasons().add( request.getDenialReason() );
                }
            }
        }

        return CHANGE_APPROVALS;
    }
}
