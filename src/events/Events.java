package events;

import java.time.LocalDateTime;

/**
 * Simple example of a Person Class with first and last name
 */
public class Events {

    public int eventID;
    public String cpr;
    public LocalDateTime dateTime;
    public String CHA2DS2VASc;
    public String HASBLED;
    public String symptoms;
    public String medicine;
    public String cardioversions;
    public String diagnosis;
    public String vitalSigns;

    /**
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
        this.eventID = eventID;
        this.dateTime = dateTime;
        this.CHA2DS2VASc = CHA2DS2VASc;
        this.HASBLED = HASBLED;
        this.symptoms = symptoms;
        this.medicine = medicine;
        this.cardioversions = cardioversions;
        this.diagnosis = diagnosis;
        this.vitalSigns = vitalSigns;

    }

}
