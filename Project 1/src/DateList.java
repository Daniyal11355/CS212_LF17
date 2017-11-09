public abstract class DateList {
    protected DateNode first;
    protected DateNode last;
    protected int length;

    public DateList() {
        first = null;
        last = null;
        length = 0;
    }

    public void append(Date212 data) {
        DateNode node = new DateNode(data);
        last.next = node;
        last = node;
        length++;
    }
}
