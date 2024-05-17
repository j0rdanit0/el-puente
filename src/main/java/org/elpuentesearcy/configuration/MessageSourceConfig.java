package org.elpuentesearcy.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig
{
    @Bean( name = "messageSource" )
    public MessageSource getMessageSource()
    {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename( "classpath:messages" );
        messageResource.setDefaultEncoding( "UTF-8" );
        return messageResource;
    }
}
