package src;

/**
 * This class represents a Date with a specific day, month, and year.
 * It provides functionality to check if the date is valid, including leap year calculations.
 * It also implements the Comparable interface to allow comparison of dates.
 *
 * @author Sydney Pacheco
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public boolean isValid() {
        if (year < 1) return false;

        if (month < 1 || month > 12) return false;

        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (isLeapYear(year)) {
            daysInMonth[2] = 29;
        }

        if (day < 1 || day > daysInMonth[month]) return false;

        return true;
    }

    private boolean isLeapYear(int year) {
        //years at end of century (ex: 1900) have to be divisible by 400
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    @Override
    public int compareTo(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }


    public static void main(String[] args){
        Date testDate0 = new Date(4,23,2023);
        System.out.println("valid day, should return True: " + testDate0.isValid());

        Date testDate1 = new Date(2,29,2024);
        System.out.println("Leap year, should return True: " + testDate1.isValid());

        Date testDate2 = new Date(2,29,2023);
        System.out.println("not a leap year, should return False: " + testDate2.isValid());

        Date testDate3 = new Date(3,42,2023);
        System.out.println("invalid day, should return False: " + testDate3.isValid());

        Date testDate4 = new Date(13,29,2023);
        System.out.println("invalid month, should return False: " + testDate4.isValid());

        Date testDate5 = new Date(2,15,2023);
        System.out.println("valid day for non-leap year, should return True: " + testDate5.isValid());


    }
}