package src;
import java.util.Scanner;
/**
 * This class represents the scheduler system for managing patient records at the clinic.
     * It provides an interactive command-line interface for adding patients and processing various commands.
 * The scheduler continuously runs until the "Q" command is entered to terminate the system.
 *
 * @author Zaid Almadani
 */



public class Scheduler {
    private MedicalRecord medicalRecord;

    public Scheduler() {
        medicalRecord = new MedicalRecord(100);
        System.out.println("Scheduler is running.");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                continue;
            }

            if (command.equalsIgnoreCase("Q")) {
                System.out.println("Scheduler terminated.");
                break;
            }

            processCommand(command);
        }

        scanner.close();
    }

    private void processCommand(String command) {
        if (command.startsWith("ADD PATIENT")) {
            String[] parts = command.split(" ");
            if (parts.length == 5) {
                String fname = parts[2];
                String lname = parts[3];
                Date dob = parseDate(parts[4]);
                if(dob == null){
                    System.out.println("Invalid Format");
                    return;
                }
                Profile profile = new Profile(fname, lname, dob);
                Patient patient = new Patient(profile);
                medicalRecord.add(patient);
                System.out.println("Patient added: " + patient);
            } else {
                System.out.println("Invalid command format. Use: ADD PATIENT <FirstName> <LastName> <DOB>");
            }
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    private Date parseDate(String dateString) {
        String[] parts = dateString.split("/");
        if(parts.length != 3){
            return null;
        }
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(month, day, year);
    }
}