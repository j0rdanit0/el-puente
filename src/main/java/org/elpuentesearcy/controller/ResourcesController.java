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
        services.add( new ResourceCard( "ARcare", "https://www.arcare.net/" ) );
        services.add( new ResourceCard( "Arkansas Department of Human Services", "https://humanservices.arkansas.gov/offices/detail/white" ) );
        services.add( new ResourceCard( "Arkansas Department of Workforce Services", "https://www.dws.arkansas.gov/" ) );
        services.add( new ResourceCard( "ARKids First", "https://humanservices.arkansas.gov/about-dhs/dms/ar-kids" ) );
        services.add( new ResourceCard( "Summit", "https://summitutilities.com/" ) );
        services.add( new ResourceCard( "City of Searcy", "https://www.cityofsearcy.org/" ) );
        services.add( new ResourceCard( "Entergy", "https://www.entergy-arkansas.com/" ) );
        services.add( new ResourceCard( "IRS (Internal Revenue Service)", "https://www.irs.gov/" ) );
        services.add( new ResourceCard( "Mexican Consulate in Arkansas", "https://consulmex.sre.gob.mx/littlerock/" ) );
        services.add( new ResourceCard( "Searcy Fire Department", "https://www.cityofsearcy.org/FireDept.php" ) );
        services.add( new ResourceCard( "Searcy Office of Motor Vehicles", "https://dmvhandbook.org/locations/searcy-office-of-motor-vehicles/" ) );
        services.add( new ResourceCard( "Searcy Police Department", "https://www.cityofsearcy.org/Police.html" ) );
        services.add( new ResourceCard( "Searcy Regional Chamber of Commerce", "https://www.searcychamber.com/" ) );
        services.add( new ResourceCard( "Searcy Revenue Office", "https://www.dfa.arkansas.gov/office-locations/details/searcy-revenue-office" ) );
        services.add( new ResourceCard( "Searcy Sanitation Department", "https://www.cityofsearcy.org/Sanitation.html" ) );
        services.add( new ResourceCard( "Searcy Water Utilities", "https://www.searcywater.org/" ) );
        services.add( new ResourceCard( "Social Security Administration", "https://www.ssa.gov/" ) );
        services.add( new ResourceCard( "Unity Health", "https://www.unity-health.org/" ) );
        services.add( new ResourceCard( "U.S. Citizenship and Immigration Services", "https://www.uscis.gov/" ) );
        services.add( new ResourceCard( "U.S. Postal Service", "https://tools.usps.com/find-location.htm?locationType=po&searchRadius=20&fdbid=1381088&" ) );
        services.add( new ResourceCard( "Voter Registration", "https://www.eac.gov/" ) );
        services.add( new ResourceCard( "White County Aging Program", "http://www.whitecountyaging.com/" ) );
        services.add( new ResourceCard( "White County Government", "https://www.whitecountyar.org/" ) );
        services.add( new ResourceCard( "Arkansas Department of Health", "https://www.healthy.arkansas.gov/" ) );
        services.add( new ResourceCard( "White County Regional Library System", "https://whitecountylibraries.org/" ) );
        services.add( new ResourceCard( "White County Sheriff's Office", "https://wcso.cc/" ) );
        services.add( new ResourceCard( "Arkansas Hospice", "https://arkansashospice.org" ) );
        services.add( new ResourceCard( "Arkansas Court Connect", "https://caseinfo.arcourts.gov/cconnect/PROD/public/ck_public_qry_main.cp_main_idx" ) );
        services.add( new ResourceCard( "Arisa Health", "https://www.arisahealth.org/locations/searcy" ) );
        services.add( new ResourceCard( "Hernandez Law Firm, Immigration Attorney", "https://ghernandezlaw.com/" ) );
        services.add( new ResourceCard( "Roots-Raices Bilingual Counseling", "https://www.roots-raices.com/" ) );
        model.addAttribute( "services", services );

        List<ResourceCard> education = new ArrayList<>();
        education.add( new ResourceCard( "Searcy Public Schools", "http://www.searcyschools.org/" ) );
        education.add( new ResourceCard( "Riverview School District", "https://www.riverviewsd.org/" ) );
        education.add( new ResourceCard( "White County Central Schools", "https://www.wccsd.k12.ar.us/" ) );
        education.add( new ResourceCard( "Harding University", "https://www.harding.edu/" ) );
        education.add( new ResourceCard( "Arkansas State University - Beebe", "http://www.asub.edu/" ) );
        education.add( new ResourceCard( "Arkansas State University - Searcy", "http://www.asub.edu/about-us/locations/searcy.aspx" ) );
        education.add( new ResourceCard( "GED Testing Service", "https://ged.com/" ) );
        education.add( new ResourceCard( "Pangburn School District", "https://www.pangburnschools.org/" ) );
        model.addAttribute( "education", education );

        SortedSet<ResourceCard> nonProfits = new TreeSet<>();
        nonProfits.add( new ResourceCard( "Child Safety Center of White County", "https://childsafetycenter.org/" ) );
        nonProfits.add( new ResourceCard( "Community Action Program for Central Arkansas", "https://www.capcainc.org/" ) );
        nonProfits.add( new ResourceCard( "Hope Cottage", "http://www.hopecottage.info/" ) );
        nonProfits.add( new ResourceCard( "Jacob's Place", "http://www.jacobsplace.org/index.html" ) );
        nonProfits.add( new ResourceCard( "Literacy Council of White County", "https://whitecountyliteracy.org/" ) );
        nonProfits.add( new ResourceCard( "Sparrow's Promise", "https://sparrowspromise.org/" ) );
        nonProfits.add( new ResourceCard( "100 Families", "https://www.restorehopear.org/" ) );
        nonProfits.add( new ResourceCard( "United Way of White County", "https://www.unitedwayofwhitecounty.org/" ) );
        nonProfits.add( new ResourceCard( "El Centro Hispano, Jonesboro", "https://www.centrohispanoarkansas.com/" ) );
        nonProfits.add( new ResourceCard( "Seis Puentes, North Little Rock", "https://seispuentes.org/" ) );
        nonProfits.add( new ResourceCard( "Juntos Unidos, Batesville", "https://www.facebook.com/LatinosCreandoHorizontes/" ) );
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
