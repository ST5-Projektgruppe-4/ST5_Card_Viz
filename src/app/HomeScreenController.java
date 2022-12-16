package app;

import database.DatabaseManipulator;
import events.Events;
import events.EventsHandlers;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.HOURS;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import journals.Journal;
import journals.JournalHandler;
import persons.Person;
import persons.PersonsHandler;

/**
 * FXML Controller class
 *
 * @author TheTeam
 */
public class HomeScreenController implements Initializable {

    // empty eventhandler that are fill with data from the database
    EventsHandlers eh = new EventsHandlers();
    JournalHandler jh = new JournalHandler();
    PersonsHandler ph = new PersonsHandler();

    // Variables for controlling events in loops
    private int saveEventreferenceID;
    private int saveidentifier;

    // Vaariable for controlling of events is selected
    boolean ispressed = false;

    // Flags for the filters
    private boolean chaFilterOn = false;
    private boolean hasFilterOn = false;
    private boolean symFilterOn = false;
    private boolean medFilterOn = false;
    private boolean dcFilterOn = false;
    private boolean diaFilterOn = false;
    private boolean visFilterOn = false;

    // Flag for overlay
    boolean overlay = false;

    // flag for checing of
    boolean identifierContainsData = false;

    // Flag for colorshiftinh in dynamic box
    boolean informationTableColorShifter = true;

    // Sets the timeline view default to 24 hours
    private int timeLineViewinHours = 24;

    // Identifiers for creating events on timeline
    public static final int CHA = 1;
    public static final int HAS = 2;
    public static final int SYM = 3;
    public static final int MED = 4;
    public static final int DC = 5;
    public static final int DIA = 6;
    public static final int VIS = 7;

    // Tracking the height of journal and dynamic box(table)
    public double journalEventTotalHeight = 0;
    public double informationTableTotalHeight = 0;

    // Scrollview of journal 
    public double scrollView = 0;

    // Eventcounter
    private int eventCounter = 1;

    // Array of storring
    int lastPlaceOnTimeLine = 0;
    int vBoxPrefWidth = 50;

    // Arrays of overlaying events
    ArrayList<Integer> overlayerEvent = new ArrayList<>(2);
    ArrayList<Integer> overlayingEvent = new ArrayList<>(2);

    // sets the formattig of time
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm");

    // All the element from Scenebuilder with ID
    @FXML
    private VBox vBoxHeaderInformationTable;

    @FXML
    private VBox vBoxheader;

    @FXML
    private Label lbHeaderInformationTable;

    @FXML
    private AnchorPane ancorPaneTimeLine;

    @FXML
    private CheckBox chBoxCHAFilter;

    @FXML
    private CheckBox chBoxDCFilter;

    @FXML
    private CheckBox chBoxDIAFilter;

    @FXML
    private CheckBox chBoxHASFilter;

    @FXML
    private CheckBox chBoxMEDFilter;

    @FXML
    private CheckBox chBoxSYMFilter;

    @FXML
    private CheckBox chBoxVISFilter;

    @FXML
    private HBox hBoxHeaderFilters;

    @FXML
    private Line indi1m;

    @FXML
    private Line indi1u;

    @FXML
    private Line indi1year;

    @FXML
    private Line indi24;

    @FXML
    private Line indi48;

    @FXML
    private Line indi5year;

    @FXML
    private Line indi6m;

    @FXML
    private Line indi72;

    @FXML
    private Line indiMax;

    @FXML
    private AnchorPane anchorPaneJournal;

    @FXML
    private AnchorPane anchorPaneInformationTable;

    @FXML
    private Label lbEarlyDate;

    @FXML
    private Label lbEarlyTime;

    @FXML
    private Label lbMidDate;

    @FXML
    private Label lbMidTime;

    @FXML
    private Label lbToDaysDate;

    @FXML
    private Label lbToDaysTime;

    @FXML
    private Line lineTheTimeLine;

    @FXML
    private Text txtPersonOplys;

    @FXML
    private VBox vBoxTimeLine;

    @FXML
    private ScrollPane journalScrollPane;

    // All the methods called from Scenebuilder with ID
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ShowElements(MouseEvent event) {

        indiMax.setVisible(true);

        if (hBoxHeaderFilters.isVisible() & vBoxTimeLine.isVisible()) {
            hBoxHeaderFilters.setVisible(false);

            vBoxheader.setStyle("-fx-border-width: 0px");
        } else {
            hBoxHeaderFilters.setVisible(true);
            vBoxheader.setStyle(" -fx-border-width: 1px ; -fx-border-color: black; -fx-border-radius: 5px;");
            if (ispressed == true) {
                vBoxHeaderInformationTable.setVisible(true);
            }
        }

        ShowTimeLineInTimeView();

    }

    @FXML
    private void MetFilterCHA(ActionEvent event) {
        chaFilterOn = !chaFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    private void MetFilterHAS(ActionEvent event) {
        hasFilterOn = !hasFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    private void MetFilterSYM(ActionEvent event) {
        symFilterOn = !symFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    private void MetFilterMED(ActionEvent event) {
        medFilterOn = !medFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    private void MetFilterDC(ActionEvent event) {
        dcFilterOn = !dcFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    private void MetFilterDIA(ActionEvent event) {
        diaFilterOn = !diaFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    private void MetFilterVIS(ActionEvent event) {
        visFilterOn = !visFilterOn;
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView24Hours(ActionEvent event) {
        timeLineViewinHours = 24;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView48Hours(ActionEvent event) {
        timeLineViewinHours = 48;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView72Hours(ActionEvent event) {
        timeLineViewinHours = 72;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView1Week(ActionEvent event) {
        timeLineViewinHours = 168;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView1Month(ActionEvent event) {
        timeLineViewinHours = 731;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView6Month(ActionEvent event) {
        timeLineViewinHours = 4380;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeView1year(ActionEvent event) {
        timeLineViewinHours = 8760;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();

    }

    @FXML
    void metTimeView5years(ActionEvent event) {
        lastPlaceOnTimeLine = 0;
        timeLineViewinHours = 43800;
        ChangeDateAndTimeView(timeLineViewinHours);
        ClearAndUpdateEvents();
    }

    @FXML
    void metTimeViewmax(ActionEvent event) {
        ShowTimeLineInTimeView();
    }

    public void InitializeHomeScreen() {
        //Here we initialise the different queryables for our 3 Handlers
        DatabaseManipulator.executeQueryWithResultSet(eh);
        DatabaseManipulator.executeQueryWithResultSet(jh);
        DatabaseManipulator.executeQueryWithResultSet(ph);

        // invert the eventhandler and the journalhandler to get the last event first.
        Collections.reverse(eh.eventList);
        Collections.reverse(jh.journalNotesList);

        //This loop adds all rows in the table "NordEpjJournal" into an arraylist
        for (int j = 0; j < jh.journalNotesList.size(); j++) {
            Journal journal = jh.getJournalNotesList().get(j);
            UpdateJournalEvent(journal, "dontHighlight", j);
        }

        // set the journal height to 0 and the journal view state to be at the start.
        journalEventTotalHeight = 0;
        scrollView = 0;

        // create instance of the first persion in the database.
        Person Person1 = ph.getPersonList().get(0);

        //Here we display the information about the patient in the "Personlige oplysninger" window of the interface
        txtPersonOplys.setText(
                "Navn: " + Person1.firstName + " " + Person1.lastName + "\n"
                + "CPR: " + Person1.cpr + "\n"
                + "Køn: " + Person1.gender + "\n"
                + "Højde: " + Person1.height + " cm" + "\n"
                + "Vægt: " + Person1.weight + " kg"
        );

        ShowTimeLineInTimeView();

        indi24.setVisible(
                false);
        indi48.setVisible(
                false);
        indi72.setVisible(
                false);
        indi1u.setVisible(
                false);
        indi1m.setVisible(
                false);
        indi6m.setVisible(
                false);
        indi1year.setVisible(
                false);
        indi5year.setVisible(
                false);

        hBoxHeaderFilters.setVisible(
                false);
        vBoxheader.setStyle(
                "-fx-border-width: 0px");
        vBoxHeaderInformationTable.setVisible(
                false);

    }

    // cheks if a date is whith todays date and the start date
    public boolean isWithinRange(LocalDateTime testdate, LocalDateTime startDate) {
        return (testdate.isAfter(startDate));
    }

    // calculate the time since now.
    public float timeSinceNow(LocalDateTime testDate) {   // long før
        LocalDateTime now = LocalDateTime.now();
        return HOURS.between(testDate, now);
    }

    // create a element on the timeline
    public void CreateTimelineElement(int eventreferenceID, int identifier) {

        // Get the events
        Events Event = eh.getEventList().get(eventreferenceID);

        // check if event is empty and do nothing if event is empty
        if (identifier == CHA && Event.cha2dsvasc.isEmpty()) {
        } else if (identifier == HAS && Event.hasbled.isEmpty()) {
        } else if (identifier == SYM && Event.symptoms.isEmpty()) {
        } else if (identifier == MED && Event.medicine.isEmpty()) {
        } else if (identifier == DC && Event.cardioversions.isEmpty()) {
        } else if (identifier == DIA && Event.diagnosis.isEmpty()) {
        } else if (identifier == VIS && Event.vitalSigns.isEmpty()) {

        } // CREATE AN EVENT ON TIME AS IT IS NOT EMPTY
        else {

            // calculate the time from not to the event
            float timeSinceNowinHours = timeSinceNow(Event.dateTime);

            // get the lenght of the timeline in the appliction
            float timeLineLength = (float) lineTheTimeLine.getEndX();

            // offset is the space between the left border og timeline box and the beginning of the timeline.
            int offSet = (((int) ancorPaneTimeLine.getPrefWidth() - (int) lineTheTimeLine.getEndX()) / 2) - (vBoxPrefWidth / 2) - 2;

            // the relation determin the plavement on the timeline.
            float relation = timeSinceNowinHours / (float) timeLineViewinHours;

            // placement is then calculated.
            int placeOnTimeLine = (int) (timeLineLength - (int) (relation * timeLineLength)) + offSet;

            // check if the event overlaps with the previous event.
            if (Math.abs(lastPlaceOnTimeLine - placeOnTimeLine) < 50) {
                overlay = true;

                if (!overlayerEvent.contains(eventreferenceID)) {
                    int previousEvent = eventreferenceID - 1;
                    
                    // if event overlay then put overlaying events into array OverlayingEvents
//
                    overlayerEvent.add(eventreferenceID);
                    overlayingEvent.add(previousEvent);
                }

            }

            // save the placement of the time to compare to the new event.
            lastPlaceOnTimeLine = placeOnTimeLine;

            // IF NO OVERLAY THEN CREATE THE EVENT
            if (overlay == false) {

                VBox VBoxElement = new VBox();
                VBoxElement.setPrefWidth(vBoxPrefWidth);

                // Check for overlaying with other events
                VBoxElement.setLayoutX​(placeOnTimeLine);
                AnchorPane.setBottomAnchor(VBoxElement, 0.0);
                VBoxElement.setAlignment(Pos.CENTER);
                ancorPaneTimeLine.getChildren().add(0, VBoxElement);

                // Creates button
                ToggleButton btnElement = new ToggleButton();
                btnElement.getStyleClass().setAll("my-custom-button");
                btnElement.setMinWidth(45);

                // if another butten is pressed it sets the new butten as selected, such that it will be colorized
                if (saveEventreferenceID == eventreferenceID && saveidentifier == identifier && ispressed == true) {
                    btnElement.setSelected(true);
                } else {

                }

                VBoxElement.getChildren().add(0, btnElement);

                // Creates line
                Line lineElement = new Line();
                lineElement.setStartY(0);

                // sets the height of the events and is based on if filters is on or not.
                switch (identifier) {
                    case CHA:
                        btnElement.setText("CHA");
                        lineElement.setEndY(25);
                        break;
                    case HAS:
                        btnElement.setText("HAS");
                        if (!Event.cha2dsvasc.isEmpty() && chaFilterOn == true) {
                            eventCounter++;
                        }
                        lineElement.setEndY(eventCounter * 25);
                        eventCounter = 1;
                        break;
                    case SYM:
                        btnElement.setText("SYM");
                        if (!Event.cha2dsvasc.isEmpty() && chaFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.hasbled.isEmpty() && hasFilterOn == true) {
                            eventCounter++;
                        }

                        lineElement.setEndY(eventCounter * 25);
                        eventCounter = 1;
                        break;
                    case MED:
                        btnElement.setText("MED");

                        if (!Event.cha2dsvasc.isEmpty() && chaFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.hasbled.isEmpty() && hasFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.symptoms.isEmpty() && symFilterOn == true) {
                            eventCounter++;
                        }

                        lineElement.setEndY(eventCounter * 25);
                        eventCounter = 1;
                        break;
                    case DC:
                        btnElement.setText("DC");
                        if (!Event.cha2dsvasc.isEmpty() && chaFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.hasbled.isEmpty() && hasFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.symptoms.isEmpty() && symFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.medicine.isEmpty() && medFilterOn == true) {
                            eventCounter++;
                        }

                        lineElement.setEndY(eventCounter * 25);
                        eventCounter = 1;
                        break;
                    case DIA:
                        btnElement.setText("DIA");
                        if (!Event.cha2dsvasc.isEmpty() && chaFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.cha2dsvasc.isEmpty() && hasFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.symptoms.isEmpty() && symFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.medicine.isEmpty() && medFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.cardioversions.isEmpty() && dcFilterOn == true) {
                            eventCounter++;
                        }
                        lineElement.setEndY(eventCounter * 25);
                        eventCounter = 1;
                        break;

                    case VIS:

                        btnElement.setText("VP");
                        if (!Event.cha2dsvasc.isEmpty() && chaFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.hasbled.isEmpty() && hasFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.symptoms.isEmpty() && symFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.medicine.isEmpty() && medFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.cardioversions.isEmpty() && dcFilterOn == true) {
                            eventCounter++;
                        }
                        if (!Event.diagnosis.isEmpty() && diaFilterOn == true) {
                            eventCounter++;

                        } else if (overlayingEvent.contains(eventreferenceID) && eventreferenceID > 0 && diaFilterOn == true) {
                            int index = eventreferenceID + 1;
                            Events previousEvents = eh.getEventList().get(index);

                            if (!previousEvents.diagnosis.isEmpty()) {
                                eventCounter++;
                            }
                        }

                        lineElement.setEndY(eventCounter * 25);
                        eventCounter = 1;
                        break;
                }

                VBoxElement.getChildren().add(1, lineElement);

                // Creates button action for event
                btnElement.setOnAction((ActionEvent event) -> {

                    // IF A BUTTEN IS PRESSED AND THEN A NEW BUTEEN IS PRESSED
                    if (btnElement.isSelected() == true && ispressed == true) {

                        saveEventreferenceID = eventreferenceID;
                        saveidentifier = identifier;
                        ancorPaneTimeLine.getChildren().clear();

                        UpdateTimeviewEvents(CHA, chaFilterOn);
                        UpdateTimeviewEvents(HAS, hasFilterOn);
                        UpdateTimeviewEvents(SYM, symFilterOn);
                        UpdateTimeviewEvents(MED, medFilterOn);
                        UpdateTimeviewEvents(DC, dcFilterOn);
                        UpdateTimeviewEvents(DIA, diaFilterOn);
                        UpdateTimeviewEvents(VIS, visFilterOn);
                        UpdateswitchCaseWithIdentificer(eventreferenceID, identifier);

                    } // FIRST TIME BUTTON IS PRESSED AND If A BUTTEN IS UNPRESSED
                    else {

                        // For tracking button state
                        saveEventreferenceID = eventreferenceID;
                        saveidentifier = identifier;
                        ispressed = !ispressed;

                        if (vBoxHeaderInformationTable.isVisible()) {
                            vBoxHeaderInformationTable.setVisible(false);
                            anchorPaneJournal.getChildren().clear();
                            journalEventTotalHeight = 0;
                            scrollView = 0;

                            for (int j = 0; j < jh.journalNotesList.size(); j++) {
                                Journal journalloop = jh.getJournalNotesList().get(j);
                                UpdateJournalEvent(journalloop, "dontHighlight", j);

                            }
                            journalEventTotalHeight = 0;
                            scrollView = 0;

                        } else {
                            vBoxHeaderInformationTable.setVisible(true);
                            //Find out which Journal journal mataches the EventID
                            anchorPaneJournal.getChildren().clear();
                            journalEventTotalHeight = 0;
                            scrollView = 0;

                            // Assigns object AddOnEvent with the first row of the AddOn Events table
                            UpdateswitchCaseWithIdentificer(eventreferenceID, identifier);
                        }
                    }
                }
                );
            } else {
                overlay = false;

            }
        }
    }

    public void CreateEventAndOverlayingEventsInTable(Events events, int eventreferenceID, int identifier) {
        // clear all the events of the information table
        anchorPaneInformationTable.getChildren().clear();
        
        // clear total hight of information table
        informationTableTotalHeight = 0;

        // set the shifte that enshures that the color changes 
        informationTableColorShifter = true;

        // set default minimum height and text height
        double MinimumtextHeight = 38;
        double TextHeight = 38;

        CreateHLineInInformationTable(events, identifier, TextHeight, MinimumtextHeight);

        if (overlayingEvent.contains(eventreferenceID)) {

            int indexForPreviousEvent = eventreferenceID + 1;
            Events EventsOverlay = eh.getEventList().get(indexForPreviousEvent);

            CreateHLineInInformationTable(EventsOverlay, identifier, TextHeight, MinimumtextHeight);

        }

        anchorPaneInformationTable.setPrefHeight(informationTableTotalHeight);

    }

    public void UpdateTimeviewEvents(int identifier, boolean FilterIsOn) {

        LocalDateTime TimePeriod = LocalDateTime.now().plusHours(-timeLineViewinHours);

        if (FilterIsOn == true) {

            for (int i = 0; i < eh.eventList.size(); i++) {
                Events Event = eh.getEventList().get(i);

                LocalDateTime EventDateTime = Event.dateTime;

                boolean DateWithin = isWithinRange(EventDateTime, TimePeriod);

                if (DateWithin == true) {
                    CreateTimelineElement(i, identifier);
                } else {
                }
            }

        }
        lastPlaceOnTimeLine = 0;
    }

    public void CreateHLineInInformationTable(Events event, int identifier, double TextHeight, double MinimumtextHeight) {

        switch (identifier) {
            case CHA:
                if (!event.cha2dsvasc.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
            case HAS:
                if (!event.hasbled.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
            case SYM:
                if (!event.symptoms.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
            case MED:
                if (!event.medicine.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
            case DC:
                if (!event.cardioversions.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
            case DIA:
                if (!event.diagnosis.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
            case VIS:
                if (!event.vitalSigns.isEmpty()) {
                    identifierContainsData = true;
                }
                break;
        }
        if (identifierContainsData == true) {

            // get the date and time of the event.
            String formattedDateTime = event.dateTime.format(dateTimeFormatter);

            // create an Hbox that is set into the AnchorPaneDynBox
            HBox HboxInformationTable = new HBox();

            // set the position horisontal position of the. Initail is 0.
            HboxInformationTable.setLayoutY(informationTableTotalHeight);
            // set width to full width.
            HboxInformationTable.setPrefWidth(853);

            // Change color of every other entry in information table
            if (informationTableColorShifter == true) {
                informationTableColorShifter = false;
                HboxInformationTable.setStyle("-fx-background-color: #c3d6e0;");
            } else {
                informationTableColorShifter = true;
                HboxInformationTable.setStyle("-fx-background-color: #dce8ed;");
            }

            // set spacing between the two labels in the hbox
            HboxInformationTable.setSpacing(200);
            HboxInformationTable.setAlignment(Pos.CENTER);

            // create label for the date of the event
            Label lbDateTimeInformationTable = new Label(formattedDateTime);
            lbDateTimeInformationTable.setPrefWidth(230);
            lbDateTimeInformationTable.setPrefHeight(26);
            lbDateTimeInformationTable.setAlignment(Pos.CENTER);
            HboxInformationTable.getChildren().add(lbDateTimeInformationTable);

            // create text field for for the data of the event
            Text txtDataInInformationTable = new Text();

            // set the data of the text with depenedend identifier
            switch (identifier) {
                case CHA:
                    txtDataInInformationTable.setText(event.cha2dsvasc);
                    break;
                case HAS:
                    txtDataInInformationTable.setText(event.hasbled);
                    break;
                case SYM:
                    txtDataInInformationTable.setText(event.symptoms);
                    TextHeight = calculateTextAreaHeight(event.symptoms, 45);
                    if (TextHeight < MinimumtextHeight) {
                        TextHeight = MinimumtextHeight;
                    }
                    break;
                case MED:
                    txtDataInInformationTable.setText(event. medicine);
                    TextHeight = calculateTextAreaHeight(event.medicine, 45);
                    if (TextHeight < MinimumtextHeight) {
                        TextHeight = MinimumtextHeight;
                    }
                    break;
                case DC:
                    txtDataInInformationTable.setText(event.cardioversions);
                    TextHeight = calculateTextAreaHeight(event.cardioversions, 45);
                    if (TextHeight < MinimumtextHeight) {
                        TextHeight = MinimumtextHeight;
                    }
                    break;
                case DIA:
                    txtDataInInformationTable.setText(event.diagnosis);
                    TextHeight = calculateTextAreaHeight(event.diagnosis, 45);
                    if (TextHeight < MinimumtextHeight) {
                        TextHeight = MinimumtextHeight;
                    }
                    break;
                case VIS:
                    txtDataInInformationTable.setText(event.vitalSigns);
                    TextHeight = calculateTextAreaHeight(event.vitalSigns, 45);
                    if (TextHeight < MinimumtextHeight) {
                        TextHeight = MinimumtextHeight;
                    }
                    break;
            }

            // set pre height and min weight
            HboxInformationTable.setPrefHeight(TextHeight);
            HboxInformationTable.setMinHeight(38);

            // calculate new total height
            informationTableTotalHeight = informationTableTotalHeight + TextHeight;

            // set wrapping width and center text
            txtDataInInformationTable.setWrappingWidth(230);
            txtDataInInformationTable.setTextAlignment(TextAlignment.CENTER);

            // add text field to hbox
            HboxInformationTable.getChildren().add(txtDataInInformationTable);

            // add hbox field to Anchorpane
            anchorPaneInformationTable.getChildren().add(HboxInformationTable);
        }
        identifierContainsData = false;
    }

    public void ClearAndUpdateEvents() {

        ancorPaneTimeLine.getChildren().clear();
        UpdateTimeviewEvents(CHA, chaFilterOn);
        UpdateTimeviewEvents(HAS, hasFilterOn);
        UpdateTimeviewEvents(SYM, symFilterOn);
        UpdateTimeviewEvents(MED, medFilterOn);
        UpdateTimeviewEvents(DC, dcFilterOn);
        UpdateTimeviewEvents(DIA, diaFilterOn);
        UpdateTimeviewEvents(VIS, visFilterOn);
    }

    private double calculateTextAreaHeight(String str, int screenSize) {

        String[] lines = str.split("\r\n|\r|\n");
        double StringsBiggerThanScreen = 0;

        //This loop adds all rows in the table "NordEpjJournal" into an arraylist
        double TextAreaWidth = screenSize;
        double lineHeight = 18.2;
        for (int j = 0; j < lines.length; j++) {
            if (lines[j].length() > TextAreaWidth) {
                StringsBiggerThanScreen = StringsBiggerThanScreen + (Math.floor((lines[j].length() / TextAreaWidth)));

            }
        }

        double TextAreaHeight = ((((lines.length + StringsBiggerThanScreen))) * lineHeight);

        return TextAreaHeight;
    }

    private void UpdateJournal(int eventreferenceID, String inputString) {

        anchorPaneJournal.getChildren().clear();
        journalEventTotalHeight = 0;
        scrollView = 0;

        //This loop adds all rows in the table "NordEpjJournal" into an arraylist
        for (int j = 0; j < jh.journalNotesList.size(); j++) {
            Journal LoopJournal = jh.getJournalNotesList().get(j);
            String LoopString = "dontHighlight";

            if (j == eventreferenceID) {
                LoopString = inputString;

            } else {
                LoopString = "dontHighlight";
            }

            UpdateJournalEvent(LoopJournal, LoopString, j);

        }
    }

    private void UpdateJournalEvent(Journal journal, String highLightableWord, double index) {

        // create hBox for label names Journalnotat
        HBox hboxWithLbJournalnote = new HBox();
        // set horisontal position to 10 px
        hboxWithLbJournalnote.setLayoutX(10);
        // Calculate the new vertical height and set the vertical height.

        journalEventTotalHeight = journalEventTotalHeight + 10;
        hboxWithLbJournalnote.setLayoutY(journalEventTotalHeight);

        // create hBox for label names tidspunkt
        HBox hboxWithlbTimeAutherProfessionDepartment = new HBox();
        // set horisontal position to 10 px
        hboxWithlbTimeAutherProfessionDepartment.setLayoutX(10);
        // Calculate the new vertical height and set the vertical height.
        journalEventTotalHeight = journalEventTotalHeight + 20;
        hboxWithlbTimeAutherProfessionDepartment.setLayoutY(journalEventTotalHeight);

        // set pref height and max width of the two hBoxes
        hboxWithLbJournalnote.setPrefWidth(330);
        hboxWithLbJournalnote.setMaxWidth(330);
        hboxWithlbTimeAutherProfessionDepartment.setPrefWidth(330);
        hboxWithlbTimeAutherProfessionDepartment.setMaxWidth(330);

        // create label named journalnote and set styling
        Label lbJournalnote = new Label("Journalnotat");
        lbJournalnote.setFont(Font.font("Arial Black", FontWeight.BOLD, 13));

        // create label named lbTimeAutherProfessionDepartment and set styling and text
        Label lbTimeAutherProfessionDepartment = new Label();
        String journalformattedDateTime = journal.dateTime.format(dateTimeFormatter);
        lbTimeAutherProfessionDepartment.setText(journalformattedDateTime + ", " + journal.author + ", " + journal.profession + ", " + journal.medicalWard);

        lbTimeAutherProfessionDepartment.setStyle("-fx-font-size: 13px; -fx-text-fill: #7c7c78; ");

        // Add the labes to the hboxes
        hboxWithLbJournalnote.getChildren().add(0, lbJournalnote);
        hboxWithlbTimeAutherProfessionDepartment.getChildren().add(0, lbTimeAutherProfessionDepartment);

        // Get the text from the journalnote
        String JournalString = journal.journalNote;

        // Makes an area in the journalnotetext that can be highlighted
        final HighlightableTextArea highlightableTextArea = new HighlightableTextArea();

        // calculate and set the position of the highlightabletext area
        journalEventTotalHeight = journalEventTotalHeight + 20;
        highlightableTextArea.setLayoutY(journalEventTotalHeight);

        // set the text of the area to the text from the journalnote and set styling
        highlightableTextArea.setText(JournalString);
        highlightableTextArea.getTextArea().setWrapText(true);
        highlightableTextArea.getTextArea().setStyle("-fx-font-size: 13px; -fx-background-color: transparent; -fx-border-color: transparent;");
        highlightableTextArea.setMaxWidth(330);

        // calculate hight of the area with the screen size og 54 charters.
        double TextAreaHeight = calculateTextAreaHeight(JournalString, 54);

        int extraSpace = 43;  //manually adapted tp screen size. Previous 10
        // set the height based on the calculated area. adds 10 for ensuring that it is high enough. Manuel adaptation.
        highlightableTextArea.textArea.setMinHeight(TextAreaHeight + extraSpace);
        highlightableTextArea.textArea.setMaxWidth(330);

        // calculate the hight for the next element
        journalEventTotalHeight = journalEventTotalHeight + TextAreaHeight + extraSpace;

        // add the the two hbox and highligtable text area to the journalAnchorPane
        anchorPaneJournal.getChildren().add(0, hboxWithLbJournalnote);
        anchorPaneJournal.getChildren().add(1, hboxWithlbTimeAutherProfessionDepartment);
        anchorPaneJournal.getChildren().add(2, highlightableTextArea);

        // set the total hight of the journalAnchorPane 
        anchorPaneJournal.setPrefHeight(journalEventTotalHeight);

        // if the method input is different from dontHighlight - the highlight
        if (!highLightableWord.equals("dontHighlight")) {

            int startindex = JournalString.indexOf(highLightableWord);
            // set the ScrollView of the journalnote - this is manually adapted
            scrollView = journalEventTotalHeight - ((TextAreaHeight + 50) / index * 0.6); // Manuel adapted with 0,6
            if (startindex != -1) {
                // if it is HAS-BLED or CHA2DS2-VASc the extend the highligatble box with 5
                if (highLightableWord.equals("HAS-BLED") || highLightableWord.equals("CHA2DS2-VASc score")) {
                    highlightableTextArea.highlight(startindex, startindex + highLightableWord.length() + 5);
                } else {
                    // else set the highligtable bos to the start index and finnish at the end index
                    highlightableTextArea.highlight(startindex, startindex + highLightableWord.length());
                }

            }
        } else {
            // Dont highlight
        }

        // set the view of the journal as the relation between scrollview og the journalnote and the total leghth.
        journalScrollPane.setVvalue(scrollView / journalEventTotalHeight);

    }

    public void ShowTimeLineInTimeView() {
        // find the last index of the events
        int OldestEventIndex = eh.eventList.size() - 1;
        // find the event of the last index
        Events Event = eh.getEventList().get(OldestEventIndex);
        int HoursFromNowToLastEvent = (int) timeSinceNow(Event.dateTime) + 500; // 500 is manual adaption making sure the oldest events are pressent

        // update the global variable timeLineViewinHours
        timeLineViewinHours = HoursFromNowToLastEvent;
        // change time view
        ChangeDateAndTimeView(timeLineViewinHours);
        // clear and update all events
        ClearAndUpdateEvents();

    }

    public void UpdateswitchCaseWithIdentificer(int eventreferenceID, int identifier) {

        // create instace of events with the input eventreferenceID
        Events actualEvent = eh.getEventList().get(eventreferenceID);

        // create instace of journals with the input eventreferenceID
        Journal journal = jh.getJournalNotesList().get(eventreferenceID);

        switch (identifier) {
            case CHA:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {

                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.cha2dsvasc.isEmpty()) {
                        UpdateJournal(eventreferenceID, "CHA2DS2-VASc score");

                    }
                } else {
                }

                // set the label of the header of the information table 
                lbHeaderInformationTable.setText("CHA2DS2_VASc Score");
                // create the events in the information table 
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);
                break;

            case HAS:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {

                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.hasbled.isEmpty()) {

                        UpdateJournal(eventreferenceID, "HAS-BLED");
                    }
                }
                lbHeaderInformationTable.setText("HASBLED Score");
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);

                break;
            case SYM:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {
                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.symptoms.isEmpty()) {
                        UpdateJournal(eventreferenceID, actualEvent.symptoms);

                    }
                }
                lbHeaderInformationTable.setText("Symptomer");
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);

                break;
            case MED:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {

                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.medicine.isEmpty()) {
                        UpdateJournal(eventreferenceID, actualEvent.medicine);
                    }
                }

                lbHeaderInformationTable.setText("Medicin");
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);

                break;
            case DC:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {

                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.cardioversions.isEmpty()) {

                        UpdateJournal(eventreferenceID, actualEvent.cardioversions);
                    }
                }
                lbHeaderInformationTable.setText("DC konverteringer");
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);

                break;
            case DIA:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {

                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.diagnosis.isEmpty()) {
                        UpdateJournal(eventreferenceID, actualEvent.diagnosis);
                    }
                }

                lbHeaderInformationTable.setText("Diagnoser");
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);

                break;
            case VIS:

                //Check if the Datetime for the given Journal matches the Datetime of the event
                if (journal.dateTime.compareTo(actualEvent.dateTime) == 0) {

                    //Checks that all non-empty matches will be colored when the datapoint/event is clicked
                    if (!actualEvent.vitalSigns.isEmpty()) {
                        UpdateJournal(eventreferenceID, actualEvent.vitalSigns);
                    }
                }

                lbHeaderInformationTable.setText("Vitale Parametre");
                CreateEventAndOverlayingEventsInTable(actualEvent, eventreferenceID, identifier);

                break;
        }

    }

    public void ChangeDateAndTimeView(int TimeView) {

        // get todays time and date
        LocalDateTime now = LocalDateTime.now();

        // calculate the logest time ago and the time in between
        LocalDateTime MostHoursAgo = LocalDateTime.now().plusHours(-TimeView);
        LocalDateTime MidtHoursAgo = LocalDateTime.now().plusHours(-(TimeView / 2));

        // update the time labels under the timeline
        lbToDaysDate.setText(dtfDate.format(now));
        lbToDaysTime.setText(dtfTime.format(now));
        lbEarlyDate.setText(dtfDate.format(MostHoursAgo));
        lbEarlyTime.setText(dtfTime.format(MostHoursAgo));
        lbMidDate.setText(dtfDate.format(MidtHoursAgo));
        lbMidTime.setText(dtfTime.format(MidtHoursAgo));

        // set the indicator under the timeinterval to visible and alle others og not visible
        switch (TimeView) {
            case 24:
                indi24.setVisible(true);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;
            case 48:
                indi24.setVisible(false);
                indi48.setVisible(true);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;

            case 72:
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(true);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;
            case 168: // 1 uge
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(true);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;

            case 731: // 1 måned
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(true);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;

            case 4380: // 6 måned
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(true);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;

            case 8760: // 1 år
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(true);
                indi5year.setVisible(false);
                indiMax.setVisible(false);
                break;

            case 43800: // 5 år
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(true);
                indiMax.setVisible(false);
                break;

            default: // max
                indi24.setVisible(false);
                indi48.setVisible(false);
                indi72.setVisible(false);
                indi1u.setVisible(false);
                indi1m.setVisible(false);
                indi6m.setVisible(false);
                indi1year.setVisible(false);
                indi5year.setVisible(false);
                indiMax.setVisible(true);
                break;
        }

    }

}
