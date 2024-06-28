package org.elpuentesearcy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elpuentesearcy.service.DeploymentApprovalService;
import org.elpuentesearcy.service.TrelloService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@Profile( "!production & trello" )
public class ChangesController extends BaseController
{
    private final TrelloService trelloService;
    private final DeploymentApprovalService deploymentApprovalService;

    public static final String URL_BASE_EN = "/changes";
    public static final String URL_BASE_ES = "/cambios";
    public static final String GO_LIVE = URL_BASE_EN + "/goLive";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String home( Model model )
    {
        model.addAttribute( "trelloCards", trelloService.getCardsThatNeedApproval() );

        return "changes";
    }

    @PostMapping( GO_LIVE )
    @ResponseBody
    public void goLive()
    {
        try
        {
            deploymentApprovalService.approve();
            trelloService.approveAllCards();
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
