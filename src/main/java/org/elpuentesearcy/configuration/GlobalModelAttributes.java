package org.elpuentesearcy.configuration;

import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.controller.BaseController;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes
{
    private final Properties properties;
    private final Environment environment;
    private final Map<String, BaseController> baseControllers;

    @ModelAttribute( "properties" )
    public Properties properties()
    {
        return properties;
    }

    @ModelAttribute( "environment" )
    public Environment environment()
    {
        return environment;
    }

    @ModelAttribute( "urls" )
    public Map<String, BaseController> urls()
    {
        return baseControllers.entrySet().stream()
            .collect( Collectors.toMap(
                e -> e.getKey().replaceAll( "Controller$", "" ),
                Map.Entry::getValue
            ) );
    }
}
