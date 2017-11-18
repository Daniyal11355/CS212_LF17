public abstract class DateList {
    protected DateNode first;
    protected DateNode last;
    protected int length;

    public DateList() {
        DateNode node = new DateNode();
        this.first = node;
        this.last = node;
        this.length = 0;
    }

    public void append(Date212 data) {
        DateNode node = new DateNode(data);
        this.last.next = node;
        this.last = node;
        this.length++;
    }

    public DateListIterator reset() {
        return (new DateListIterator(this.first.next));
    }
}
