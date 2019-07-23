package org.elpuentesearcy.configuration;

import org.elpuentesearcy.ElPuenteBoot;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties( "org.elpuentesearcy" )
public class Properties
{
    private String version;
    private boolean isTestMode;
    private List<String> physicalAddress = new ArrayList<>();
    private List<String> mailingAddress = new ArrayList<>();
    private String officeHoursWeekday;
    private String officeHoursWeekend;
    private String email;
    private String phone;
    private String facebookUrl;
    private String twitterUrl;
    private Google google;
    private List<Employee> board;
    private List<Employee> staff;
    private List<String> siteChanges = new ArrayList<>();

    private static Properties instance;

    @PostConstruct
    @Order( Ordered.HIGHEST_PRECEDENCE )
    public void postConstruct()
    {
        instance = this;
    }

    public static Properties get()
    {
        return instance;
    }

    public static class Google
    {
        private Analytics analytics;
        private Maps maps;

        public static class Analytics
        {
            private String trackingId;

            public String getTrackingId()
            {
                return trackingId;
            }

            public void setTrackingId( String trackingId )
            {
                this.trackingId = trackingId;
            }
        }

        public static class Maps
        {
            private String apiKey;

            public String getApiKey()
            {
                return apiKey;
            }

            public void setApiKey( String apiKey )
            {
                this.apiKey = apiKey;
            }
        }

        public Analytics getAnalytics()
        {
            return analytics;
        }

        public void setAnalytics( Analytics analytics )
        {
            this.analytics = analytics;
        }

        public Maps getMaps()
        {
            return maps;
        }

        public void setMaps( Maps maps )
        {
            this.maps = maps;
        }
    }

    public static class Employee
    {
        private String name;
        private String image;
        private String squareImage;
        private Map<String, String> title;
        private Map<String, String> description;

        public Employee()
        {
        }

        public String getName()
        {
            return name;
        }

        public void setName( String name )
        {
            this.name = name;

            String imageFileName = ElPuenteBoot.IMAGE_FOLDER + name.toLowerCase().replaceAll( " ", "" );
            setImage( imageFileName + ".jpg" );
            setSquareImage( imageFileName + "-square.jpg" );
        }

        public String getImage()
        {
            return image;
        }

        public void setImage( String image )
        {
            this.image = image;
        }

        public String getSquareImage()
        {
            return squareImage;
        }

        public void setSquareImage( String squareImage )
        {
            this.squareImage = squareImage;
        }

        public String getLocalTitle( String locale )
        {
            return getLocalString( locale, title ).replaceAll( " ", "&nbsp;" );
        }

        public Map<String, String> getTitle()
        {
            return title;
        }

        public void setTitle( Map<String, String> title )
        {
            this.title = title;
        }

        public String getLocalDescription( String locale )
        {
            return getLocalString( locale, description );
        }

        public Map<String, String> getDescription()
        {
            return description;
        }

        public void setDescription( Map<String, String> description )
        {
            this.description = description;
        }

        private String getLocalString( String locale, Map<String, String> map )
        {
            return map.getOrDefault( locale, map.getOrDefault( "en", "" ) );
        }
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public boolean isTestMode()
    {
        return isTestMode;
    }

    public void setIsTestMode( boolean testMode )
    {
        isTestMode = testMode;
    }

    public Google getGoogle()
    {
        return google;
    }

    public void setGoogle( Google google )
    {
        this.google = google;
    }

    public List<Employee> getBoard()
    {
        return board;
    }

    public void setBoard( List<Employee> board )
    {
        this.board = board;
    }

    public List<String> getPhysicalAddress()
    {
        return physicalAddress;
    }

    public void setPhysicalAddress( List<String> physicalAddress )
    {
        this.physicalAddress = physicalAddress;
    }

    public List<String> getMailingAddress()
    {
        return mailingAddress;
    }

    public void setMailingAddress( List<String> mailingAddress )
    {
        this.mailingAddress = mailingAddress;
    }

    public String getOfficeHoursWeekday()
    {
        return officeHoursWeekday;
    }

    public void setOfficeHoursWeekday( String officeHoursWeekday )
    {
        this.officeHoursWeekday = officeHoursWeekday;
    }

    public String getOfficeHoursWeekend()
    {
        return officeHoursWeekend;
    }

    public void setOfficeHoursWeekend( String officeHoursWeekend )
    {
        this.officeHoursWeekend = officeHoursWeekend;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getFormattedPhone()
    {
        return phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3" );
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public String getFacebookUrl()
    {
        return facebookUrl;
    }

    public void setFacebookUrl( String facebookUrl )
    {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl()
    {
        return twitterUrl;
    }

    public void setTwitterUrl( String twitterUrl )
    {
        this.twitterUrl = twitterUrl;
    }

    public List<Employee> getStaff()
    {
        return staff;
    }

    public void setStaff( List<Employee> staff )
    {
        this.staff = staff;
    }

    public List<String> getSiteChanges()
    {
        return siteChanges;
    }

    public void setSiteChanges( List<String> siteChanges )
    {
        this.siteChanges = siteChanges;
    }
}
