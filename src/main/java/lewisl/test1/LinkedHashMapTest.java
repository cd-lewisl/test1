package lewisl.test1;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(2);

        map.put("a", "a1");
        map.put("b", "a1");
        map.put("c", "a1");

        map.get("b");

        Iterator<Map.Entry<String, String>> entry = map.entrySet().iterator();
        entry.hasNext();
        System.out.println(entry.next().getKey());
        System.out.println(entry.next().getKey());
        System.out.println(entry.next().getKey());

        
        
        // System.out.println(map.entrySet().iterator().next().getKey());

        // System.out.println(map.keySet().toArray(new String[0])[0]);
        // System.out.println(map.keySet().toArray(new String[1])[1]);
        // System.out.println(map.keySet().toArray(new String[2])[2]);

    }
}
