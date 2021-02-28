
//^ LIBRARIES:
import java.util.*;

//^ CLASSES:
class GardenItemStore {
    //^ Fields:
    Map<String, ArrayList<String>> map;
    

    //^ Methods
    //$ Constructor that takes no arguments and initializes the map using a new
    public GardenItemStore() {
        map = new HashMap<String, ArrayList<String>>(); //* HashMap cannot have multiple values per key which means that an Array has to be used
    }
    
    //$ Checks whether the map contains a given string as a key
    public boolean containsKey(String key) {
        return (this.map.containsKey(key));
    }

    //£ Method that creates new mapping without replacing existing mapping
    public void put(String key, String plant) { 
        if (this.containsKey(key)) { // Check whether the key already exists
            map.get(key).add(plant); // Get the map first and then add element to array
        } else { 
            map.put(key, new ArrayList<String>()); // If key does not exit then create a new list map 
            map.get(key).add(plant); // Get the map first and then add element to array
        }
    }

    //$ Method that return a random value from a given key
    public String getRandomItem(String key) { //* Returns random plant by randomly selecting index
        //$ If key contains mapping then return random
        if (this.map.get(key) != null) {
            //£ Capitalize key 
            Random randomGenerator = new Random();
            ArrayList<String> array = this.map.get(key); // Returns that array of plants
            int randomIndex = randomGenerator.nextInt(array.size()); // Select random index between 0 and array size
            
            String capitalPlant =  array.get(randomIndex).substring(0, 1).toUpperCase() + array.get(randomIndex).substring(1); // Capitalize first letter: Return first character and capitalize then add rest of string except first index
            String capitalKey = key.substring(0, 1).toUpperCase() + key.substring(1);
            
            return (capitalKey + capitalPlant);
        } else {
            //$ If key does not contains mapping then return null 
            return (null);
        }       
    }

    //^ Tests
    public ArrayList<String> getValue(String key) {
        return (this.map.get(key));
        // System.out.println(this.map.keySet() + this.map.get(this.map.keySet()));
    }
    public void getSet() {
        System.out.println(this.map.keySet());
    }
}

public class Assignment {
    //^ Methods:
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
        System.out.println("Get Values 3:" + test.getValue("three"));System.out.println();

        System.out.println("Random Item 1:" + test.getRandomItem("one"));
        System.out.println("Random Item 2:" + test.getRandomItem("two"));
        System.out.println("Random Item 3:" + test.getRandomItem("three"));System.out.println();
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

    public static void main(String[] args) {
        myTest();
        // plantsTest();
    }   
}