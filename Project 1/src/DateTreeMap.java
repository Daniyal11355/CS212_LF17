import java.util.Map;
import java.util.TreeMap;

public class DateTreeMap {
    private TreeMap<Integer, Date212> treeDate;
    private int treeLength;

    public DateTreeMap() {
        this.treeDate = new TreeMap<>();
        treeLength = 0;
    }

    public DateTreeMap(TreeMap<Integer, Date212> treeDate) {
        this.treeDate = treeDate;
    }

    @Override
    public String toString() {
        String fullDate = "";

        for(Map.Entry e: treeDate.entrySet()) {
            fullDate += e.getValue() + "\n";
        }

        return fullDate;
    }

    public String toStringSorted(TreeMap<Integer, Date212> treeDate) {
        String fullDate = "";
        DateTreeMap sortedTree = new DateTreeMap(treeDate);


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
