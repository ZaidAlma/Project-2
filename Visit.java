package src;
/**
 * This class represents a visit made by a patient, containing an appointment and a reference to the next visit in a linked list.
 * It provides methods to access the appointment and navigate through the linked list of visits.
 *
 * @author Zaid Almadani
 */


public class Visit {
    private Appointment appointment;
    private Visit next;

    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Visit getNext() {
        return next;
    }

    public void setNext(Visit next) {
        this.next = next;
    }
}