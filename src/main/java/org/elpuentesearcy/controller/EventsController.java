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
import java.util.stream.Stream;

@Controller
public class EventsController extends BaseController
{
    public static final String URL_BASE_EN = "/events";
    public static final String URL_BASE_ES = "/eventos";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String events( Model model )
    {
        String eventsFolder = "events/";

        List<ElPuenteEvent> fifthAnniversaryDinners = fetchEvents( eventsFolder + "fifth-anniversary-dinner" );
        if ( !fifthAnniversaryDinners.isEmpty() )
        {
            model.addAttribute( "fifthAnniversaryDinners", fifthAnniversaryDinners );
        }

        List<ElPuenteEvent> beatsAndEatsCincoDeMayos = fetchEvents( eventsFolder + "beats-and-eats-cinco-de-mayo" );
        if ( !beatsAndEatsCincoDeMayos.isEmpty() )
        {
            model.addAttribute( "beatsAndEatsCincoDeMayos", beatsAndEatsCincoDeMayos );
        }

        List<ElPuenteEvent> hispanicHeritageMonthDinners = fetchEvents( eventsFolder + "hispanic-heritage-month-dinner" );
        if ( !hispanicHeritageMonthDinners.isEmpty() )
        {
            model.addAttribute( "hispanicHeritageMonthDinners", hispanicHeritageMonthDinners );
        }

        return "events";
    }

    private List<ElPuenteEvent> fetchEvents( String imageFolder )
    {
        List<ElPuenteEvent> events = new ArrayList<>();

        String[] yearsArray = new File( ElPuenteBoot.IMAGE_DIRECTORY + imageFolder ).list();
        if ( yearsArray != null && yearsArray.length > 0 )
        {
            List<String> years = Stream
              .of( yearsArray )
              .sorted()
              .toList()
              .reversed();

            for ( String year : years )
            {
                String[] hispanicHeritageMonthDinnerImageNames = new File( ElPuenteBoot.IMAGE_DIRECTORY + imageFolder + "/" + year + "/" ).list();
                if ( hispanicHeritageMonthDinnerImageNames != null && hispanicHeritageMonthDinnerImageNames.length > 0 )
                {
                    List<String> imagePaths = Arrays
                      .stream( hispanicHeritageMonthDinnerImageNames )
                      .map( name -> ElPuenteBoot.IMAGE_FOLDER + imageFolder + "/" + year + "/" + name )
                      .collect( Collectors.toList() );

                    events.add( new ElPuenteEvent( year, imagePaths ) );
                }
            }
        }

        return events;
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
