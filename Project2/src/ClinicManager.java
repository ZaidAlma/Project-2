package src;

import java.io.File;
import java.util.Scanner;

public class ClinicManager  {
    public void run() {
        String userCommand = "";
        Scanner scan = new Scanner(System.in);
        List<Provider> providers = getProvider();
        if(providers == null){
            System.out.println("Failed to load the providers");
            return;
        }
        List<Technician> technicians = getTechicians(providers);
        //System.out.println("Before:");
        //printTechicians(technicians);
        //Sort.sortProviders(providers);
        printProviders(providers);
        technicians = reverseList(technicians);
        System.out.println("Rotation list for the technicians");
        //System.out.println("After:");
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
            //scheduleOfficeAppointment(commandInputs, officeAppointments);
        }
        else if(command[0].equals("T")){
            //scheduleImagingAppointment(commandInputs, imagingAppointments);
        }
        else if(command[0].equals("C")){
            //cancelAppointment(commandInputs, officeAppointments, imagingAppointments);
        }
        else if(command[0].equals("R")){
            //rescheduleAppointment(appointments);
        }
        else if(command[0].contains("P")){
            //printCommands(commandInputs[0], providers,appointments);
        }
        else{
            System.out.println("Invalid Command");
        }
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
        else if(commandInputs.length == 1 && commandInputs[0].length() < 1 || (commandInputs.length == 1 && commandInputs[0].length() > 2)){
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
            fileDirectory = System.getProperty("user.dir") + "\\src\\providers.txt";
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