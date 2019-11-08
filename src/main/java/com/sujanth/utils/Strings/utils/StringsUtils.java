package com.sujanth.utils.Strings.utils;

import java.util.Set;
import java.util.TreeSet;

public class StringsUtils {
	
	public void removeDuplicatesandSortString(String string){
		Set<String> set = new TreeSet<>();
		for(int i=0;i<string.length();i++){
			set.add(String.valueOf(string.charAt(i)));
		}
		System.out.println(set);
	}
	
	public static void main(String[] args) {
		StringsUtils su = new StringsUtils();
		su.removeDuplicatesandSortString("facde");
	}

}
