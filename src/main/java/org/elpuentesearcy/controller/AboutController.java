package org.elpuentesearcy.controller;

import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AboutController
{
    private final Properties properties;

    public static final String URL_BASE = "/about";

    @GetMapping( URL_BASE )
    public String about( Model model )
    {
        model.addAttribute( "boardOfDirectors", properties.getBoard() );
        model.addAttribute( "staff", properties.getStaff() );
        return "about";
    }
}
