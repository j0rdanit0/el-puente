package org.elpuentesearcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgramsController extends BaseController
{
    public static final String URL_BASE_EN = "/programs";
    public static final String URL_BASE_ES = "/programas";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String programs()
    {
        return "programs";
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
