package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;

public class ClinicManager  {

    public void run() {
        String userCommand = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Clinic Manager is running.");
        List<Provider> providers = getProvider();
        Sort.sortProviders(providers);
        printProviders(providers);
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
        List<Provider> providers = new List();
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
        return providers;
    }
    private void printProviders(List<Provider> providers){
        for(int i = 0; i < providers.size(); i++){
            System.out.println(providers.get(i));
        }
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