package org.elpuentesearcy.configuration;

import org.elpuentesearcy.service.SitemapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

@Service
public final class SitemapView extends AbstractView
{
    @Autowired
    private SitemapService sitemapService;

    @Override
    protected void renderMergedOutputModel( Map<String, Object> map, HttpServletRequest request, HttpServletResponse response ) throws Exception
    {
        response.setContentType( MediaType.APPLICATION_XML_VALUE );

        try ( Writer writer = response.getWriter() )
        {
            writer.append( sitemapService.createSitemap( UrlLocaleResolver.ElPuenteLanguage.getFromSubdomain( request ).orElse( null ) ) );
        }
    }
}
