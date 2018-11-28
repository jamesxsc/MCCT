package com.georlegacy.general.mcct.util;

import com.georlegacy.general.mcct.data.objects.hook.enumeration.TreePattern;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;

public class TreeAPIUtil {

    private static final String url = "https://treeserver.dlljs.uk/restapi/gopattern";

    private static final String user = "brocraft";
    private static final String pass = "Chr1stm4s*";

    public static void on() {
        try {
            HttpResponse response = HttpUtil.post(
                    new StringEntity("{\"pattern\":\"poweron\"}"),
                    url,
                    user,
                    pass
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void off() {
        try {
            HttpResponse response = HttpUtil.post(
                    new StringEntity("{\"pattern\":\"poweroff\"}"),
                    url,
                    user,
                    pass
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPattern(TreePattern pattern) {
        try {
            HttpResponse response = HttpUtil.post(
                    new StringEntity("{\"pattern\":\"" + pattern.getApiCode() + "\"}"),
                    url,
                    user,
                    pass
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreePattern getCurrentPattern() {
        try {
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("brocraft", "Chr1stm4s*".toCharArray());
                }
            });

            URL url = new URL("https://treeserver.dlljs.uk/restapi/getpattern");
            Scanner scanner = null;
            scanner = new Scanner(url.openStream());
            String response = scanner.useDelimiter("\\Z").next();
            return TreePattern.valueOf(new Gson().fromJson(response, JsonObject.class).get("CurrentPattern").getAsString().toUpperCase());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
