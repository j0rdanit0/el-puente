package org.elpuentesearcy.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.configuration.Properties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
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
    private final ResourceLoader resourceLoader;

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String getInvolved( Model model )
    {
        model.addAttribute( "mailingAddress", String.join( " ", properties.getMailingAddress() ) );

        String carouselFolder = "carousel/";
        ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver( resourceLoader );

        try
        {
            Resource[] imageResources = resourcePatternResolver.getResources( "classpath:static/images/" + carouselFolder + "*" );

            // Map resources to HTTP-accessible paths and collect into a sorted set
            Set<String> carouselImagePaths = Arrays
              .stream( imageResources )
              .map( resource -> {
                  String filename = resource.getFilename();
                  return filename != null ? "/images/" + carouselFolder + filename : null;
              } )
              .filter( Objects::nonNull )
              .collect( Collectors.toCollection( TreeSet::new ) );

            model.addAttribute( "carouselImagePaths", carouselImagePaths );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( "Failed to load carousel images from classpath: static/images/" + carouselFolder, e );
        }

        return "getInvolved";
    }

    @Deprecated
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
