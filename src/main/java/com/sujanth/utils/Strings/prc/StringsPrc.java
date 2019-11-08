package com.sujanth.utils.Strings.prc;

public class StringsPrc {
	
	public void stringEqualityandInterning() {
		String constantString1 = "interned Baeldung";
		String constantString2 = "interned Baeldung";
		String newString = new String("interned Baeldung");
		
		System.out.println(constantString1==constantString2); //true
		System.out.println(constantString1==newString); //false
		
		String internedString = newString.intern();
		
		System.out.println(constantString1==internedString); //true
		System.out.println(newString==internedString); //false
		System.out.println(constantString1==newString); //false
		
        System.out.println(constantString1.equals(constantString2));//true
        System.out.println(constantString2.equals(newString)); //true
        System.out.println(newString.equals(internedString)); //true
        System.out.println(constantString1.equals(internedString)); //true
        System.out.println(constantString1.equals(newString)); //true
	}

	public static void main(String[] args) {
		StringsPrc su = new StringsPrc();
		su.stringEqualityandInterning();
	}

}
