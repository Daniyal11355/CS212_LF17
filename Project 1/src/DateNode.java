
/**
 *
 * Creates a single node for the linked list
 */
public class DateNode {
    protected Date212 data;
    protected DateNode next;

    /**
     * Initialize an empty node
     */
    public DateNode() {
        this.data = null;
        this.next = null;
    }

    /**
     * Initialize a node the first data field
     *
     * @param data
     */
    public DateNode(Date212 data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Initialize a node with fist and next data fields
     *
     * @param data
     * @param next
     */
    public DateNode(Date212 data, DateNode next) {
        this.data = data;
        this.next = next;
    }
}
