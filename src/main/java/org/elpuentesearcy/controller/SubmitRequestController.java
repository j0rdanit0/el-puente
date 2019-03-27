package org.elpuentesearcy.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty( "org.elpuentesearcy.isTestMode" )
public class SubmitRequestController
{
    @GetMapping( "/submitRequest" )
    public String home()
    {
        return "submitRequest";
    }
}
