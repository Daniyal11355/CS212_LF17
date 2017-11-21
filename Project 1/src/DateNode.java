


public class DateNode {
    protected Date212 data;
    protected DateNode next;


    public DateNode() {
        this.data = null;
        this.next = null;
    }

    public DateNode(Date212 data) {
        this.data = data;
        this.next = null;
    }

    public DateNode(Date212 data, DateNode next) {
        this.data = data;
        this.next = next;
    }
}
