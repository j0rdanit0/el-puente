package org.elpuentesearcy.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.elpuentesearcy.ElPuenteBoot;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties( "org.elpuentesearcy" )
public class Properties
{
    private String version;
    private String domain;
    private List<String> physicalAddress = new ArrayList<>();
    private List<String> mailingAddress = new ArrayList<>();
    private String officeHoursWeekday;
    private String officeHoursWeekend;
    private String email;
    private String phone;
    private String facebookUrl;
    private String twitterUrl;
    private String instagramUrl;
    private String giveLivelyUrl;
    private String salsaCompetitionSignupUrl;
    private String salsaCompetitionRulesDownload;
    private String gardenTour2021Url;
    private String volunteerFormDownload;
    private String cashtag;
    private String volunteerFormUrl_en;
    private String volunteerFormUrl_es;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private LocalDate showGivingTuesdayWidgetStart;
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private LocalDate showGivingTuesdayWidgetEnd;
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )

    private Google google;
    private List<Employee> board;
    private List<Employee> staff;

    @Data
    public static class Google
    {
        private Analytics analytics;
        private Maps maps;

        @Data
        public static class Analytics
        {
            private String trackingId;
        }

        @Data
        public static class Maps
        {
            private String apiKey;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Employee
    {
        private String name;
        private String image;
        private String squareImage;
        private Map<String, String> title;
        private boolean abiceCertified;

        public void setName( String name )
        {
            this.name = name;

            String imageFileName = ElPuenteBoot.IMAGE_FOLDER + name.toLowerCase().replace( " ", "" );
            setImage( imageFileName + ".jpg" );
            setSquareImage( imageFileName + "-square.jpg" );
        }

        public String getLocalTitle( String locale )
        {
            return getLocalString( locale, title ).replace( " ", "&nbsp;" );
        }

        private String getLocalString( String locale, Map<String, String> map )
        {
            return map.getOrDefault( locale, map.getOrDefault( "en", "" ) );
        }
    }

    public String getFormattedPhone()
    {
        return phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3" );
    }

    public String getSalsaCompetitionRulesDownloadPath()
    {
        return getDownloadsPath( salsaCompetitionRulesDownload );
    }

    public String getVolunteerFormDownloadPath()
    {
        return getDownloadsPath( volunteerFormDownload );
    }

    private String getDownloadsPath( String fileName )
    {
        return ElPuenteBoot.DOWNLOADS_FOLDER + fileName + ".pdf";
    }

    public boolean isShowGivingTuesdayWidget()
    {
        return LocalDate.now().isAfter( showGivingTuesdayWidgetStart ) && LocalDate.now().isBefore( showGivingTuesdayWidgetEnd );
    }
}
