import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableColumnModel;

public class Main implements FrontEnd{

    private JFrame f;

    private JTable dayTimetable;
    private JTable taskList;

    private JButton addNewTaskButton;
    private JButton editTaskButton;
    private JButton optionButton;

    private JButton nextButton;
    private JButton taskButton;

    private JPanel dayPanel;
    private JPanel infoPanel;
    private JPanel calendarPanel;

    private JScrollPane dayScrollPane;
    private JScrollPane taskScrollPane;

    private Dimension screenSize;
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel timetablePanel;

    private CardLayout cardLayout;

    private JPanel infoDisplayPanel;

    private JCalendar calendar;

    private JPanel newTaskPanel;
    private JPanel inputPanel;
    private JButton saveButton;

    private String[][] taskData;

    private JPanel optionPanel;
    private JButton saveButton2;


    String [] urgencyStrings = {"Non-urgent", "Urgent", "Very urgent"};
    String[] categoryStrings = {"Work", "Sleep", "Eat", "Rest", "Activity"};

    JLabel name = new JLabel("Name: ");
    JLabel description = new JLabel("Description: ");
    JLabel duration = new JLabel("Duration: ");
    JLabel deadline = new JLabel("Deadline: ");
    JLabel intervals = new JLabel("Intervals: ");
    JLabel urgency = new JLabel("Urgency: ");
    JLabel category = new JLabel("Category: ");

    JTextField inputName= new JTextField(15);
    JTextField inputDescription = new JTextField((15));
    JSpinner inputDuration = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    JDateChooser inputDeadline = new JDateChooser();
    JSpinner inputIntervals = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    JComboBox inputUrgency = new JComboBox(urgencyStrings);
    JComboBox inputCategory = new JComboBox(categoryStrings);

    JLabel restTime = new JLabel("Rest Time: ");
    JLabel drinking = new JLabel("Drinking Amount: ");
    JLabel sleeping = new JLabel("Sleep Time: ");
    JLabel working = new JLabel("Work Time: ");

    JSpinner inputRestTime = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    JSpinner inputDrink = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));
    JSpinner inputSleeping = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
    JSpinner inputWorking = new JSpinner(new SpinnerNumberModel(1, 0, null, 1));


    public void init(){
        f = new JFrame();
        mainPanel = new JPanel(new BorderLayout());

        cardLayout = new CardLayout();

        infoPanel = new JPanel(new GridLayout(4,1));
        dayPanel = new JPanel(cardLayout);

        calendarPanel = new JPanel();
        listPanel = new JPanel(new BorderLayout());
        timetablePanel = new JPanel(new BorderLayout());
        infoDisplayPanel = new JPanel(new BorderLayout());
        calendar = new JCalendar();
        addNewTaskButton = new JButton("Add New Task");
        editTaskButton = new JButton("Edit Task");
        optionButton = new JButton("Options");
        taskButton = new JButton("Tasks");
        nextButton = new JButton("Next");

        editTaskButton.setEnabled(false);

        initTaskPanel();
        initTimeTable();
        initInfoPanel();
        initSidePanel();
        initTaskAddPanel();
        initListeners();

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.add(infoPanel, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.EAST);
        mainPanel.add(optionPanel, BorderLayout.NORTH);
        mainPanel.add(dayPanel, BorderLayout.CENTER);

        f.getContentPane().add(mainPanel);
        f.setVisible(true);
        f.setSize(screenSize.width/2,screenSize.height/2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LocalDate date = LocalDate.now();
        //String sDate = (String) date.replaceAll("\\D","");



        //MyDateTime mdt = new MyDateTime(Integer.valueOf(sDate),0);
        //BackEnd backend = new ChummiesMagicMaker(this, mdt);

    }

    private void initTaskAddPanel(){

        newTaskPanel = new JPanel(new BorderLayout());
        inputPanel = new JPanel(new GridLayout(8,2));
        optionPanel = new JPanel(new GridLayout(5,2));
        saveButton = new JButton("Save");
        saveButton2 = new JButton("Save");

        inputPanel.add(name); inputPanel.add(inputName);
        inputPanel.add(description); inputPanel.add(inputDescription);
        inputPanel.add(duration); inputPanel.add(inputDuration);
        inputPanel.add(deadline); inputPanel.add(inputDeadline);
        inputPanel.add(intervals); inputPanel.add(inputIntervals);
        inputPanel.add(urgency); inputPanel.add(inputUrgency);
        inputPanel.add(category); inputPanel.add(inputCategory);
        inputPanel.add(saveButton); inputPanel.add(new JLabel(" "));

        inputPanel.setVisible(false);

        optionPanel.add(restTime); optionPanel.add(inputRestTime);
        optionPanel.add(drinking); optionPanel.add(inputDrink);
        optionPanel.add(sleeping); optionPanel.add(inputSleeping);
        optionPanel.add(working); optionPanel.add(inputWorking);
        optionPanel.add(saveButton2); optionPanel.add(new JLabel(" "));

        optionPanel.setVisible(false);
    }

    public void initTimeTable(){
        String[][] times= {
                {"00:00", ""},
                {"01:00", ""},
                {"02:00", ""},
                {"03:00", ""},
                {"04:00", ""},
                {"05:00", ""},
                {"06:00", ""},
                {"07:00", ""},
                {"08:00", ""},
                {"09:00", ""},
                {"10:00", ""},
                {"11:00", ""},
                {"12:00", ""},
                {"13:00", ""},
                {"14:00", ""},
                {"15:00", ""},
                {"16:00", ""},
                {"17:00", ""},
                {"18:00", ""},
                {"19:00", ""},
                {"20:00", ""},
                {"21:00", ""},
                {"22:00", ""},
                {"23:00", ""}
        };


        String[] columnNamesTimetable = {"Time: ", "Date: "+ LocalDate.now()};
        dayTimetable = new JTable(times, columnNamesTimetable);
        TableColumnModel columnModel = dayTimetable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(470);
        dayScrollPane = new JScrollPane(dayTimetable);
        dayScrollPane.setPreferredSize(new Dimension(480,300));


        JPanel dayScrollPaneHolder = new JPanel(new BorderLayout());
        dayScrollPaneHolder.add(dayScrollPane,BorderLayout.CENTER);
        dayScrollPaneHolder.add(infoDisplayPanel,BorderLayout.SOUTH);
        dayScrollPaneHolder.setVisible(true);
        timetablePanel.add(taskButton, BorderLayout.SOUTH);
        timetablePanel.add(dayScrollPaneHolder, BorderLayout.CENTER);

        dayPanel.add(listPanel, "tasks");
        dayPanel.add(timetablePanel, "timetable");
    }

    private void initTaskPanel(){

        taskData = new String[][] {{"Homework", "140", "2", "2020-02-14", "Urgent", "Revision for Test", "Work"},
                {"Nap", "60", "1", "2020-02-9" , " Non-urgent", "Rest", "Sleep"}
        };

        String[] columnNames = {"Name","Total Duration", "Intervals", "Deadline", "Urgency", "Description", "Category"};

        populateTable(taskScrollPane, columnNames, taskData);
        listPanel.add(nextButton, BorderLayout.SOUTH);
        listPanel.add(taskScrollPane, BorderLayout.CENTER);


    }

    private void populateTable(JScrollPane pane,String[] columnNames, String[][] data){
        taskList = new JTable(taskData, columnNames);
        taskScrollPane = new JScrollPane(taskList);
    }

    private void initInfoPanel(){

        infoDisplayPanel.add(new JTextArea(13,10), BorderLayout.CENTER);
        infoDisplayPanel.setVisible(false);

    }

    private void initSidePanel(){
        calendarPanel.add(calendar);

        infoPanel.add(calendarPanel);
        infoPanel.add(optionButton);
        infoPanel.add(editTaskButton);
        infoPanel.add(addNewTaskButton);
    }

    private void initListeners(){
        nextButton.addActionListener(e->{
            cardLayout.show(dayPanel, "timetable");
        });

        taskButton.addActionListener(e->{
            cardLayout.show(dayPanel, "tasks");
        });

        taskList.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                int row = dayTimetable.getSelectedRow();
                if(row==-1){
                    editTaskButton.setEnabled(true);
                }
                else if(row!=-1){
                    editTaskButton.setEnabled(false);
                }

            }
        });

        dayTimetable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                int row = dayTimetable.getSelectedRow();
                if(row==-1){
                    infoDisplayPanel.setVisible(false);
                }
                else if(row!=-1){
                    //JOptionPane.showMessageDialog(f, "Details", "data", JOptionPane.INFORMATION_MESSAGE);
                    infoDisplayPanel.setVisible(true);
                }

            }
        });

        addNewTaskButton.addActionListener(e->{
            inputPanel.setVisible(true);
            Date date = null;

            inputName.setText("");
            inputDuration.setValue(1);
            inputIntervals.setValue(1);
            inputDeadline.setDate(date);
            inputUrgency.setSelectedIndex(0);
            inputDescription.setText("");
            inputCategory.setSelectedIndex(0);

        });

        editTaskButton.addActionListener(e->{
            inputPanel.setVisible(true);
            String [] s = taskData[taskList.getSelectedRow()];
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(s[3]);
            }
            catch(ParseException ex){
            }

            inputName.setText(s[0]);
            inputDuration.setValue(Integer.valueOf(s[1]));
            inputIntervals.setValue(Integer.valueOf(s[2]));
            inputDeadline.setDate(date);
            inputUrgency.setSelectedItem(s[4]);
            inputDescription.setText(s[5]);
            inputCategory.setSelectedItem(s[6]);
        });

        saveButton.addActionListener(e->{
            inputPanel.setVisible(false);
            String [] newTask = {inputName.getText(),
                    inputDescription.getText(),
                    (String) inputDuration.getValue(),
                    (String) inputIntervals.getValue(),
                    (String) inputUrgency.getSelectedItem(),
                    (String) inputCategory.getSelectedItem()
            };

        });

        optionButton.addActionListener(e->{
            optionPanel.setVisible(true);
        });

        saveButton2.addActionListener(e->{
            optionPanel.setVisible(false);
            inputRestTime.setValue(inputRestTime.getValue());
            inputDrink.setValue(inputDrink.getValue());
            inputSleeping.setValue(inputSleeping.getValue());
            inputWorking.setValue(inputWorking.getValue());
        });

    }

    public static void main(String[] args) {

        Main main = new Main();
        main.init();
    }

    @Override
    public ArrayList<Task> getTasks() {
        return null;
    }

    @Override
    public ArrayList<RestTime> getRestTimes() {
        return null;
    }

    @Override
    public Options getOptions() {
        return null;
    }
}

