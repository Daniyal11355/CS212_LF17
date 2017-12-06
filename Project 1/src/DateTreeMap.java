
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * TreeMap method stores Date212 as value and item # in key
 * has a toString() returns a string of unsorted values
 * has a toStringSorted() returns a string of sorted values
 *
 *
 */

public class DateTreeMap {
    private TreeMap<Integer, Date212> treeDate;
    private int treeLength;

    public DateTreeMap() {
        this.treeDate = new TreeMap<>();
        this.treeLength = 0;
    }

    /**
     * takes each value and creates a single string for display
     *
     * @return String of entire TreeMap values
     */
    @Override
    public String toString() {
        String fullDate = "";

        for(Map.Entry value: treeDate.entrySet()) {
            fullDate += value.getValue() + "\n";
        }

        return fullDate;
    }

    /**
     * takes each value and creates a single string for display
     *
     * @return String of entire TreeMap values sorted
     */
    public String toStringSorted() {
        String fullDate = "";
        Map sortedMap = sortByValues(this.treeDate);

        // Get a set of the entries on the sorted map
        Set set = sortedMap.entrySet();
        // Get an iterator
        Iterator i = set.iterator();
        // Display elements
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            fullDate += me.getValue() + "\n";
        }
        return fullDate;
    }


    /**
     * sorts a map by comparing values using Comparator
     *
     *
     * @param map this.treeDate
     * @param <K> Integer
     * @param <V> Date212
     * @return sorted Map by value
     */
    public <K, V extends Comparable<V>> Map<K, V> sortByValues(Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k1).compareTo(map.get(k2));
                if (compare == 0)
                    return 1;
                else
                    return compare;
            }
        };

        // creates a map of sorted values
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }


    /**
     * updates the value and the length
     *
     * @param date value to be added to TreeMap
     */
    public void addDate(Date212 date){
        this.treeLength++;
        this.treeDate.put(treeLength, date);
    }
}