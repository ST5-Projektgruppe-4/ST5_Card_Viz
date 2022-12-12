/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package journals;

import java.time.LocalDateTime;

/**
 *
 * @author jacob
 */
public class Journal{
    
     /**
     * Attributes of Event class
     */
    public int JournalID;
    public String CPR;
    public String Author;
    public String Profession;
    public String MedicalWard;
    public LocalDateTime DateTime;
    public String JournalNote;

    
    public Journal(int journalID, String author, String profession, String medicalWard, LocalDateTime dateTime, String journalNote) {
        this.JournalID = journalID;
        this.Author = author;
        this.Profession = profession;
        this.MedicalWard = medicalWard;
        this.DateTime = dateTime;
        this.JournalNote = journalNote;
    }

    
}
