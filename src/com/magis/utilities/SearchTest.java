package com.magis.utilities;

import java.util.ArrayList;
import java.util.List;

public class SearchTest {

	public static void main(String[] args) {
		System.out.println(getAlphaNumericString(1000));	
		List<String> liste = new ArrayList<String>();
		long start = System.currentTimeMillis();
		for (int i=0;i<10000;i++)
			liste.add("denenenenene");
		
		for (int i=0;i<10000;i++) {
			if (liste.get(i).indexOf("dddd")>-1) System.out.println("found:" + liste.get(i));
		}
		
		System.out.println("Completed:" + ((System.currentTimeMillis()-start)));
		liste.clear();
		start = System.currentTimeMillis();
		for (int i=0;i<10000;i++)
			liste.add(getAlphaNumericString(1000));
		
		for (int i=0;i<10000;i++) {
			if (liste.get(i).indexOf("dddd")>-1) System.out.println("found:" + liste.get(i));
		}
		
		System.out.println("Completed:" + ((System.currentTimeMillis()-start)));

		start = System.currentTimeMillis();
		
		String tmp ="";
		int size =0;
		String[] tmpList;
		int found = 0;
		for (int i=0;i<10000;i++) {
			tmp = getAlphaNumericString(20);
			tmpList = tmp.split(" ");
			for (int j=0;j<tmpList.length;j++)
			if (tmpList[j].length()>2 && liste.get(i).toLowerCase().indexOf(tmpList[j].toLowerCase())>-1) {
				found++;
				System.out.println(found + " found:" + liste.get(i));
			}
		}
		
		System.out.println("Completed:" + ((System.currentTimeMillis()-start)));

	}

	
    static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ "
                                    + " 0123456789 "
                                    + " abcdefghijklmnopqrstuvxyz "; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
}
