package org.elpuentesearcy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@Component
public class Interceptor extends HandlerInterceptorAdapter
{
    private static final Logger logger = LoggerFactory.getLogger( Interceptor.class );

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
    {
        String requestUrl = getRequestUrl( request );

        if ( Stream.of( ".css", ".js", ".json", ".xml", ".txt", ".png", ".jpg" ).anyMatch( requestUrl::contains ) )
        {
            logger.trace( requestUrl );
        }
        else
        {
            logger.info( requestUrl );
        }

        return true;
    }

    private static String getRequestUrl( HttpServletRequest request )
    {
        return request.getRequestURL().append( request.getQueryString() == null ? "" : "?" + request.getQueryString() ).toString();
    }
}
