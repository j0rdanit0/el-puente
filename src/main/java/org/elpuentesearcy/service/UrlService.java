package org.elpuentesearcy.service;

import org.elpuentesearcy.configuration.UrlLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlService
{
    @Value( "${org.elpuentesearcy.domain}" )
    private String domain;

    public String getBaseUrl()
    {
        return getBaseUrl( null );
    }

    public String getBaseUrl( UrlLocaleResolver.ElPuenteLanguage subdomain )
    {
        return "https://%s%s".formatted( (subdomain == null ? "" : subdomain.getSubdomain() + "."), domain );
    }
}
