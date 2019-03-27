package org.elpuentesearcy.configuration;

import org.elpuentesearcy.ElPuenteBoot;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
public class ElPuenteConfiguration implements WebMvcConfigurer
{
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
    public void addResourceHandlers( ResourceHandlerRegistry registry )
    {
        registry.addResourceHandler( "/images/**" ).addResourceLocations( "file:" + ElPuenteBoot.IMAGE_DIRECTORY );
    }
}
