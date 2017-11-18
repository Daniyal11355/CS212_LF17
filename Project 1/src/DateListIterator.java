public class DateListIterator {

    private DateNode node;

    public DateListIterator(DateNode node) {
        this.node = node;
    }

    public boolean hasNext() {
        return (node != null);
    }

    public Date212 next() {
        if(node == null) {
            throw new NullPointerException("List is empty");
        }

        Date212 currentDate = node.data;
        node = node.next;
        return currentDate;


    }
}
