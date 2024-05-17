package org.elpuentesearcy.configuration;

import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.ElPuenteBoot;
import org.elpuentesearcy.controller.Interceptor;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer
{
    private final Interceptor interceptor;

    @Override
    public void addInterceptors( InterceptorRegistry registry )
    {
        registry.addInterceptor( interceptor );
    }

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry )
    {
        registry.addResourceHandler( ElPuenteBoot.IMAGE_FOLDER + "**" ).addResourceLocations( "file:" + ElPuenteBoot.IMAGE_DIRECTORY );
        registry.addResourceHandler( ElPuenteBoot.DOWNLOADS_FOLDER + "**" ).addResourceLocations( "file:" + ElPuenteBoot.DOWNLOADS_DIRECTORY );
        registry.addResourceHandler( ElPuenteBoot.WELL_KNOWN_FOLDER + "**" ).addResourceLocations( "file:" + ElPuenteBoot.WELL_KNOWN_DIRECTORY );
    }

    @Override
    public void addViewControllers( ViewControllerRegistry registry )
    {
        registry.addViewController( "/notFound" ).setViewName( "forward:/" );
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer()
    {
        return container -> container.addErrorPages( new ErrorPage( HttpStatus.NOT_FOUND, "/notFound" ) );
    }

    @Bean( name = "localeResolver" )
    public LocaleResolver getLocaleResolver()
    {
        return new UrlLocaleResolver();
    }
}
