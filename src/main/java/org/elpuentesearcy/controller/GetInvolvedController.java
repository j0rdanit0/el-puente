package org.elpuentesearcy.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.ElPuenteBoot;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class GetInvolvedController extends BaseController
{
    public static final String URL_BASE_EN = "/get-involved";
    public static final String URL_BASE_ES = "/involucrarse";

    private final Properties properties;

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String getInvolved( Model model )
    {
        model.addAttribute( "mailingAddress", String.join( " ", properties.getMailingAddress() ) );

        String carouselFolder = "carousel/";
        String[] carouselImageNames = new File( ElPuenteBoot.IMAGE_DIRECTORY + carouselFolder ).list();
        if ( carouselImageNames != null && carouselImageNames.length > 0 )
        {
            Set<String> carouselImagePaths = Arrays
              .stream( carouselImageNames )
              .map( name -> ElPuenteBoot.IMAGE_FOLDER + carouselFolder + name )
              .collect( Collectors.toSet() );

            model.addAttribute( "carouselImagePaths", new TreeSet<>( carouselImagePaths ) );
        }

        return "getInvolved";
    }

    @GetMapping( value = "/involvement" )
    public void involvement( HttpServletResponse response ) throws IOException
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
