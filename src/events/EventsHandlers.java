package events;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.Queryable;
/**
 * Class for handeling the persons.
 */
public class EventsHandlers implements Queryable {

    /**
     * ArrayList with Person objects
     */
    public ArrayList<Events> EventList = new ArrayList<>();

    /**
     * Method for adding a person to the list of persons.
     *
     * @param p The Person to add
     */
    public void addEventToList(Events p) {
        this.EventList.add(p);
    }

    /**
     * Get the list of all persons
     *
     * @return The list with person objects
     */
    public ArrayList<Events> getEventList() {
        return this.EventList;
    }

    /**
     * Method for processing the ResultSet. This will add Person-objects to the
     * list of persons
     */
    @Override
    public void processResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            this.addEventToList(new Events(rs.getInt("EventID"), rs.getTimestamp("DateTime").toLocalDateTime(), rs.getString("CHA2DS2VASc"), rs.getString("HASBLED"), rs.getString("Symptoms"), rs.getString("Medicine"), rs.getString("Cardioversion"), rs.getString("Diagnosis"), rs.getString("VitalSigns")));
        }
    }

    /**
     * Return SQL Query as String for selecting all Persons in the Database Are
     * used in the databaseManipulator.
     */
    @Override
    public String returnSqlQuery() {
        String sqlStatement = "SELECT EventID, DateTime, CHA2DS2VASc, HASBLED, Symptoms, Medicine, Cardioversion, Diagnosis, VitalSigns FROM AddOnEvents";
        return sqlStatement;
    }


}
