package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;

public class ClinicManager  {

    public void run() {
        System.out.println("Clinic Manager is running.");
        List<Provider> providers = getProvider();
        Sort.sortProviders(providers);
    }

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






}
