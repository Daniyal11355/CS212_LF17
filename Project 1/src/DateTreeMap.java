
import java.util.Map;
import java.util.TreeMap;

public class DateTreeMap {
    private TreeMap<Integer, Date212> treeDate;
    private int treeLength;

    public DateTreeMap() {
        this.treeDate = new TreeMap<>();
        treeLength = 0;
    }

    @Override
    public String toString() {
        String fullDate = "";

        for(Map.Entry value: treeDate.entrySet()) {
            fullDate += value.getValue() + "\n";
        }

        return fullDate;
    }

    public String toStringSorted() {
        String fullDate = "";
        TreeMap<Integer, Date212> sortedTree = new TreeMap<>(this.treeDate);

        // need a way to sort the values


        for(Map.Entry value: sortedTree.entrySet()) {
            fullDate += value.getValue() + "\n";
        }

        return fullDate;
    }


    public void addDate(Date212 date){
        treeLength++;

        this.treeDate.put(treeLength, date);
    }

    public TreeMap<Integer, Date212> getTreeDate() {
        return treeDate;
    }
}
