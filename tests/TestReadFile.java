package tests;

import java.io.*;
import java.util.*;

public class TestReadFile {
	public static void main(String[] args) throws Exception {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		try (BufferedReader inputStream = new BufferedReader(new FileReader(new File("test.txt")))) { //! May throw exception
			String word;
			while ((word = inputStream.readLine()) != null) {// Checks each line. Loops until the line is empty
				String firstCharacter = word.substring(0, 1);

				// str.add(word); // Adds each word to array
				//£ Call put method
				if (!map.containsKey(firstCharacter)) {
					map.put(firstCharacter, new ArrayList<String>()); // If key does not exit then create a new list map 
				}
				map.get(firstCharacter).add(word); // Get the map first and then add element to array

				System.out.println(map.get(firstCharacter));
			}
		}
	}
}