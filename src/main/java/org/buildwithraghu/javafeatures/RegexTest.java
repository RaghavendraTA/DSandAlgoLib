package org.buildwithraghu.javafeatures;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public void checkCorrectness(String word) {
        Pattern pattern = Pattern.compile("[a-z ]");
        Matcher matcher = pattern.matcher(word);
        while(matcher.find()) {
            System.out.print(matcher.group());
        }
    }

    public static void main(String[] args) {
        RegexTest retest = new RegexTest();
        retest.checkCorrectness("Here Only Small Character will be Matched without Space");
    }
}
