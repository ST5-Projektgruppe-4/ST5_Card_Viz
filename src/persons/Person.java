package persons;


/**
 * Simple example of a Person Class with first and last name
 */
public class Person {

    /**
     * Attributes of Event class
     */
    public String CPR;
    public String FirstName;
    public String LastName;
    public int Age;
    public String Gender;
    public int Weight;
    public int Height;

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
        this.CPR = CPR;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
        this.Gender = gender;
        this.Weight = weight;
        this.Height = height;
    }

}
