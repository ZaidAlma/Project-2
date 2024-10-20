package src;
/**
 * This enum represents the available timeslots for appointments at the clinic.
 * Each timeslot is associated with a specific hour and minute of the day.
 * It provides methods to retrieve the hour and minute of each timeslot.
 *
 * @author Zaid Almadani
 */


public class Timeslot implements Comparable<Timeslot>{
<<<<<<< HEAD
=======
    /*
    SLOT1 (9, 0), //9:30 am
    SLOT2 (10, 45), //10:45 am
    SLOT3 (11, 15), //11:15 am
    SLOT4 (13, 30), //1:30 pm
    SLOT5 (15, 0), //3:00 pm
    SLOT6 (16, 15); //4:15 pm

     */


>>>>>>> e00f3610221ad437627ee028644d315e27ff524a
    private final int hour;
    private final int minute;

    // Predefined array of timeslots, based on the given schedule
    private static final Timeslot[] timeslots = {
            new Timeslot(9, 0),   // Timeslot 1: 9:00 AM
            new Timeslot(9, 30),  // Timeslot 2: 9:30 AM
            new Timeslot(10, 0),  // Timeslot 3: 10:00 AM
            new Timeslot(10, 30), // Timeslot 4: 10:30 AM
            new Timeslot(11, 0),  // Timeslot 5: 11:00 AM
            new Timeslot(11, 30), // Timeslot 6: 11:30 AM
            new Timeslot(14, 0),  // Timeslot 7: 2:00 PM
            new Timeslot(14, 30), // Timeslot 8: 2:30 PM
            new Timeslot(15, 0),  // Timeslot 9: 3:00 PM
            new Timeslot(15, 30), // Timeslot 10: 3:30 PM
            new Timeslot(16, 0),  // Timeslot 11: 4:00 PM
            new Timeslot(16, 30)  // Timeslot 12: 4:30 PM
    };


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
    @Override
    public int compareTo(Timeslot otherTimeSlot){
        if(hour < otherTimeSlot.getHour()){
            return -1;
        }
        else if(hour > otherTimeSlot.getHour()){
            return 1;
        }
        else if(hour == otherTimeSlot.getHour()){
            if(minute == otherTimeSlot.getMinute()){
                return 0;
            }
            else if(minute < otherTimeSlot.getMinute()){
                return -1;
            }
            else if(minute > otherTimeSlot.getMinute()){
                return 1;
            }
        }
        return -5;
    }
    @Override
<<<<<<< HEAD
    public boolean equals(Object obj){
        if(obj instanceof Timeslot){
            if(this.compareTo((Timeslot)obj) == 0){
                return true;
            }
        }
=======
    public boolean equals(Timeslot timeslot){
>>>>>>> e00f3610221ad437627ee028644d315e27ff524a
        return false;
    }
    @Override
    public String toString(){
        return hour +":" +minute;
    }
<<<<<<< HEAD

    public static Timeslot getTimeslots(int timeslotNumber) {
        return timeslots[timeslotNumber - 1];
    }

=======
>>>>>>> e00f3610221ad437627ee028644d315e27ff524a
}