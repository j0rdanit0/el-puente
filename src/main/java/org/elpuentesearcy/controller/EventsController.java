package org.elpuentesearcy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EventsController extends BaseController
{
    public static final String URL_BASE_EN = "/events";
    public static final String URL_BASE_ES = "/eventos";

    private final ResourceLoader resourceLoader;

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

        ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver( resourceLoader );

        try
        {
            Resource[] yearResources = resourcePatternResolver.getResources( "classpath:static/images/" + imageFolder + "/**/*" );

            SequencedSet<String> years = Arrays
              .stream( yearResources )
              .map( resource -> {
                  try
                  {
                      String[] parts = resource.getURL().getPath().split( "/" );
                      return parts[parts.length - 2]; // year
                  }
                  catch ( IOException e )
                  {
                      throw new RuntimeException( e );
                  }
              } )
              .filter( year -> year.matches( "\\d{4}" ) )
              .sorted()
              .collect( Collectors.toCollection( LinkedHashSet::new ) )
              .reversed();

            for ( String year : years )
            {
                List<String> imagePaths = Arrays
                  .stream( yearResources )
                  .filter( filename -> {
                      try
                      {
                          return filename.getURL().getPath().matches( ".+/" + year + "/.+" );
                      }
                      catch ( IOException e )
                      {
                          throw new RuntimeException( e );
                      }
                  } )
                  .map( resource -> "/images/" + imageFolder + "/" + year + "/" + resource.getFilename() )
                  .toList();

                events.add( new ElPuenteEvent( year, imagePaths ) );
            }
        }
        catch ( IOException e )
        {
            throw new RuntimeException( "Failed to load images from classpath: static/images/" + imageFolder, e );
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
