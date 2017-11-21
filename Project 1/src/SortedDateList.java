
/**
 *  A DateList class that adds new data in ascending order
 */

public class SortedDateList extends DateList {

    public SortedDateList() {
    }

    /**
     * Add method that checks for location to add new data
     *
     * @param data new data to be added to list
     * @param node DateList before the new data is added
     */
    public void add(Date212 data, SortedDateList node) {

        DateNode compareNode = node.first;
        DateNode placeHolder;

        // while loop runs until new data is added to the list
        while (compareNode.next != null) {

            // check if new data is smaller then next data on the list
            // if its smaller add it before the next data
            if (compareNode.next.data.compareTo(data) > 0) {
                placeHolder = new DateNode(compareNode.next.data, compareNode.next.next);
                compareNode.next.data = data;
                compareNode.next.next = placeHolder;
                this.length++;
                break;
            }

            // shift linked list
            compareNode = compareNode.next;

            // if end of linked list add new data to list
            if (compareNode.next == null) {
                compareNode.next = new DateNode(data);
                this.length++;
                break;
            }
        }

        // if the linked list is empty add date as fist item
        if (node.first.next == null) {
            node.first.next = new DateNode(data);
            this.length++;
        }
    }
}