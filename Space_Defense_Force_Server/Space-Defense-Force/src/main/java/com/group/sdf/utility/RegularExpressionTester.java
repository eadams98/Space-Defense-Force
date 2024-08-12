package com.group.sdf.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionTester {
	
	static private void TestRegex(String pattern, String[] testCases) {
		Pattern generatedRegex = Pattern.compile(pattern);
		Matcher validator;
		
		System.out.println("The tested pattern is " + pattern);
		
		for( String testCase : testCases) {
			validator = generatedRegex.matcher(testCase);
			System.out.println(	"String " + testCase + " has " + 
								(validator.matches() == true ? "passed" : "failed"));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String upgradeNamePattern = "([A-Z][a-z0-9]{2,}){1}([ ][A-Z][a-z0-9]*)*";
		String[] upgradeNameTestCases = {"hello", "world", "Ship C4", "Ac130", "AC130", "Space Defense Force Cruiser", "Bfg Model G Ship"};
		TestRegex(upgradeNamePattern, upgradeNameTestCases);
		// TestRegex("([A-Z][a-z0-9]{2,}){1}([ ][A-Z][a-z0-9]*)*", {"hello", "world"}); Doesn't work and wanted to know why, but saving it for later
	}

}
