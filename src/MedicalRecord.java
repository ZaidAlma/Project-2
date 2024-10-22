package src;

/**
 * This class represents a medical record, which stores an array of patients.
 * It provides functionality to add patients to the record and retrieve patients by index.
 * The size of the medical record is tracked, and it cannot exceed the defined capacity.
 *
 * @author Sydney Pacheco
 */


public class MedicalRecord {
    private Patient[] patients;
    private int size;

    public MedicalRecord(int capacity) {
        patients = new Patient[capacity];
        size = 0;
    }

    public void add(Patient patient) {
        if (size < patients.length) {
            patients[size++] = patient;
        } else {
            System.out.println("Medical record is full. Cannot add more patients.");
        }
    }

    public int getSize() {
        return size;
    }

    public Patient getPatient(int index) {
        if (index >= 0 && index < size) {
            return patients[index];
        }
        return null;
    }
}