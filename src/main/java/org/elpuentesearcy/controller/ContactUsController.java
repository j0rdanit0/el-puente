package org.elpuentesearcy.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
public class ContactUsController extends BaseController
{
    private final Properties properties;

    public static final String URL_BASE_EN = "/contact-us";
    public static final String URL_BASE_ES = "/contactenos";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String contactUs( Model model )
    {
        model.addAttribute( "addressUrlLine", URLEncoder.encode( String.join( " ", properties.getPhysicalAddress() ), StandardCharsets.UTF_8 ) );
        return "contactUs";
    }

    @GetMapping( value = "/contact" )
    public void contact( HttpServletResponse response ) throws IOException
    {
        response.sendRedirect( URL_BASE_EN );
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
