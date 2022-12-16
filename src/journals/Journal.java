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
    public int journalID;
    public String cpr;
    public String author;
    public String profession;
    public String medicalWard;
    public LocalDateTime dateTime;
    public String journalNote;

    
    public Journal(int journalID, String author, String profession, String medicalWard, LocalDateTime dateTime, String journalNote) {
        this.journalID = journalID;
        this.author = author;
        this.profession = profession;
        this.medicalWard = medicalWard;
        this.dateTime = dateTime;
        this.journalNote = journalNote;
    }

    
}
