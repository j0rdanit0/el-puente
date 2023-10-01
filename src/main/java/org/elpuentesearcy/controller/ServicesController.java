package org.elpuentesearcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController extends BaseController
{
    public static final String URL_BASE_EN = "/services";
    public static final String URL_BASE_ES = "/servicios";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String services()
    {
        return "services";
    }

    @Override
    public String getEnglishUrlBase()
    {
        return URL_BASE_EN;
    }

    @Override
    public String getSpanishUrlBase()
    {
        return URL_BASE_ES;
    }
}
