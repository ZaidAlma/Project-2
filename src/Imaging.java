package src;
/**
 * This enum represents an imaging. It contains the date of the appointment, time of the appointment, the patient of the appointment, provider, and the room where the appointment is located at
 *
 *
 * @author Zaid Almadani
 */

import src.util.Date;

public class Imaging extends Appointment {
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room){
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    /**
     * Returns the room number of the appointment
     * @return room   the room number of the appointment
     */
    public Radiology getRoom(){
        return room;
    }
}
