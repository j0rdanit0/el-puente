package org.elpuentesearcy.service;

import org.elpuentesearcy.configuration.UrlLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlService
{
    @Value( "${org.elpuentesearcy.testMode}" )
    private boolean testMode;
    @Value( "${server.port}" )
    private int port;
    @Value( "${org.elpuentesearcy.domain}" )
    private String domain;

    public String getBaseUrl()
    {
        return getBaseUrl( null );
    }

    public String getBaseUrl( UrlLocaleResolver.ElPuenteLanguage subdomain )
    {
        return "http" + (testMode ? "" : "s") + "://" +                   //https://
               (subdomain == null ? "" : subdomain.getSubdomain() + ".") +  //es.
               domain +                                                     //elpuentesearcy.org
               (testMode ? ":" + port : "");                              //:8080
    }
}
