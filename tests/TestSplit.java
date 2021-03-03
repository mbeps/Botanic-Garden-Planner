package tests;

import java.util.*;
import java.io.*;

public class TestSplit {
    public static void splitEfficient() throws NumberFormatException, IOException {
        //£ Work out the final space
        // String word = "Saucer Magnolia 8m";

        try (BufferedReader inputStream = new BufferedReader(new FileReader(new File("trees.txt")))) { //! May throw exception
			String word;
			while ((word = inputStream.readLine()) != null) {// Checks each line. Loops until the line is empty
				//^ Extract name except for number
                System.out.println(word.substring(0, word.lastIndexOf(" ")));
        
                //^ Extract Integer from Line
                String numberString = (word.substring(word.lastIndexOf(" ") + 1, word.length() - 1)); // Extract number from final space to -1 final character
                int number = Integer.parseInt(numberString); //! Throw NumberFormatException
                System.out.println(number);
			}
		}
        
        // //^ Extract name except for number
        // System.out.println(word.substring(0, word.lastIndexOf(" ")));
        
        // //^ Extract Integer from Line
        // String numberString = (word.substring(word.lastIndexOf(" ") + 1, word.length() - 1)); // Extract number from final space to -1 final character
        // int number = Integer.parseInt(numberString); //! Throw NumberFormatException
        // System.out.println(number);
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        splitEfficient();
    }
}
