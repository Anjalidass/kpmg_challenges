Challenge 3: We have a nested object. We would like a function where you pass in the object and a key and get back the value.

Ans:code.java

import java.util.Map;
public class NestedObject 
{
    public static Object getValue(Map<String, Object> object, String key) 
   {
       String[] keys = key.split("/");
       Map<String, Object> currentObject = object;
       for (String k : keys)
       {
            if (currentObject.containsKey(k) && currentObject.get(k) instanceof Map)
	    {
                currentObject = (Map<String, Object>) currentObject.get(k);
            } 
	    else
	    {
                return null;
            }
        }
        return currentObject;
    }
    public static void main(String[] args) {
        // Eg: 1
        Map<String, Object> object1 = Map.of("a", Map.of("b", Map.of("c", "d")));
        String key1 = "a/b/c";
        Object value1 = getValue(object1, key1);
        System.out.println("Example 1: Value for key '" + key1 + "' is '" + value1 + "'");

        // Eg: 2
        Map<String, Object> object2 = Map.of("x", Map.of("y", Map.of("z", "a")));
        String key2 = "x/y/z";
        Object value2 = getValue(object2, key2);
        System.out.println("Example 2: Value for key '" + key2 + "' is '" + value2 + "'");
    }
}
            
        

