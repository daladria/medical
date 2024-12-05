package com.magis.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> liste = new ArrayList<String>();
		liste.add("veli");
		liste.add("kalem");
		liste.add("ali");
		liste.add("ADANA");
		liste.add("çankaya");
		liste.add("şişman");
		liste.add("Batı");
		liste.add("balık");
		liste.add("abdurrahman");
		liste.add("zeynep");
		
		for (int i=0;i<liste.size();i++)
			System.out.println(liste.get(i));
		Collections.sort(liste); 
		System.out.println("----------------------------------------");
		for (int i=0;i<liste.size();i++)
			System.out.println(liste.get(i));		
	
		Collections.sort(liste, new SortIgnoreCase()); 
		System.out.println("----------------------------------------");
		for (int i=0;i<liste.size();i++)
			System.out.println(liste.get(i));		

		
	}


}
