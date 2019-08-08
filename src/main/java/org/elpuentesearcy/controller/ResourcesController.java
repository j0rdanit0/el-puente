package org.elpuentesearcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResourcesController
{
    public static final String URL_BASE = "/resources";

    @GetMapping( URL_BASE )
    public String resources()
    {
        return "resources";
    }
}
