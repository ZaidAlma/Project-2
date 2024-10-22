package src;

import src.util.Date;
import src.util.List;
import java.io.File;
import java.sql.Time;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class ClinicManager  {
    private final List<Appointment> officeAppointments;
    private final List<Appointment> imagingAppointments;
    private List<Technician> technicians;
    private int currentTechnicianIndex;

    public ClinicManager(){
        officeAppointments = new List<>();
        imagingAppointments = new List<>();
    }


    public void run() {
        String userCommand = "";
        Scanner scan = new Scanner(System.in);
        List<Provider> providersList = getProvider();

        assert providersList != null;
        technicians = getTechicians(providersList);
        currentTechnicianIndex = 0;
        if(providersList == null){
            System.out.println("Failed to load the providers");
            return;
        }
        List<Technician> technicians = getTechicians(providersList);
        printProviders(providersList);
        technicians = reverseList(technicians);
        System.out.println("Rotation list for the technicians");
        printTechicians(technicians);
        System.out.println("Clinic Manager is running.");
        while(true){
            System.out.println("Please enter a command:");
            userCommand = scan.nextLine();
            if(userCommand.contains(", ")){
                System.out.println("Invalid Command");
                continue;
            }
            if(userCommand.equals("Q"))
                break;

            runCommand(userCommand.split(","));
        }
        System.out.println("Clinic Manager Terminated");
    }
    private void runCommand(String[] command){
        checkCommand(command);
        if(command[0].equals("D")){
            scheduleOfficeAppointment(command);
        }
        else if(command[0].equals("T")){
            scheduleImagingAppointment(command);
        }
        else if(command[0].equals("C")){
            cancelAppointment(command);
        }
        else if(command[0].equals("R")){
            rescheduleAppointment(command);
        }
        else if(command[0].length() == 2 &&command[0].contains("P")){
            //printCommands(commandInputs[0]);
        }
        else{
            System.out.println("Invalid Command");
        }
    }

    private void printCommands(String printCommand){
        if(printCommand.equals("PA")){

        }
        else if (printCommand.equals("PP")) {

        }
        else if (printCommand.equals("PL")){

        }
        else if(printCommand.equals("PS")){

        }
        //Office Appointments
        else if(printCommand.equals("PO")){

        }
        //Imaging Appointment
        else if(printCommand.equals("PI")){
            for(int i = 0; i < officeAppointments.size(); i++){
                System.out.println(officeAppointments.get(i));
            }
        }
        //Credit
        else if(printCommand.equals("PC")){

        }
    }

    private void scheduleOfficeAppointment(String[] commands){
        Date appointmentDate = analyzeDate(commands[1]);
        if(appointmentDate == null){
            System.out.println("appointmentDate is null");
            return;
        }

        Timeslot timeslot = analyzeTimeslot(commands[2]);
        if(timeslot == null){
            System.out.println("timeSlot is null");
            return;
        }

        Patient patient = analyzePatient(commands[3], commands[4], commands[5]);
        if (patient == null){
            System.out.println("patient is null");
            return;
        }

        String npi = commands[6];
        if(!npi.matches("\\d+")){
            System.out.println("Npi not working");
            return;
        }

        Provider provider = findProviderNPI(npi);
        if(!(provider instanceof Doctor)){
            System.out.println("Doctor not found with npi: " + npi);
            return;
        }

        Appointment appointment = new Appointment(appointmentDate, timeslot, patient, provider);
        if(officeAppointments.contains(appointment)){
            System.out.println("Appointment with patient already exists");
            return;
        }

        officeAppointments.add(appointment);
        System.out.println("New Appointment Created!");
    }

    private void scheduleImagingAppointment(String[] commands){
        Date appointmentDate = analyzeDate(commands[1]);
        if(appointmentDate == null){
            System.out.println("appointmentDate is null");
            return;
        }

        Timeslot timeslot = analyzeTimeslot(commands[2]);
        if(timeslot == null){
            System.out.println("timeSlot is null");
            return;
        }

        Patient patient = analyzePatient(commands[3], commands[4], commands[5]);
        if (patient == null){
            System.out.println("patient is null");
            return;
        }

        Radiology radiologyRoom = Radiology.getImagingService(commands[6]);
        if (radiologyRoom == null){
            System.out.println("Invalid Radiology Room");
            return;
        }

        Technician technician = getAvailableTechnician(appointmentDate, timeslot, radiologyRoom);
        if(technician == null){
            System.out.println("No Technician Available");
            return;
        }

        Imaging appointment = new Imaging(appointmentDate, timeslot, patient, technician, radiologyRoom);
        if(imagingAppointments.contains(appointment)){
            System.out.println("Imaging Appointment with patient already exists");
            return;
        }

        imagingAppointments.add(appointment);
        System.out.println("New Imaging Appointment Created!");


    }

    private void rescheduleAppointment(String[] commands){
        if(commands.length != 7){
            System.out.println("Too many/Too little Tokens");
            return;
        }
        try{
            Date appointmentDate = analyzeDate(commands[1]);
            Timeslot anotherTimeslot = analyzeTimeslot(commands[2]);
            Patient patient = analyzePatient(commands[3], commands[4], commands[5]);
            Timeslot newTimeslot = analyzeTimeslot(commands[6]);
            if(newTimeslot == null){
                System.out.println("Invalid new Timeslot");
                return;
            }

            Appointment appointment = new Appointment(appointmentDate, anotherTimeslot, patient, null);
            Appointment existingAppointment = locateAppointment(officeAppointments, appointment);

            //we're doing this method because idk why the first if statement is not working in the != null isn't working
            if(existingAppointment == null){
                appointment = new Appointment(appointmentDate, newTimeslot, patient, null);
                existingAppointment = locateAppointment(officeAppointments, appointment);
            }
            if (existingAppointment != null) {
                // Check if the appointment is already at the new timeslot
                if (existingAppointment.getTimeSlot().equals(newTimeslot)) {
                    System.out.println("Appointment Was Already Rescheduled To This Timeslot");
                } else if (availableProvider(existingAppointment.getProvider(), appointmentDate, newTimeslot, officeAppointments)) {
                    // Reschedule to the new timeslot
                    existingAppointment.setTimesSlot(newTimeslot);
                    System.out.println("Appointment Successfully Rescheduled!");
                } else {
                    System.out.println("Unable To Reschedule With Provider");
                }
            } else {
                System.out.println("No Original Appointment Was Found; Unable To Reschedule");
            }
        } catch (Exception e){
            System.out.println("Invalid Cancel Command");
        }
    }

    private static <E> void selectionSort(List<E> list, Comparator<E> comparator){
        for(int i = 1; i <list.size(); i++){
            E key = list.get(i);
            int j = i - 1;
            while (j>=0 && comparator.compare(list.get(j), key) > 0){
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    private Appointment locateAppointment(List<Appointment> appointmentsList, Appointment temp){
        for(int i = 0; i < appointmentsList.size(); i++){
            Appointment appointment = appointmentsList.get(i);
            if(appointment.equals(temp)){
                return appointment;
            }
        }
        return null;
    }

    private boolean availableProvider(Person person, Date date, Timeslot timeslot, List<Appointment> appointmentsList){
        for(int i = 0; i < appointmentsList.size(); i++){
            Appointment appointment = appointmentsList.get(i);
            if(appointment.getDate().equals(date) && appointment.getTimeSlot().equals(timeslot) && appointment.getProvider().equals(person)){
                return false;
            }
        }
        return true;
    }

    private void cancelAppointment(String[] commands){
        if(commands.length != 6){
            System.out.println("Too many/Too little Tokens");
            return;
        }
        try{
            Date appointmentDate = analyzeDate(commands[1]);
            Timeslot timeslot = analyzeTimeslot(commands[2]);
            Patient patient = analyzePatient(commands[3], commands[4], commands[5]);
            Appointment appointment = new Appointment(appointmentDate, timeslot, patient, null);
            boolean removeOffice = officeAppointments.remove(appointment);
            boolean removeImaging = imagingAppointments.remove(appointment);

            if(removeOffice || removeImaging){
                System.out.println("Appointment Successfully Removed!");
            } else{
                System.out.println("Appointment Doesn't Exist");
            }
        } catch (Exception e){
            System.out.println("Invalid Cancel Command");
        }
    }


    private Technician getAvailableTechnician(Date date, Timeslot timeslot, Radiology radiology){
        if(technicians == null || technicians.size() == 0){ //just checking to make sure
            System.out.println("No available Technician");
            return null;
        }
        int technicianListSize = technicians.size();
        for(int i = 0; i < technicianListSize; i++){
            Technician technician = technicians.get(currentTechnicianIndex);
            if(availableTechnician(technician, date, timeslot)){
                if(availableRoom(technician.getLocation(), radiology, date, timeslot)){
                    currentTechnicianIndex = (currentTechnicianIndex + 1) % technicianListSize;
                    return technician;
                }
            }
            currentTechnicianIndex = (currentTechnicianIndex + 1) % technicianListSize;
        }
        System.out.println("No available Technician");
        return null;
    }

    private boolean availableTechnician(Technician technician, Date date, Timeslot timeslot){
        for(int i = 0; i < imagingAppointments.size(); i++){
            Imaging appointment = (Imaging) imagingAppointments.get(i);
            if(appointment.getDate().equals(date) && appointment.getTimeSlot().equals(timeslot) && appointment.getProvider().equals(technician)){
                return false;
            }
        }
        return true;
    }

    private boolean availableRoom(Location location, Radiology radiology, Date date, Timeslot timeslot){
        for(int i = 0; i < imagingAppointments.size(); i++){
            Imaging appointment = (Imaging) imagingAppointments.get(i);
            if(appointment.getDate().equals(date) && ((Provider) appointment.getProvider()).getLocation().equals(location) && appointment.getRoom().equals(radiology)){
                return false;
            }
        }
        return true;
    }

    private Date analyzeDate(String string){
        String[] date = string.split("/");
        if(date.length != 3){
            System.out.println("Invalid Date Format");
            return null;
        }
        Date date1 = new Date(date[0], date[1],date[2]);
        if(!date1.isValid()){
            System.out.println("Invalid Date");
            return null;
        }
        return date1;
    }

    private Timeslot analyzeTimeslot(String string){
        int checkTimeSlot;
        try{
            checkTimeSlot = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            System.out.println("Not Numeric");
            throw new NumberFormatException();

        }
        Timeslot timeslot = Timeslot.getTimeslots(checkTimeSlot);
        if(timeslot == null){
            System.out.println("Invalid timeslot");
        }
        return timeslot;
    }

    private Patient analyzePatient(String firstName, String lastName, String string){
        Date dob = analyzeDate(string);
        if(dob == null || !dob.isValid()){
            System.out.println("Invalid DOB");
            return null;
        }
        Profile profile = new Profile(firstName, lastName, dob);
        Patient patientProfile = new Patient(profile);
        return patientProfile;
    }

    private Provider findProviderNPI(String npi){
        List<Provider> providersList = getProvider();
        for(int i = 0; i < providersList.size(); i++){
            if(providersList.get(i) instanceof Doctor){
                Doctor doctor = (Doctor) providersList.get(i);
                if(doctor.getNpi().equals(npi)){
                    return doctor;
                }
            }
        }
        return null;
    }
    private Date getDate(String dateString){
        return new Date(dateString.split("/")[0], dateString.split("/")[1], dateString.split("/")[2]);
    }



    private void checkCommand(String[] commandInputs){
        if(commandInputs.length == 0){
            System.out.println("Invalid Command");
            return;
        }
        else if(commandInputs.length == 1 && commandInputs[0].length() < 1 || (commandInputs.length == 1 && commandInputs[0].length() > 2)){
            System.out.println("Invalid Command");
            return;
        }
        else if(commandInputs.length == 1 && commandInputs[0].length() ==1 && !commandInputs[0].equals("Q")){
            System.out.println("Invalid Command");
            return;
        }
        else if( (commandInputs.length == 1 && commandInputs[0].length() == 2 && !commandInputs[0].equals("PA"))){
            System.out.println("Invalid Command");
            return;
        }
        else if(commandInputs[0].equals("D") && commandInputs.length != 7 || commandInputs.equals("T") && commandInputs.length != 7){
            System.out.println("Invalid Command");
            return;
        }
    }
    private boolean hasErrors(String[] commandInputs){
        if(commandInputs.length == 0){
            return true;
        }
        else if(commandInputs.length == 1 && commandInputs[0].isEmpty() || (commandInputs.length == 1 && commandInputs[0].length() > 2)){
            return true;
        }
        else if(commandInputs.length == 1 && commandInputs[0].length() ==1 && !commandInputs[0].equals("Q")){
            return true;
        }
        else if( (commandInputs.length == 1 && commandInputs[0].length() == 2 && !commandInputs[0].equals("PA"))){
            return true;
        }
        else if(commandInputs[0].equals("D") && commandInputs.length != 7 || commandInputs.equals("T") && commandInputs.length != 7){
            return true;
        }
        return false;
    }
    private List<Provider> getProvider(){
        List<Provider> providers = new List<>();
        String fileDirectory = "";
        System.out.println(System.getProperty("user.dir"));
        if(System.getProperty("os.name").equalsIgnoreCase("Mac OS X") || System.getProperty("os.name").equalsIgnoreCase("Linux")){
            fileDirectory = System.getProperty("user.dir")+"/src/providers.txt";
        }
        else if(System.getProperty("os.name").contains("Windows")){
            fileDirectory = System.getProperty("user.dir") + "\\Project2\\src\\providers.txt";
        }
        File textFile;
        Scanner fileReader = null;
        try{
            textFile = new File(fileDirectory);
            fileReader = new Scanner(textFile);
        }catch(Exception e){
            System.out.println("File not found or cannot be opened.");
            return null;
        }
        while(fileReader.hasNext()){
            String[] providersInfo = fileReader.nextLine().split("\\s+");
            if(providersInfo[0].equalsIgnoreCase("D")){
                providers.add(new Doctor(getProfile(providersInfo), getLocation(providersInfo), getSpecialty(providersInfo), providersInfo[6]));
            }
            else if(providersInfo[0].equalsIgnoreCase("T")){
                providers.add(new Technician(getProfile(providersInfo), getLocation(providersInfo), Integer.parseInt(providersInfo[providersInfo.length -1])));
            }
        }
        fileReader.close();
        return providers;
    }
    public List<Technician> getTechicians(List<Provider> providers){
        List<Technician> technicians = new List<Technician>();
        for(int i = 0; i < providers.size(); i++){
            if(providers.get(i) instanceof Technician){
                technicians.add((Technician) providers.get(i));
            }
        }
        return technicians;
    }
    public List<Technician> reverseList(List<Technician> technicians){
        List<Technician> reversedList = new List<>();
        for(int i = technicians.size() -1; i >= 0; i--){
            reversedList.add(technicians.get(i));
            //System.out.println(technicians.get(i));
        }
        return reversedList;
    }
    private void printProviders(List<Provider> providers){
        for(int i = 0; i < providers.size(); i++){
            System.out.println(providers.get(i));
        }
    }
    private void printTechicians(List<Technician> technicians){
        for(int i = 0; i < technicians.size(); i++){
            String fullName = technicians.get(i).getFullName();
            String townName = getTownName(technicians.get(i).getLocation());
            System.out.print(fullName + " (" +townName+")");
            if(i < technicians.size() -1){
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
    private String getTownName(Location location){
        if(location.getZipCode().equalsIgnoreCase("08807")){
            return "BRIDGEWATER";
        }
        else if(location.getZipCode().equalsIgnoreCase("08817")){
            return "EDISON";
        }
        else if(location.getZipCode().equalsIgnoreCase("08854")){
            return "PISCATAWAY";
        }
        else if(location.getZipCode().equalsIgnoreCase("08542")){
            return "PRINCETON";
        }
        else if(location.getZipCode().equalsIgnoreCase("07960")){
            return "MORRISTOWN";
        }
        else if(location.getZipCode().equalsIgnoreCase("07066")){
            return "CLARK";
        }
        return "";
    }
    private Profile getProfile(String[] providersInfo){
        return new Profile(providersInfo[1], providersInfo[2], getDOB(providersInfo));
    }

    private Date getDOB(String[] providersInfo){
        String[] dateOfBirthStrings = providersInfo[3].split("/");
        return new Date(dateOfBirthStrings[0], dateOfBirthStrings[1], dateOfBirthStrings[2]);
    }
    private Location getLocation(String[] providersInfo){
        if(providersInfo[4].equalsIgnoreCase("BRIDGEWATER")){
            return Location.BRIDGEWATER;
        }
        else if(providersInfo[4].equalsIgnoreCase("CLARK")){
            return Location.CLARK;
        }
        else if(providersInfo[4].equalsIgnoreCase("PRINCETON")){
            return Location.PRINCETON;
        }
        else if(providersInfo[4].equalsIgnoreCase("EDISON")){
            return Location.EDISON;
        }
        else if(providersInfo[4].equalsIgnoreCase("MORRISTOWN")){
            return Location.MORRISTOWN;
        }
        else if(providersInfo[4].equalsIgnoreCase("PISCATAWAY")){
            return Location.PISCATAWAY;
        }
        else{
            return null;
        }
    }
    private Specialty getSpecialty(String[] providersInfo){
        if(providersInfo[5].equalsIgnoreCase("FAMILY")){
            return Specialty.FAMILY;
        }
        else if(providersInfo[5].equalsIgnoreCase("ALLERGIST")){
            return Specialty.ALLERGIST;
        }
        else if(providersInfo[5].equalsIgnoreCase("PEDIATRICIAN")){
            return Specialty.PEDIATRICIAN;
        }
        else{
            return null;
        }
    }
}