package org.elpuentesearcy.controller;

import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class InvolvementController
{
    public static final String URL_BASE = "/involvement";

    private final Properties properties;

    @GetMapping( URL_BASE )
    public String involvement( Model model )
    {
        model.addAttribute( "mailingAddress", String.join( " ", properties.getMailingAddress() ) );
        return "involvement";
    }
}
