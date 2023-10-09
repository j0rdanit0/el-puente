package org.elpuentesearcy.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AboutUsController extends BaseController
{
    private final Properties properties;

    public static final String URL_BASE_EN = "/about-us";
    public static final String URL_BASE_ES = "/sobre-nosotros";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String aboutUs( Model model )
    {
        model.addAttribute( "boardOfDirectors", properties.getBoard() );
        model.addAttribute( "staff", properties.getStaff() );
        return "aboutUs";
    }

    @Deprecated
    @GetMapping( value = "/about" )
    public void about( HttpServletResponse response ) throws IOException
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
