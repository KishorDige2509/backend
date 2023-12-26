package com.leclique;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {

	/**
	 * input: abc{def}hikl{mno}p Output: abc{fed}hikl{onm}p
	 */

	public static void main(String[] args) {
        String input = "abc{def}hikl{mno}p";
        String output = reverseInsideCurlyBraces(input);
        System.out.println(output);
    }

    private static String reverseInsideCurlyBraces(String input) {
        Pattern pattern = Pattern.compile("\\{(.*?)\\}");
        Matcher matcher = pattern.matcher(input);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String insideBraces = matcher.group(1);
            String reversedInsideBraces = new StringBuilder(insideBraces).reverse().toString();
            matcher.appendReplacement(result, "{" + reversedInsideBraces + "}");
        }
        matcher.appendTail(result);

        return result.toString();
    }

}
