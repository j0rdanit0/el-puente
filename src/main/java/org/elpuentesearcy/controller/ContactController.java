package org.elpuentesearcy.controller;

import org.elpuentesearcy.configuration.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class ContactController
{
    @Autowired
    private Properties properties;

    public static final String URL_BASE = "/contact";

    @GetMapping( URL_BASE )
    public String contact( Model model ) throws UnsupportedEncodingException
    {
        model.addAttribute( "addressUrlLine", URLEncoder.encode( String.join( " ", properties.getPhysicalAddress() ), "UTF-8" ) );
        return "contact";
    }
}
