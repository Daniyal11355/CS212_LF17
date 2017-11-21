
/**
 *
 * Abstract method to create generic linked list of Data212
 */
public abstract class DateList {
    protected DateNode first;
    protected DateNode last;
    protected int length;

    /**
     * Initialize an empty linked list
     */
    public DateList() {
        DateNode node = new DateNode();
        this.first = node;
        this.last = node;
        this.length = 0;
    }

    /**
     * Initialize a linked list with a first DateNode
     *
     * @param data
     */
    public void append(Date212 data) {
        DateNode node = new DateNode(data);
        this.last.next = node;
        this.last = node;
        this.length++;
    }

    /**
     * toString method using StringBuilder
     *
     * @return a single String of the entire linked list
     */
    @Override
    public String toString() {
        DateNode node = this.first;
        StringBuilder stringList = new StringBuilder();
        while(node.next != null) {
            stringList.append(String.format("%s%n", node.next.data));
            node = node.next;
        }
        return stringList.toString();
    }
}
