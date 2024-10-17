package src;

/**
 * This class represents a profile of a patient, including their first name, last name, and date of birth.
 * It implements the Comparable interface to allow sorting of profiles by last name, first name, and date of birth.
 *
 * @author Zaid Almadani
 */

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile profile = (Profile) obj;
        return fname.equals(profile.fname) && lname.equals(profile.lname) && dob.equals(profile.dob);
    }

    @Override
    public int compareTo(Profile other) {
        int lastNameComparison = this.lname.compareTo(other.lname);
        if (lastNameComparison != 0) return lastNameComparison;
        int firstNameComparison = this.fname.compareTo(other.fname);
        if (firstNameComparison != 0) return firstNameComparison;
        return this.dob.compareTo(other.dob);
    }

    @Override
    public String toString() {
        return fname + " " + lname + " " + dob;
    }

    public String getLastName() {
        return lname;
    }

    public static void main(String[] args){
        //different first name
        Profile test1 = new Profile("his", "name", new Date(12,13, 1989));
        Profile test2 = new Profile("her", "name", new Date(12,13, 1989));
        System.out.println("should return a positive number: " + test1.compareTo(test2));

        //different last name
        Profile test3 = new Profile("his", "juice", new Date(12,13, 1989));
        Profile test4 = new Profile("his", "chicken", new Date(12,13, 1989));
        System.out.println(" should return a positive number: " + test3.compareTo(test4));

        //same name but different birthdays
        Profile test5 = new Profile("his", "name", new Date(12,13, 1989));
        Profile test6 = new Profile("his", "name", new Date(12,13, 2000));
        System.out.println("should return a negative number: " + test5.compareTo(test6));

        //same everything
        Profile test7 = new Profile("his", "name", new Date(12,13, 1989));
        Profile test8 = new Profile("his", "name", new Date(12,13, 1989));
        System.out.println("should return 0: " + test7.compareTo(test8));

        //Different Completely
        Profile test9 = new Profile("John", "Smith", new Date(12,13, 1989));
        Profile test10 = new Profile("Jane", "Doe", new Date(10,25, 1980));
        System.out.println("should return positive number: " + test9.compareTo(test10));

        //Case Sensitivity
        Profile test11 = new Profile("John", "Doe", new Date(12,13, 1989));
        Profile test12 = new Profile("john", "doe", new Date(10,25, 1980));
        System.out.println("should be j > J, so a negative number: " + test11.compareTo(test12));

        //Different Completely
        Profile test13 = new Profile("Jackie", "Chan", new Date(4,17, 1954));
        Profile test14 = new Profile("Will", "Smith", new Date(9,25, 1968));
        System.out.println("should return a negative number: " + test13.compareTo(test14));

    }

}