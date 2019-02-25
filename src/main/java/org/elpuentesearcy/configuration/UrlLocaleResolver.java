package org.elpuentesearcy.configuration;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class UrlLocaleResolver implements LocaleResolver
{
    private static final String URL_LOCALE_ATTRIBUTE_NAME = "URL_LOCALE_ATTRIBUTE_NAME";

    private enum ElPuenteLocale
    {
        English( Locale.ENGLISH, "en" ),
        Spanish( new Locale( "es" ), "es" )
        ;

        private final Locale locale;
        private final String path;

        ElPuenteLocale( Locale locale, String path )
        {
            this.locale = locale;
            this.path = path;
        }

        boolean matches( String uri, String contextPath )
        {
            boolean matches = false;

            String prefix = contextPath + "/" + path;
            if ( uri.startsWith( prefix ) )
            {
                if ( uri.length() > prefix.length() )
                {
                    matches = uri.startsWith( prefix + "/" );
                }
                else
                {
                    matches = true;
                }
            }

            return matches;
        }

        public static Optional<Locale> resolveLocale( HttpServletRequest request )
        {
            Optional<ElPuenteLocale> optional = Arrays.stream( values() )
                                                      .filter( l -> l.matches( request.getRequestURI(), request.getServletContext().getContextPath() ) )
                                                      .findFirst();

            Locale locale = optional.isPresent() ? optional.get().locale : (Locale) request.getSession().getAttribute( URL_LOCALE_ATTRIBUTE_NAME );

            return Optional.ofNullable( locale );
        }
    }

    @Override
    public Locale resolveLocale( HttpServletRequest request )
    {
        Locale locale = ElPuenteLocale.resolveLocale( request ).orElse( Locale.ENGLISH );

        //session-based locale (will override default if it has ever been set via the locale in the URL)
//        request.getSession().setAttribute( URL_LOCALE_ATTRIBUTE_NAME, locale );

        return locale;
    }

    @Override
    public void setLocale( HttpServletRequest request, HttpServletResponse response, Locale locale )
    {
        // Nothing
    }
}
