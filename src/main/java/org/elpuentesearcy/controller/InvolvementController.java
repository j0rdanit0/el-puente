package org.elpuentesearcy.controller;

import org.elpuentesearcy.configuration.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvolvementController
{
    public static final String URL_BASE = "/involvement";

    @Autowired
    private Properties properties;

    @GetMapping( URL_BASE )
    public String involvement( Model model )
    {
        model.addAttribute( "mailingAddress", String.join( " ", properties.getMailingAddress() ) );
        return "involvement";
    }
}
