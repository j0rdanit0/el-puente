package org.elpuentesearcy.configuration;

import org.elpuentesearcy.ElPuenteBoot;
import org.elpuentesearcy.controller.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
public class ElPuenteConfiguration implements WebMvcConfigurer
{
    @Autowired
    private Interceptor interceptor;

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

    @Bean( name = "localeResolver" )
    public LocaleResolver getLocaleResolver()
    {
        return new UrlLocaleResolver();
    }

    @Bean( name = "messageSource" )
    public MessageSource getMessageSource()
    {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename( "classpath:messages" );
        messageResource.setDefaultEncoding( "UTF-8" );
        return messageResource;
    }

    @Bean
    public CacheManager cacheManager()
    {
        return new ConcurrentMapCacheManager( ElPuenteCaches.SITEMAP, ElPuenteCaches.ROBOTS );
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
}
