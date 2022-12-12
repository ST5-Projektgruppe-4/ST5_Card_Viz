package events;

import java.time.LocalDateTime;

/**
 * Simple example of a Person Class with first and last name
 */
public class Events {

    /**
     * Attributes of Event class
     */
    public int EventID;
    public String CPR;
    public LocalDateTime DateTime;
    public String CHA2DS2VASc;
    public String HASBLED;
    public String Symptoms;
    public String Medicine;
    public String Cardioversions;
    public String Diagnosis;
    public String VitalSigns;

    /**
     *
     *
     * /**
     * Constructor with 9 inputs
     *
     * @param eventID
     * @param dateTime
     * @param CHA2DS2VASc
     * @param HASBLED
     * @param symptoms
     * @param medicine
     * @param cardioversions
     * @param diagnosis
     * @param vitalSigns
     *
     */
    public Events(int eventID, LocalDateTime dateTime, String CHA2DS2VASc, String HASBLED, String symptoms, String medicine, String cardioversions, String diagnosis, String vitalSigns) {
        this.EventID = eventID;
        this.DateTime = dateTime;
        this.CHA2DS2VASc = CHA2DS2VASc;
        this.HASBLED = HASBLED;
        this.Symptoms = symptoms;
        this.Medicine = medicine;
        this.Cardioversions = cardioversions;
        this.Diagnosis = diagnosis;
        this.VitalSigns = vitalSigns;

    }

}
