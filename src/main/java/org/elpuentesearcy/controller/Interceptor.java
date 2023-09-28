package org.elpuentesearcy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@Slf4j
@Component
public class Interceptor implements AsyncHandlerInterceptor
{
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
    {
        String requestUrl = getRequestUrl( request );

        if ( Stream.of( ".css", ".js", ".json", ".xml", ".txt", ".png", ".jpg" ).anyMatch( requestUrl::contains ) )
        {
            log.trace( requestUrl );
        }
        else
        {
            log.info( requestUrl );
        }

        return true;
    }

    private String getRequestUrl( HttpServletRequest request )
    {
        return request.getRequestURL().append( request.getQueryString() == null ? "" : "?" + request.getQueryString() ).toString();
    }
}
