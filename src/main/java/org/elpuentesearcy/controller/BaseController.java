package org.elpuentesearcy.controller;

import java.util.Map;

public abstract class BaseController
{
    private final Map<String, String> urlMap = Map.of( "en", getEnglishUrlBase(), "es", getSpanishUrlBase() );

    public abstract String getEnglishUrlBase();
    public abstract String getSpanishUrlBase();

    public String getUrlBase( String locale )
    {
        return urlMap.getOrDefault( locale, getEnglishUrlBase() );
    }
}
