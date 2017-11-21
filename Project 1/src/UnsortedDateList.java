
/**
 *  A DateList class that adds new data to the end of the list
 */
public class UnsortedDateList extends DateList {

    public UnsortedDateList() {
    }

    /**
     * Adds data to end of the list
     *
     * @param date
     */
    public void add(Date212 date) {
        this.append(date);
    }
}
