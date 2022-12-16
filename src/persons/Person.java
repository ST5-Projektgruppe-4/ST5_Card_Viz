package persons;


/**
 * Simple example of a Person Class with first and last name
 */
public class Person {

    /**
     * Attributes of Event class
     */
    public String cpr;
    public String firstName;
    public String lastName;
    public int age;
    public String gender;
    public int weight;
    public int height;

    /**
     *
     *
     * /**
     * Constructor with 9 inputs
     *
     * @param CPR
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param weight
     * @param height
     *
     */
    public Person(String CPR, String firstName, String lastName, int age, String gender, int weight, int height) {
        this.cpr = CPR;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

}
