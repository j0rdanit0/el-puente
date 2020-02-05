package org.elpuentesearcy.configuration;

import lombok.RequiredArgsConstructor;
import org.elpuentesearcy.service.SitemapService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

@Component
@RequiredArgsConstructor
public final class SitemapView extends AbstractView
{
    private final SitemapService sitemapService;

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
