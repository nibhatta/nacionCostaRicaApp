package com.nacion.android.nacioncostarica.useCases;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class JSONFeedImpl implements JSONFeed{
    private static int HTTP_RESPONSE_OK = 200;
    private static String EMPTY_JSON = "";

    public String readJSONFeed(String argUrl){
        if(isUrlNotEmpty(argUrl)){
            throw new IllegalArgumentException("Invalid argument for JSONFeedImpl.readJSONFeed");
        }
        String json = new String();
        try{
            HttpResponse response = downloadJsonFile(argUrl);
            json = isResponseOK(response) ? convertJsonFileToString(response) : EMPTY_JSON;
        }catch(ClientProtocolException e){
            Log.d(JSONFeedImpl.class.getName(), e.getLocalizedMessage());
        }catch(IOException e){
            Log.d(JSONFeedImpl.class.getName(), e.getLocalizedMessage());
        }
        return json;
    }

    private boolean isResponseOK(HttpResponse argResponse){
        return(argResponse != null && argResponse.getStatusLine().getStatusCode() == HTTP_RESPONSE_OK);
    }

    private HttpResponse downloadJsonFile(String argUrl) throws IOException{
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(argUrl);
        HttpResponse response = httpClient.execute(httpGet);
        return response;
    }

    private String convertJsonFileToString(HttpResponse argResponse){
        StringBuilder json = new StringBuilder();
        try {
            HttpEntity entity = argResponse.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            inputStream.close();
        }catch(IOException e){
            Log.d(JSONFeedImpl.class.getName(), e.getLocalizedMessage());
        }
        return json.toString();
    }

    private boolean isUrlNotEmpty(String argUrl){
        return argUrl == null && argUrl.isEmpty();
    }
}
