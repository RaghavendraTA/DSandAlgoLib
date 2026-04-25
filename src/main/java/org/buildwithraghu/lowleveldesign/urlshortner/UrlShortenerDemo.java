package org.buildwithraghu.lowleveldesign.urlshortner;

import java.time.Duration;

public class UrlShortenerDemo {
    // =============== Demo Main =================
    public static void main(String[] args) throws Exception {
        UrlShortenerService service = new UrlShortenerService();

        String s1 = service.create("https://leetcode.com/problems", Duration.ofHours(1));
        String s2 = service.createCustom("https://openai.com", "openai", Duration.ofMinutes(30));

        System.out.println("Short1: " + s1);
        System.out.println("Short2: " + s2);

        System.out.println("Resolve1: " + service.get(s1));
        System.out.println("Resolve2: " + service.get("openai"));

        System.out.println("Access count for openai: " + service.getCodeToUrl().get("openai").getVisits());
    }
}
