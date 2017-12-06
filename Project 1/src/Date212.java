
 /**
  *
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
  * implrements Comparable to overide compareTo to use in TreeMap
  * throws IllegalDate212Exception is value is not valid
  * @author  m.shamilov
  */


public class Date212 implements Comparable<Date212> {
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
      * takes a string and test for proper format and calendar date
      *
      * @param date string value of date "20010101"
      * @throws IllegalDate212Exception if date is not a valid argument
      */
    public Date212(String date) throws IllegalDate212Exception {

        if(isValidValue(date)) {
            this.setYear(Integer.parseInt( (date.substring(0, date.length()-4)) ));
            this.setMonth(Integer.parseInt( (date.substring(date.length()-4, date.length()-2)) ));
            this.setDay(Integer.parseInt( (date.substring(date.length()-2, date.length())) ));
        } else {
            throw new IllegalDate212Exception(date + " not valid");
        }
    }

     /**
      * tests given value to meet calendar specifications
      *
      * - minimum value can be five digits "10101" == jan 1, 1
      * - max value can be 8 digits "20010101" == jan 1, 2001
      * - year cannot be negative or 0
      *
      * - test for leap year
      * formula: year must divide by 4 evenly,
      *         if year is divisible by 100 it must also be divisible by 400
      *
      * @param str date from user of file
      * @return true id date is passes all checks
      */
    private boolean isValidValue(String str) {
        boolean value = false;
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // test for length and all pos int
        if (str.matches("^\\d{5,8}$")) {// regex == (str.length() < 9 && str.length() > 4) {
            // get day, month, year
            int d = Integer.parseInt((str.substring(str.length() - 2, str.length())));
            int m = Integer.parseInt((str.substring(str.length() - 4, str.length() - 2)));
            int y = Integer.parseInt((str.substring(0, str.length() - 4)));

            // leap years
            if ((y % 4 == 0) && (y % 100 != 0)) {
                months[1] = 29;
            }
            if ((y % 100 == 0) && (y % 400 == 0)) {
                months[1] = 29;
            } // leap years
            // check month for valid entry and year is not 0
            if ((y != 0) && (m > 0 && m <= months.length)) {
                // check day for valid entry
                if ((d > 0) && (d <= months[m - 1])) {
                    value = true;
                } // valid day
            } // valid month
        } // proper length
        return value;
    }

    /**
     *
     * @param date  compares to an instance of Date212
     * @return      - int for <, 0 for ==, + int for >
     */

    @Override
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
      * @return  formatted string 20171002 -> “October 2, 2017”
      */
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
