/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package journals;

import database.Queryable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jacob
 */
public class JournalHandler implements Queryable {
    /**
     * ArrayList with Person objects
     */
    public ArrayList<Journal> journalNotesList = new ArrayList<>();

    /**
     * Method for adding a person to the list of persons.
     *
     * @param p The Person to add
     */
    public void addJournalNoteToList(Journal p) {
        this.journalNotesList.add(p);
    }

    /**
     * Get the list of all persons
     *
     * @return The list with person objects
     */
    public ArrayList<Journal> getJournalNotesList() {
        return this.journalNotesList;
    }

    /**
     * Method for processing the ResultSet. This will add Person-objects to the
     * list of persons
     */
    @Override
    public void processResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            this.addJournalNoteToList(new Journal(rs.getInt("JournalID"), rs.getString("Author"),rs.getString("Profession"), rs.getString("MedicalWard"), rs.getTimestamp("DateTime").toLocalDateTime(), rs.getString("JournalNote")));
        }
    }

    /**
     * Return SQL Query as String for selecting all Persons in the Database Are
     * used in the databaseManipulator.
     */
    @Override
    public String returnSqlQuery() {
        String sqlStatement = "SELECT JournalID, Author, Profession, MedicalWard, DateTime, JournalNote FROM AddOnJournal";
        return sqlStatement;
    }

}
