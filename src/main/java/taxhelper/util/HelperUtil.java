package taxhelper.util;

import java.util.*;

public class HelperUtil {

    //https://www.javacodeexamples.com/java-hashmap-sort-by-values-example/487
    public static <K extends Comparable, V extends Comparable> Map<K, V> getSortedMapByValues(final Map<K, V> map){

        Map<K, V> mapSortedByValues = new LinkedHashMap<K, V>();

        //get all the entries from the original map and put it in a List
        List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());

        //sort the entries based on the value by custom Comparator
        Collections.sort(list, new Comparator<Map.Entry<K, V>>(){

            public int compare(Map.Entry<K, V> entry1, Map.Entry<K, V> entry2) {
                return entry1.getValue().compareTo( entry2.getValue() );
            }

        });

        //put all sorted entries in LinkedHashMap
        for( Map.Entry<K, V> entry : list  )
            mapSortedByValues.put(entry.getKey(), entry.getValue());

        //return Map sorted by values
        return mapSortedByValues;
    }

}
