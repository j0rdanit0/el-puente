package org.elpuentesearcy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgramsController
{
    public static final String URL_BASE = "/programs";

    @GetMapping( URL_BASE )
    public String programs()
    {
        return "programs";
    }
}
