package org.elpuentesearcy.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TrelloService
{
    private static final Gson GSON = new Gson();

    @Value( "${trello.api.baseUrl}" )
    private String trelloBaseUrl;
    @Value( "${trello.api.key}" )
    private String trelloKey;
    @Value( "${trello.api.token}" )
    private String trelloToken;

    public <T> T get( Class<T> responseType, String service, Object... parameters ) throws IOException
    {
        return makeTrelloRequest( responseType, RequestMethod.GET, service, parameters );
    }

    public <T> T get( Type responseType, String service, Object... parameters ) throws IOException
    {
        return makeTrelloRequest( responseType, RequestMethod.GET, service, parameters );
    }

    public <T> T put( Class<T> responseType, String service, Object... parameters ) throws IOException
    {
        return makeTrelloRequest( responseType, RequestMethod.PUT, service, parameters );
    }

    public <T> T put( Type responseType, String service, Object... parameters ) throws IOException
    {
        return makeTrelloRequest( responseType, RequestMethod.PUT, service, parameters );
    }

    public <T> T post( Class<T> responseType, String service, Object... parameters ) throws IOException
    {
        return makeTrelloRequest( responseType, RequestMethod.POST, service, parameters );
    }

    public <T> T post( Type responseType, String service, Object... parameters ) throws IOException
    {
        return makeTrelloRequest( responseType, RequestMethod.POST, service, parameters );
    }

    private <T> T makeTrelloRequest( Type typeOfT, RequestMethod requestMethod, String service, Object... parameters ) throws IOException
    {
        return GSON.fromJson( makeTrelloRequest( requestMethod, service, "[]", parameters ), typeOfT );
    }

    private <T> T makeTrelloRequest( Class<T> typeOfT, RequestMethod requestMethod, String service, Object... parameters ) throws IOException
    {
        return GSON.fromJson( makeTrelloRequest( requestMethod, service, "{}", parameters ), typeOfT );
    }

    public String makeTrelloRequest( RequestMethod requestMethod, String service, String defaultResult, Object... parameters ) throws IOException
    {
        String extraParameters = "";
        Map<String, Object> parameterMap = createParameters( parameters );
        for ( String key : parameterMap.keySet() )
        {
            extraParameters += "&" + key + "=" + parameterMap.get( key );
        }

        URL url = new URL( trelloBaseUrl + service +
                           "?key=" + trelloKey +
                           "&token=" + trelloToken +
                           extraParameters );
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod( requestMethod.name() );

        String json = defaultResult;
        try ( BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream() ) ) )
        {
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            json = sb.toString();
        }
        catch ( Exception exception )
        {
            String error;
            try ( BufferedReader br = new BufferedReader( new InputStreamReader( con.getErrorStream() ) ) )
            {
                StringBuilder sb = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    sb.append(output);
                }
                error = sb.toString();
            }
            log.error( "Unable to make Trello request: " + error, exception );
        }

        return json;
    }

    private Map<String, Object> createParameters( Object... keyValues )
    {
        Map<String, Object> parameters = new HashMap<>();

        for ( int i = 0; i < keyValues.length; i += 2 )
        {
            parameters.put( keyValues[i]+"", keyValues[i+1] );
        }

        return parameters;
    }
}
