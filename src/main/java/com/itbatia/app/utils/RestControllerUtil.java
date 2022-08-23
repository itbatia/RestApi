package com.itbatia.app.utils;

public class RestControllerUtil {

    public static Integer getIdFromUrl(StringBuffer url) {
        int lastSlashIndex = url.lastIndexOf("/");
        String endPoint = url.substring(lastSlashIndex + 1);
        return Integer.parseInt(endPoint);
    }

    public static String getEndpointFromUrl(StringBuffer url) {
        int lastSlashIndex = url.lastIndexOf("/");
        return url.substring(lastSlashIndex + 1);
    }
}
