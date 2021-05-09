package com.example.lab9_getwebpagesourcecode;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG= NetworkUtils.class.getSimpleName();
    private static final String HTTP="http";
    private static final String HTTPS="https";

    static String getSoruceCode(Context context, String queryString, String protocol){
        HttpURLConnection httpURLConnection= null;
        BufferedReader reader=null;
        String htmlSourceCode= null;
        String [] protocolList= context.getResources().getStringArray(R.array.spinner_item);
        try {
            Uri builderUri;
            if(protocol.equals(protocolList[0])) {
                builderUri = Uri.parse(queryString).buildUpon().scheme(HTTP).build();
            }
            else{
                builderUri= Uri.parse(queryString).buildUpon().scheme(HTTPS).build();
            }
            URL requestURL= new URL(builderUri.toString());
            httpURLConnection= (HttpURLConnection) requestURL.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream= httpURLConnection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder= new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

            if(stringBuilder.length()==0){
                return null;
            }
            htmlSourceCode= stringBuilder.toString();
            return htmlSourceCode;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                if(httpURLConnection!=null){
                    httpURLConnection.disconnect();
                }
                if(reader!=null){
                    try{
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(htmlSourceCode!=null) {
            Log.d(LOG_TAG, htmlSourceCode);
        }
        else{
            Log.d(LOG_TAG, "Null");

        }
        return htmlSourceCode;
    }
}
