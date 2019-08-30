package org.elpuentesearcy.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@ConditionalOnProperty( "org.elpuentesearcy.isTestMode" )
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{
    @Override
    public void configureMessageBroker( MessageBrokerRegistry config )
    {
        config.enableSimpleBroker( "/topic" );
        config.setApplicationDestinationPrefixes( "/app" );
    }

    @Override
    public void registerStompEndpoints( StompEndpointRegistry registry )
    {
        registry.addEndpoint( "/elpuente-websocket" ).withSockJS();
    }
}
