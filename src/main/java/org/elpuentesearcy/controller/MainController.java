package org.elpuentesearcy.controller;

import org.elpuentesearcy.ElPuenteBoot;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class MainController
{
    @GetMapping( "" )
    public String home( Model model )
    {
//        final String carouselFolder = "carousel/";
//        model.addAttribute( "carouselImagePath", ElPuenteBoot.IMAGE_FOLDER + carouselFolder );
//
//        String[] carouselImageNames = new File( ElPuenteBoot.IMAGE_DIRECTORY + carouselFolder ).list();
//        model.addAttribute( "carouselImageNames", carouselImageNames );
//        model.addAttribute( "carouselImageRandomStartIndex", carouselImageNames == null ? 0 : ThreadLocalRandom.current().nextInt( carouselImageNames.length ) );

        return "main";
    }
}
