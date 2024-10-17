package src;

/**
 * This class represents a patient, including their profile and a linked list of completed visits.
 * It provides functionality to add new visits, calculate the total charge for all visits, and compare patients by last name.
 * The patient is compared based on their profile information.
 *
 * @author Sydney Pacheco
 */


public class Patient extends Person {
    protected Profile profile;
    private Visit visits; // a linked list of visits (completed appt.)

    public Patient(Profile profile) {
        super(profile);
//        this.profile = profile;
        this.visits = null;
    }

    public Profile getProfile() {
        return profile;
    }

    public void addVisit(Appointment appointment) {
        Visit newVisit = new Visit(appointment);
        if (visits == null) {
            visits = newVisit;
        } else {
            Visit current = visits;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newVisit);
        }
    }

    public int charge() { // traverse the linked list to compute the charge
        int totalCharge = 0;
        Visit current = visits;

        while (current != null) {
            totalCharge += current.getAppointment().getCharge();
            current = current.getNext();
        }

        return totalCharge;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient other = (Patient) obj;
        return profile.equals(other.profile);
    }

    @Override
    public String toString() {
        return "Patient: " + profile.toString();
    }

    @Override
    public int compareTo(Person other) {
        return this.profile.getLastName().compareTo(other.profile.getLastName());
    }
}