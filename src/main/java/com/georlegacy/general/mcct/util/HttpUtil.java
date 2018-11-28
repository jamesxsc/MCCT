package com.georlegacy.general.mcct.util;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpUtil {

    public static HttpResponse post(StringEntity params, String url, String user, String pass) throws IOException {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, pass);
        provider.setCredentials(AuthScope.ANY, credentials);

        HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);

        return client.execute(request);
    }

}
