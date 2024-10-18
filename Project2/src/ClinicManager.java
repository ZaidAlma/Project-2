package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;

public class ClinicManager  {

    public void run() {
        System.out.println("Clinic Manager is running.");
        List<Provider> providers = getProvider();
        //Sort.sortProviders(providers);
        printProviders(providers);
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

    /*

    private Provider readProvider(String s){
        String[] string = s.split("\\s+");
        String firstName = string[0];
        String lastName = string[1];
        String DOB = string[2];
        String location = string[3];
        String specialty = string[4];
        String npi = string[5];

    }

        private List<Provider> getProviders() {
            List<Provider> providers1 = new List<>();
            List<Technician> rotationList = new List<>();
            try {
                File file = new File("providers.txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine().trim();
                    if (!nextLine.isEmpty()) {
                        Provider provider = readProvider(nextLine);
                        providers1.add(provider);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: providers.txt not found"); //We want this to make sure it's found
            }
            return providers1;
        }
*/
}
