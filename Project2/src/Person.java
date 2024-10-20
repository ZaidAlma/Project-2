package src;

public class Person implements Comparable<Person>{
    protected Profile profile;

    public Person(Profile profile){
        this.profile = profile;
    }

    public String getFullName(){
        return profile.getFullName();
    }


    @Override
    public int compareTo(Person otherPerson) {
        return this.profile.compareTo(otherPerson.profile);
    }

    @Override
    public String toString() {
        return profile.toString();
    }

}
