package org.buildwithraghu.lowleveldesign.urlshortner;

public class Utils {
    public static final String BASE_HOST = "http://sho.rt/";
    public static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int BASE = ALPHABET.length();

    // ================= Utility =================
    public static String encode(long id) {
        StringBuilder sb = new StringBuilder();
        while (id > 0) {
            sb.append(ALPHABET.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return sb.reverse().toString();
    }

    public static boolean isValidAlias(String alias) {
        return alias.matches("^[A-Za-z0-9_-]+$");
    }

    public static String normalize(String url) {
        return url.startsWith(BASE_HOST) ? url.substring(BASE_HOST.length()) : url;
    }
}
