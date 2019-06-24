package org.elpuentesearcy.service;

import org.elpuentesearcy.configuration.UrlLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlService
{
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
        return "http" + (isUsesSSL() ? "s" : "") + "://" + (subdomain == null ? "" : subdomain.getSubdomain() + ".") + getDomain() + (isUsesSSL() ? "" : ":" + getPort());
    }

    public int getPort()
    {
        return port;
    }

    public void setPort( int port )
    {
        this.port = port;
    }

    public boolean isUsesSSL()
    {
        return getPort() == 443;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain( String domain )
    {
        this.domain = domain;
    }
}
