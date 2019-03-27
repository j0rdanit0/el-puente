package org.elpuentesearcy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties( "org.elpuentesearcy" )
public class Properties
{
    private String version;
    private boolean isTestMode;
    private Google google;

    private static Properties instance;

    @PostConstruct
    @Order( Ordered.HIGHEST_PRECEDENCE )
    public void postConstruct()
    {
        instance = this;
    }

    public static Properties get()
    {
        return instance;
    }

    public static class Google
    {
        private Analytics analytics;
        private Maps maps;

        public static class Analytics
        {
            private String trackingId;

            public String getTrackingId()
            {
                return trackingId;
            }

            public void setTrackingId( String trackingId )
            {
                this.trackingId = trackingId;
            }
        }

        public static class Maps
        {
            private String apiKey;

            public String getApiKey()
            {
                return apiKey;
            }

            public void setApiKey( String apiKey )
            {
                this.apiKey = apiKey;
            }
        }

        public Analytics getAnalytics()
        {
            return analytics;
        }

        public void setAnalytics( Analytics analytics )
        {
            this.analytics = analytics;
        }

        public Maps getMaps()
        {
            return maps;
        }

        public void setMaps( Maps maps )
        {
            this.maps = maps;
        }
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public boolean isTestMode()
    {
        return isTestMode;
    }

    public void setIsTestMode( boolean testMode )
    {
        isTestMode = testMode;
    }

    public Google getGoogle()
    {
        return google;
    }

    public void setGoogle( Google google )
    {
        this.google = google;
    }
}
