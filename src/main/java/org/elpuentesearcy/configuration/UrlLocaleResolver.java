package org.elpuentesearcy.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

public class UrlLocaleResolver implements LocaleResolver
{
    @Getter
    @RequiredArgsConstructor
    public enum ElPuenteLanguage
    {
        English( Locale.ENGLISH, "en" ),
        Spanish( new Locale( "es" ), "es" );

        private final Locale locale;
        private final String subdomain;

        public static Optional<ElPuenteLanguage> get( String subdomain )
        {
            return Arrays.stream( values() )
                         .filter( l -> l.subdomain.equalsIgnoreCase( subdomain ) )
                         .findFirst();
        }

        public static Optional<ElPuenteLanguage> getFromSubdomain( HttpServletRequest request )
        {
            String domain = request.getServerName();
            String subdomain = domain.substring( 0, Math.max( 0, domain.indexOf( '.' ) ) );

            return get( subdomain );
        }

        public static Optional<ElPuenteLanguage> getFromHeaders( HttpServletRequest request )
        {
            return Optional.ofNullable( request.getHeader( "Accept-Language" ) )
                           .flatMap( languageHeader -> {
                               String localeString = languageHeader.substring( 0, Math.max( 0, languageHeader.indexOf( "," ) ) );
                               if ( localeString.contains( "-" ) )
                               {
                                   localeString = localeString.substring( 0, localeString.indexOf( "-" ) );
                               }

                               return get( localeString );
                           } );
        }
    }

    @Override
    public Locale resolveLocale( HttpServletRequest request )
    {
        return ElPuenteLanguage.getFromSubdomain( request )
                               .orElseGet( () -> ElPuenteLanguage.getFromHeaders( request )
                                                                 .orElse( ElPuenteLanguage.English )
                             ).getLocale();
    }

    @Override
    public void setLocale( HttpServletRequest request, HttpServletResponse response, Locale locale )
    {
        throw new UnsupportedOperationException( "Cannot change sub-domain locale - use a different locale resolution strategy" );
    }
}
