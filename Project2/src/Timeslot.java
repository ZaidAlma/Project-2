package src;
/**
 * This enum represents the available timeslots for appointments at the clinic.
 * Each timeslot is associated with a specific hour and minute of the day.
 * It provides methods to retrieve the hour and minute of each timeslot.
 *
 * @author Zaid Almadani
 */


public enum Timeslot{
    SLOT1 (9, 0), //9:30 am
    SLOT2 (10, 45), //10:45 am
    SLOT3 (11, 15), //11:15 am
    SLOT4 (13, 30), //1:30 pm
    SLOT5 (15, 0), //3:00 pm
    SLOT6 (16, 15); //4:15 pm


    private final int hour;
    private final int minute;

    Timeslot(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }


}