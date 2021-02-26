import java.util.*;

class GardenItemStore {
    Map<String, ArrayList<String>> map;
    ArrayList<String> plantArrayList = new ArrayList<String>();

    //$ Constructor with no arguments which initialized hash map
    public GardenItemStore() {
        map = new HashMap<String, ArrayList<String>>(); //* HashMap cannot have multiple values per key which means that an Array has to be used
    }

    //$ Method that checks contains whether it contains a key
    public boolean containsKey(String key) {
        return (this.map.containsKey(key));
    }

    //$ Method that creates new mapping without replacing existing mapping
    public void put(String key, String plant) {
        map.put(key, this.plantArrayList); // Create map with one key and empty array as list of plants
        map.get(key).add(plant); // Get the map first and then add element to array
        // System.out.println(this.map.get(key)); 
    }
    //$ Method that return a random value from a given key
    public String getRandomItem(String key) { //* Returns random plant by randomly selecting index
        Random randomGenerator = new Random();
        ArrayList<String> array = this.map.get(key); // Returns that array of plants
        int randomIndex = randomGenerator.nextInt(array.size()); // Select random index between 0 and array size
        return (array.get(randomIndex)); 
    }

    public void print(String key) {
        System.out.println(this.map.get(key));
    }
}

public class Assignment {
    public static void main(String[] args) {
        GardenItemStore foo = new GardenItemStore();
        foo.put("1", "plant");
        foo.put("1", "Test");
        foo.put("1", "Hello");
        foo.put("1", "World");
        foo.put("1", "Table");

        System.out.println(foo.containsKey("1"));
        System.out.println(foo.containsKey("3"));

        System.out.println(foo.getRandomItem("1"));

        foo.print("1");
    }   
}