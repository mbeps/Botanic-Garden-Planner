
//^ LIBRARIES:
import java.util.*;
import java.io.*;

//^ CLASSES:
class GardenItemStore {
    //^ Fields:
    private Map<String, ArrayList<String>> map;

    //^ Methods
    public GardenItemStore() {
        map = new HashMap<String, ArrayList<String>>(); //* HashMap cannot have multiple values per key which means that an Array has to be used
    }
    
    public GardenItemStore(String file) throws IOException { //& Dependencies: initializeMap
        // map = new HashMap<String, ArrayList<String>>();
        this.initializeMap();

        try (BufferedReader inputStream = new BufferedReader(new FileReader(new File("test.txt")))) { //! May throw exception
			String word;
			while ((word = inputStream.readLine()) != null) {// Checks each line. Loops until the line is empty
				String firstCharacterKey = word.substring(0, 1); // Extract the first character for each word to use as key for mapping
				this.put(firstCharacterKey.toLowerCase(), word.toLowerCase()); // Create new mapping
			}
		}
        /** Explanation
         * To add elements from file, buffered reader and file reader are used to read over the file efficiently
         * Use try with resources as BufferedReader may throw exception
         * While loop is used to iterate over the file line-by-line the line is null
         * The first character is extracted by getting character from index position 0 to 1
         * Call put method to add the key (first character) in lower case and the entire line also as lower case  
         */
    }

    public void initializeMap() {//& Dependencies: GardenItemStore
        map = new HashMap<String, ArrayList<String>>(); // Used for initializing hash map in sub-classes
        /** Explanation
         * Map field is not accessible to other sub-classes as it is private
         * Using the super-class constructor would allow for constricting sub-objects but cannot be used when using custom constructors or specific access
         */
    }
    
    public boolean containsKey(String key) { //& Dependencies: put
        return (this.map.containsKey(key));
    }

    public void put(String key, String plant) { //& Dependencies: getRandomItem
        if (!this.containsKey(key)) {
            map.put(key, new ArrayList<String>()); // If key does not exit then create a new list map 
        }
        map.get(key).add(plant); // Get the map first and then add element to array
        /**Explanation
         * Before adding new mapping, it has to be checked if it already exists by checking if the key is stored
         * Without checking, the mapping will be overwritten which means that all the mappings will be the same
         * If the mapping does not exists, then create a new mapping
         * Checking the absence of mapping is more efficient tan to check if it exits as if-else statement does not have to be used
         * Outside the if-statement is the point where a mapping exists (from before or new)
         * Getting the map will return an ArrayList, elements will be appended into the array  
         */
    }

    public String getRandomItem(String key) { //& Dependencies:  
        //* Returns random plant by randomly selecting index
        if (this.map.get(key) != null) {
            Random randomGenerator = new Random();
            ArrayList<String> array = this.map.get(key); // Returns that array of plants
            int randomIndex = randomGenerator.nextInt(array.size()); // Select random index between 0 and array size
            
            String capitalPlant =  array.get(randomIndex).substring(0, 1).toUpperCase() + array.get(randomIndex).substring(1); // Capitalize first letter: Return first character and capitalize then add rest of string except first index
            // String capitalKey = key.substring(0, 1).toUpperCase() + key.substring(1);
            
            return (capitalPlant);
        } else {
            return (null);
        }
        /** Explanation
         * Firstly, the mapping has to exist before returning elements from it. Therefore, if-statement is used to check whether the mapping is null
         * The mapping associated with key is returned (ArrayList type) and assigned to a local variable
         * A random number is generated from 0 to the length of the array
         * The random number will be used as index which will return a random element from array
         */ 
    }

    public ArrayList<String> getMappings(String key) {
        return (this.map.get(key)); // Returns mapping 
        /** Explanation
         * Returns mapping as sub-classes will not have access
         * Map field is private therefore not accessible
         */
    }

    //^ Tests
    public ArrayList<String> getValue(String key) {
        return (this.map.get(key));
    }

    public void getSet() {
        System.out.println(this.map.keySet());
    }
}

class OrchidStore extends GardenItemStore {
    public OrchidStore(String file) throws IOException {
        super(file); //! May throw exception
    }

    @Override
    public String getRandomItem(String key) {
        return (super.getRandomItem(key) + " (orchid)"); // Use the method from super and append
    }
}

class TreeStore extends GardenItemStore {
    public TreeStore(String file) throws IOException {
        super(file);
    }

    @Override
    public String getRandomItem(String key) { //& Dependencies: extractName, extractSize, height
        if (super.getMappings(key) != null) {
            Random randomGenerator = new Random();
            ArrayList<String> array = this.getMappings(key); // Returns that array of plants
            int randomIndex = randomGenerator.nextInt(array.size()); // Select random index between 0 and array size
            
            String extractedName = extractName(array.get(randomIndex)); // Returns the extracted name without plant height 
            int extractedHeight = extractSize(array.get(randomIndex));

            String capitalPlant =  array.get(randomIndex).substring(0, 1).toUpperCase() + extractedName.substring(1); // Capitalize first letter: Return first character and capitalize then add rest of string except first index.
            return (capitalPlant + height(extractedHeight));
        } else {
            return (null);
        }
        /** Explanation
         * Similar to random method of super-class
         * Items are stored as entire lines which means that their sizes are also stored
         * After random element is returned (same as super-class method), extracted names is called which will only display the plant names without the size
         * Height categories are added by extracting the height from element (as size was also saved) and passing it onto height method which will return message as string 
         */
    }

    public String extractName(String word) { //& Dependencies: getRandomItem
        return (word.substring(0, word.lastIndexOf(" ")));
        /** Explanation
         * Extracts name by returning portion of string
         * Index from 0 (start) to the last space are returned as everything after last space is the size 
         */
    }

    public int extractSize(String word) { //& Dependencies: getRandomItem
        String numberString = (word.substring(word.lastIndexOf(" ") + 1, word.length() - 1)); // Extract number from final space to -1 final character
        int number = Integer.parseInt(numberString); // Convert from String to int //! Throws NumberFormatException
        return (number);
        /**
         * Return string of number from the end of line
         * Index from the last space (everything before is plant name) to one less than the length so the 'm' is excluded
         * Convert string of number to int type
         */
    }

    public String height(int height) { //& Dependencies: getRandomItem
        if (height > 80) {
            return (" (very tall tree)");
        } else if (height > 15){
            return (" (tall tree)");
        } else {
            return (" (small tree)");
        }
    }
}


//^ TESTING:
class GardenItemStoreTest {
    public static void myTest() {
        GardenItemStore test = new GardenItemStore();
        
        test.put("one", "one1");
        test.put("one", "one2");
        test.put("one", "one3");
        test.put("one", "one4");
        test.put("two", "Two1");
        test.put("two", "two2");
        test.put("three", "Three");

        System.out.println("Contains 1: " + test.containsKey("one"));
        System.out.println("Contains 2: " + test.containsKey("two"));
        System.out.println("Contains 3: " + test.containsKey("three"));
        System.out.println("Contains 4: " + test.containsKey("four")); System.out.println();

        System.out.println("Get Values 1:" + test.getValue("one"));
        System.out.println("Get Values 2:" + test.getValue("two"));
        System.out.println("Get Values 3:" + test.getValue("three")); System.out.println();

        System.out.println("Random Item 1:" + test.getRandomItem("one"));
        System.out.println("Random Item 2:" + test.getRandomItem("two"));
        System.out.println("Random Item 3:" + test.getRandomItem("three")); System.out.println();
    }
    public static void myFileTest() throws IOException {
        GardenItemStore test = new GardenItemStore("test.txt");

        System.out.println("Contains L: " + test.containsKey("l"));
        System.out.println("Contains L: " + test.containsKey("d")); System.out.println();

        System.out.println("Get Values L:" + test.getValue("l"));
        System.out.println("Get Values d:" + test.getValue("d")); System.out.println();

        System.out.println("Random Item 2:" + test.getRandomItem("l"));
        System.out.println("Random Item 3:" + test.getRandomItem("d")); System.out.println();
    }
    public static void plantsTest() {
        GardenItemStore gis0 = new GardenItemStore();
        
        gis0.put("a", "azalea");
        gis0.put("bu", "burning bush");
        gis0.put("bu", "bursting heart");
        gis0.put("a", "amur chokecherry");

        System.out.println(gis0.containsKey("a"));

        System.out.println(gis0.getRandomItem("a"));
    }
}

public class Assignment {
    public static void main(String[] args) throws IOException {
        // myTest();
        GardenItemStoreTest.myFileTest();
        // plantsTest();
    }   
}