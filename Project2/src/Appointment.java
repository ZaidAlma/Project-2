package src;

import src.util.Date;

/**
 * This class represents an appointment, including the date, timeslot, patient profile, and provider.
 * It provides methods to retrieve appointment details, calculate the charge based on the provider's specialty,
 * and compare appointments by date, timeslot, and patient.
 *
 * @author Sydney Pacheco
 */


public class Appointment implements Comparable<Appointment> {
    protected Date date;
    protected Timeslot timeslot;
    protected Person patient;
    protected Person provider;;

    public Appointment(Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    public Person getPatient() {
        return patient;
    }

    public Date getDate() {
        return date;
    }

    public Person getProvider() {
        return provider;
    }

    public Timeslot getTimeSlot(){
        return timeslot;
    }

    public int getCharge() {
        if (provider instanceof Provider){
            return ((Provider)provider).rate();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return date.equals(that.date) && timeslot == that.timeslot && patient.equals(that.patient);
    }

    @Override
    public int compareTo(Appointment other) {
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) return dateComparison;
        int timeslotComparison = this.timeslot.compareTo(other.timeslot);
        if (timeslotComparison != 0) return timeslotComparison;
        return this.patient.compareTo(other.patient);
    }

    @Override
    public String toString() {
        return date.toString() + " " + timeslot + " " + patient + " [" + provider + "]";
    }
}