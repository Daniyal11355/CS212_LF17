
 /**
  * NOTE:: all exceptions are handled before Date212 gets a value
  * <p>
  * Store the year, month and day as integers so you will need three
  * private instance variables the.
  * <p>
  * Constructor should be provided that takes a <code>String</code> representing the date in
  * yyyymmdd format, use the substring method of class <code>String</code> to pull out the
  * month, day and year, parse them as <code>int</code> put them in the instance variables.
  * <p>
  * Usual set and get methods, and equals, compareTo and toString methods <code>toString</code>
  * method should print the date in “mm dd, yyyy” format
  * (for example, 20171002 would be returned as “October 2, 2017”).
  *
  * @author  m.shamilov
  */


public class Date212 {
    /**
     * stores the parsed and separated year
     */
    private int year;
    /**
     * stores the parsed and separated month
     */
    private int month;
    /**
     * stores the parsed and separated day
     */
    private int day;

     /**
      *
      * @param date  separated into three int variables
      */
    public Date212(String date) {
        this.setYear(Integer.parseInt(date.substring(0, 4)));
        this.setMonth(Integer.parseInt(date.substring(4, 6)));
        this.setDay(Integer.parseInt(date.substring(6, 8)));
    }

     /**
      *
      * @param date  compares to an instance of Date212
      * @return      - int for <, 0 for ==, + int for >
      */
    public int compareTo(Date212 date) {
        int result;
        // test to see if dates != else return 0
        if (this.equals(date)) {
            return 0;
        }
        // test years are ==
        else {
            if (this.year == date.getYear()) {
                // test months are ==
                if (this.month == date.getMonth()) {
                    return this.day - date.getDay();
                } else {
                    return this.month - date.getMonth();
                } //  months ==
            } else {
                return this.year - date.getYear();
            }// year ==
        } // !equals
    }

     /**
      *
      * @param o  test to see if o is equal to self
      * @return   true is equal, otherwise false
      */
    // returns true if 2 Date212 are ==
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date212 date212 = (Date212) o;
        // returns true if all are ==
        return (year == date212.year &&
                month == date212.month &&
                day == date212.day);
    }

     /**
      * Takes the date int variables and formats them to <code>String</code>
      *
      * @return  a single String object
      */
    // returns formatted string 20171002 -> “October 2, 2017”
    @Override
    public String toString() {
        String[] months = new String[]{"January", "February", "March",
                            "April", "May", "June",
                            "July", "August", "September",
                            "October", "November", "December"};

        return String.format("%s %d, %d",
                months[this.getMonth() - 1],
                this.getDay(),
                this.getYear());
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
