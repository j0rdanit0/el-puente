package org.elpuentesearcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController
{
    public static final String URL_BASE = "/services";

    @GetMapping( URL_BASE )
    public String services()
    {
        return "services";
    }
}
