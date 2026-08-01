package org.elpuentesearcy.controller;

import org.elpuentesearcy.domain.ResourceCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Controller
public class ResourcesController extends BaseController
{
    public static final String URL_BASE_EN = "/resources";
    public static final String URL_BASE_ES = "/recursos";

    @GetMapping( value = { URL_BASE_EN, URL_BASE_ES } )
    public String resources( Model model )
    {
        SortedSet<ResourceCard> services = new TreeSet<>();
        // Healthcare / Medical — teal
        services.add( new ResourceCard( "ARcare", "https://www.arcare.net/", "fas fa-heartbeat", "card-teal" ) );
        services.add( new ResourceCard( "ARKids First", "https://humanservices.arkansas.gov/about-dhs/dms/ar-kids", "fas fa-child", "card-teal" ) );
        services.add( new ResourceCard( "Arkansas Department of Health", "https://www.healthy.arkansas.gov/", "fas fa-notes-medical", "card-teal" ) );
        services.add( new ResourceCard( "Arkansas Hospice", "https://arkansashospice.org", "fas fa-hand-holding-heart", "card-teal" ) );
        services.add( new ResourceCard( "Arisa Health", "https://www.arisahealth.org/locations/searcy", "fas fa-brain", "card-teal" ) );
        services.add( new ResourceCard( "Roots-Raices Bilingual Counseling", "https://www.roots-raices.com/", "fas fa-spa", "card-teal" ) );
        services.add( new ResourceCard( "Unity Health", "https://www.unity-health.org/", "fas fa-hospital", "card-teal" ) );
        services.add( new ResourceCard( "White County Aging Program", "http://www.whitecountyaging.com/", "fas fa-user-friends", "card-teal" ) );
        // Government / Civic — blue
        services.add( new ResourceCard( "Arkansas Court Connect", "https://caseinfo.arcourts.gov/cconnect/PROD/public/ck_public_qry_main.cp_main_idx", "fas fa-gavel", "card-blue" ) );
        services.add( new ResourceCard( "Arkansas Department of Human Services", "https://humanservices.arkansas.gov/offices/detail/white", "fas fa-hands-helping", "card-blue" ) );
        services.add( new ResourceCard( "Arkansas Department of Workforce Services", "https://www.dws.arkansas.gov/", "fas fa-briefcase", "card-blue" ) );
        services.add( new ResourceCard( "City of Searcy", "https://www.cityofsearcy.org/", "fas fa-city", "card-blue" ) );
        services.add( new ResourceCard( "IRS (Internal Revenue Service)", "https://www.irs.gov/", "fas fa-file-invoice-dollar", "card-blue" ) );
        services.add( new ResourceCard( "Searcy Revenue Office", "https://www.dfa.arkansas.gov/office-locations/details/searcy-revenue-office", "fas fa-receipt", "card-blue" ) );
        services.add( new ResourceCard( "Social Security Administration", "https://www.ssa.gov/", "fas fa-id-card", "card-blue" ) );
        services.add( new ResourceCard( "U.S. Citizenship and Immigration Services", "https://www.uscis.gov/", "fas fa-flag-usa", "card-blue" ) );
        services.add( new ResourceCard( "Voter Registration", "https://www.eac.gov/", "fas fa-vote-yea", "card-blue" ) );
        services.add( new ResourceCard( "White County Government", "https://www.whitecountyar.org/", "fas fa-landmark", "card-blue" ) );
        // Legal / Immigration — orange
        services.add( new ResourceCard( "Hernandez Law Firm, Immigration Attorney", "https://ghernandezlaw.com/", "fas fa-balance-scale", "card-orange" ) );
        services.add( new ResourceCard( "Mexican Consulate in Arkansas", "https://consulmex.sre.gob.mx/littlerock/", "fas fa-passport", "card-orange" ) );
        // Utilities — green
        services.add( new ResourceCard( "Entergy", "https://www.entergy-arkansas.com/", "fas fa-bolt", "card-green" ) );
        services.add( new ResourceCard( "Searcy Office of Motor Vehicles", "https://dmvhandbook.org/locations/searcy-office-of-motor-vehicles/", "fas fa-car", "card-green" ) );
        services.add( new ResourceCard( "Searcy Sanitation Department", "https://www.cityofsearcy.org/Sanitation.html", "fas fa-trash-alt", "card-green" ) );
        services.add( new ResourceCard( "Searcy Water Utilities", "https://www.searcywater.org/", "fas fa-tint", "card-green" ) );
        services.add( new ResourceCard( "Summit", "https://summitutilities.com/", "fas fa-fire", "card-green" ) );
        services.add( new ResourceCard( "U.S. Postal Service", "https://tools.usps.com/find-location.htm?locationType=po&searchRadius=20&fdbid=1381088&", "fas fa-envelope", "card-green" ) );
        // Emergency / Safety — coral/red
        services.add( new ResourceCard( "Searcy Fire Department", "https://www.cityofsearcy.org/FireDept.php", "fas fa-fire-extinguisher", "card-red" ) );
        services.add( new ResourceCard( "Searcy Police Department", "https://www.cityofsearcy.org/Police.html", "fas fa-shield-alt", "card-red" ) );
        services.add( new ResourceCard( "White County Sheriff's Office", "https://wcso.cc/", "fas fa-star", "card-red" ) );
        // Business / Community — purple
        services.add( new ResourceCard( "Searcy Regional Chamber of Commerce", "https://www.searcychamber.com/", "fas fa-store", "card-purple" ) );
        model.addAttribute( "services", services );

        List<ResourceCard> education = new ArrayList<>();
        education.add( new ResourceCard( "Arkansas State University - Beebe", "http://www.asub.edu/", "fas fa-university", "card-indigo" ) );
        education.add( new ResourceCard( "Arkansas State University - Searcy", "http://www.asub.edu/about-us/locations/searcy.aspx", "fas fa-university", "card-indigo" ) );
        education.add( new ResourceCard( "GED Testing Service", "https://ged.com/", "fas fa-certificate", "card-indigo" ) );
        education.add( new ResourceCard( "Harding University", "https://www.harding.edu/", "fas fa-graduation-cap", "card-indigo" ) );
        education.add( new ResourceCard( "Pangburn School District", "https://www.pangburnschools.org/", "fas fa-school", "card-indigo" ) );
        education.add( new ResourceCard( "Riverview School District", "https://www.riverviewsd.org/", "fas fa-school", "card-indigo" ) );
        education.add( new ResourceCard( "Searcy Public Schools", "http://www.searcyschools.org/", "fas fa-school", "card-indigo" ) );
        education.add( new ResourceCard( "White County Central Schools", "https://www.wccsd.k12.ar.us/", "fas fa-school", "card-indigo" ) );
        model.addAttribute( "education", education );

        SortedSet<ResourceCard> nonProfits = new TreeSet<>();
        nonProfits.add( new ResourceCard( "100 Families", "https://www.restorehopear.org/", "fas fa-home", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Avanza Arkansas", "https://avanzaarkansas.org", "fas fa-globe-americas", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Child Safety Center of White County", "https://childsafetycenter.org/", "fas fa-child", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Community Action Program for Central Arkansas", "https://www.capcainc.org/", "fas fa-people-carry", "card-warm" ) );
        nonProfits.add( new ResourceCard( "El Centro Hispano, Jonesboro", "https://www.centrohispanoarkansas.com/", "fas fa-globe-americas", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Hope Cottage", "http://www.hopecottage.info/", "fas fa-house-user", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Jacob's Place", "http://www.jacobsplace.org/index.html", "fas fa-hands", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Juntos Unidos, Batesville", "https://www.facebook.com/LatinosCreandoHorizontes/", "fas fa-globe-americas", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Literacy Council of White County", "https://whitecountyliteracy.org/", "fas fa-book-open", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Seis Puentes, North Little Rock", "https://seispuentes.org/", "fas fa-globe-americas", "card-warm" ) );
        nonProfits.add( new ResourceCard( "Sparrow's Promise", "https://sparrowspromise.org/", "fas fa-dove", "card-warm" ) );
        nonProfits.add( new ResourceCard( "United Way of White County", "https://www.unitedwayofwhitecounty.org/", "fas fa-hand-holding-usd", "card-warm" ) );
        model.addAttribute( "nonProfits", nonProfits );

        return "resources";
    }

    @Override
    public String getEnglishUrlBase()
    {
        return URL_BASE_EN;
    }

    @Override
    public String getSpanishUrlBase()
    {
        return URL_BASE_ES;
    }
}
