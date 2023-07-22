package org.elpuentesearcy.controller;

import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.ElPuenteBoot;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

        return "involvement";
    }
}
