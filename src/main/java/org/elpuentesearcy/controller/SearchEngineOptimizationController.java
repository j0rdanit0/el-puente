package org.elpuentesearcy.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.configuration.ElPuenteCaches;
import org.elpuentesearcy.configuration.SitemapView;
import org.elpuentesearcy.configuration.UrlLocaleResolver;
import org.elpuentesearcy.service.UrlService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SearchEngineOptimizationController
{
    public static final String SITEMAP_URL = "/sitemap.xml";

    private final SitemapView view;
    private final UrlService urlService;

    @RequestMapping( path = SITEMAP_URL, produces = MediaType.APPLICATION_XML_VALUE )
    public SitemapView createSitemap()
    {
        return view;
    }

    @Cacheable( cacheNames = ElPuenteCaches.ROBOTS, key = "1" )
    @GetMapping( path = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE )
    @ResponseBody
    public String createRobots( HttpServletResponse response )
    {
        response.setContentType( MediaType.TEXT_PLAIN_VALUE );

        String robots = "Sitemap: " + urlService.getBaseUrl() + SITEMAP_URL;

        for ( UrlLocaleResolver.ElPuenteLanguage language : UrlLocaleResolver.ElPuenteLanguage.values() )
        {
            robots += "\nSitemap: " + urlService.getBaseUrl( language ) + SITEMAP_URL;
        }

        return robots;
    }
}
