package org.elpuentesearcy.controller;

import org.elpuentesearcy.configuration.Properties;
import org.elpuentesearcy.domain.ChangeApproval;
import org.elpuentesearcy.domain.ChangeApprovalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@ConditionalOnProperty( "org.elpuentesearcy.isTestMode" )
public class ChangesController
{
    @Autowired
    private Properties properties;

    private static final List<ChangeApproval> CHANGE_APPROVALS = new ArrayList<>();

    public static final String URL_BASE = "/changes";

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
        return "changes";
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
