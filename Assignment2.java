import java.util.*;

class GardenItemStore {
    public GardenItemStore() {
        //$ takes no arguments and initiates the map using new hash-map
        Map<String, String> map = new HashMap<String, String>();
    }

    public boolean containsKey(String key) {
        //$ checks whether the map contains a given string as a key
        return (this.containsKey(key));
    }

    public void put(String key, String plant) {
        //$ creates a new mapping from key to plant 
        this.put(key, new ArrayList<String>());  //! Error StackOverflowError
        //? Maybe there is a recursive call taking place instead of calling the method from HashMap class
    }

    public String getRandomItem(String key) {
        //£ looks up key in the map and returns a random plant from the list of strings that key is mapped to
        return null;
    }
}

public class Assignment2 {
    public static void main(String[] args) {
        GardenItemStore gis0 = new GardenItemStore();
        gis0.put("a", "azalea");
    }
}