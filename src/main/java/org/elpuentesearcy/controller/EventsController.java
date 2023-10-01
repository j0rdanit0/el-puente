package org.elpuentesearcy.controller;

import org.elpuentesearcy.ElPuenteBoot;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventsController extends BaseController
{
    public static final String URL_BASE_EN = "/events";
    public static final String URL_BASE_ES = "/eventos";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String events( Model model )
    {
        String eventsFolder = "events/";

        String hispanicHeritageMonthDinnerImageFolder = eventsFolder + "hispanic-heritage-month-dinner/";
        String[] hispanicHeritageMonthDinnerYears = new File( ElPuenteBoot.IMAGE_DIRECTORY + hispanicHeritageMonthDinnerImageFolder ).list();
        if ( hispanicHeritageMonthDinnerYears != null && hispanicHeritageMonthDinnerYears.length > 0 )
        {
            List<ElPuenteEvent> hispanicHeritageMonthDinners = new ArrayList<>();
            for ( String year : hispanicHeritageMonthDinnerYears )
            {
                String[] hispanicHeritageMonthDinnerImageNames = new File( ElPuenteBoot.IMAGE_DIRECTORY + hispanicHeritageMonthDinnerImageFolder + year + "/" ).list();
                if ( hispanicHeritageMonthDinnerImageNames != null && hispanicHeritageMonthDinnerImageNames.length > 0 )
                {
                    List<String> imagePaths = Arrays
                      .stream( hispanicHeritageMonthDinnerImageNames )
                      .map( name -> ElPuenteBoot.IMAGE_FOLDER + hispanicHeritageMonthDinnerImageFolder + year + "/" + name )
                      .collect( Collectors.toList() );

                    hispanicHeritageMonthDinners.add( new ElPuenteEvent( year, imagePaths ) );
                }
            }

            if ( !hispanicHeritageMonthDinners.isEmpty() )
            {
                model.addAttribute( "hispanicHeritageMonthDinners", hispanicHeritageMonthDinners );
            }
        }


        String beatsAndEatsCincoDeMayoImageFolder = eventsFolder + "beats-and-eats-cinco-de-mayo/";
        String[] beatsAndEatsCincoDeMayoYears = new File( ElPuenteBoot.IMAGE_DIRECTORY + beatsAndEatsCincoDeMayoImageFolder ).list();
        if ( beatsAndEatsCincoDeMayoYears != null && beatsAndEatsCincoDeMayoYears.length > 0 )
        {
            List<ElPuenteEvent> beatsAndEatsCincoDeMayos = new ArrayList<>();
            for ( String year : beatsAndEatsCincoDeMayoYears )
            {
                String[] beatsAndEatsCincoDeMayoImageNames = new File( ElPuenteBoot.IMAGE_DIRECTORY + beatsAndEatsCincoDeMayoImageFolder + year + "/" ).list();
                if ( beatsAndEatsCincoDeMayoImageNames != null && beatsAndEatsCincoDeMayoImageNames.length > 0 )
                {
                    List<String> imagePaths = Arrays
                      .stream( beatsAndEatsCincoDeMayoImageNames )
                      .map( name -> ElPuenteBoot.IMAGE_FOLDER + beatsAndEatsCincoDeMayoImageFolder + year + "/" + name )
                      .collect( Collectors.toList() );

                    beatsAndEatsCincoDeMayos.add( new ElPuenteEvent( year, imagePaths ) );
                }
            }

            if ( !beatsAndEatsCincoDeMayos.isEmpty() )
            {
                model.addAttribute( "beatsAndEatsCincoDeMayos", beatsAndEatsCincoDeMayos );
            }
        }

        return "events";
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

    private record ElPuenteEvent(String year, List<String> imagePaths)
    {
    }
}
