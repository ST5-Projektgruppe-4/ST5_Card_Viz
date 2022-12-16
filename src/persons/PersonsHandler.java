package persons;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.Queryable;

/**
 * Class for handeling the persons.
 */
public class PersonsHandler implements Queryable {

    /**
     * ArrayList with Person objects
     */
    private ArrayList<Person> personList = new ArrayList<>();

    /**
     * Method for adding a person to the list of persons.
     *
     * @param p The Person to add
     */
    public void addPersonToList(Person p) {
        this.personList.add(p);
    }

    /**
     * Get the list of all persons
     *
     * @return The list with person objects
     */
    public ArrayList<Person> getPersonList() {
        return this.personList;
    }

    /**
     * Method for processing the ResultSet. This will add Person-objects to the
     * list of persons
     */
    @Override
    public void processResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            this.addPersonToList(new Person(rs.getString("CPR"), rs.getString("FirstName"), rs.getString("LastName"), rs.getInt("Age"), rs.getString("Gender"), rs.getInt("Weight"), rs.getInt("Height")));
        }
    }

    /**
     * Return SQL Query as String for selecting all Persons in the Database Are
     * used in the databaseManipulator.
     */
    @Override
    public String returnSqlQuery() {
        String sqlStatement = "SELECT CPR, FirstName, LastName, Age, Gender, Weight, Height FROM AddOnPerson";
        return sqlStatement;
    }



}
