package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testregexp {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
        Matcher matcher = pattern.matcher("size=");
        boolean rep = matcher.find();
        System.out.println(rep);
    }
}
