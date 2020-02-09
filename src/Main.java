import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
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
    private JButton deleteButton;

    private String[][] taskData;

    private JPanel optionPanel;
    private JButton saveButton2;

    private JPanel buttonPanel;


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

    String[] columnNames = {"Name","Total Duration", "Intervals", "Deadline", "Urgency", "Description", "Category"};


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

        deleteButton = new JButton("Delete");

        editTaskButton.setEnabled(false);
        deleteButton.setEnabled(false);

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

        LocalDate date = LocalDate.now();
        String sDate = date.toString().replaceAll("\\D","");



        MyDateTime mdt = new MyDateTime(Integer.valueOf(sDate),0);
        BackEnd backend = new ChummiesMagicMaker(this, mdt);

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
        taskData = new String[0][0];
        populateTable(taskScrollPane, columnNames, taskData);
        addToTable(taskList, new String[]{"Homework", "140", "2", "2020-02-14", "Urgent", "Revision for Test", "Work"});
        addToTable(taskList, new String[]{"Nap", "60", "1", "2020-02-9" , " Non-urgent", "Rest", "Sleep"});

        listPanel.add(nextButton, BorderLayout.SOUTH);
        listPanel.add(taskScrollPane, BorderLayout.CENTER);


    }

    private String[][] getTaskData(JTable table){
        String[][] taskData = new String[table.getModel().getRowCount()][table.getModel().getColumnCount()];
        for(int i = 0; i < table.getModel().getRowCount(); i++){
            String[] taskDataTemp = new String[table.getModel().getColumnCount()];
            for(int j = 0; j < table.getModel().getColumnCount(); j++){
                taskDataTemp[j] = table.getModel().getValueAt(i,j).toString();
            }
            taskData[i] = taskDataTemp;
        }
        return taskData;
    }

    private DefaultTableModel addToTable(JTable table, String[] rowData){
        taskData = getTaskData(table);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        for(String[] row : taskData){
            model.addRow(row);
        }
        model.addRow(rowData);
        table.setModel(model);
        return model;
    }

    private DefaultTableModel editTable(JTable table, String[] rowData){
        taskData = getTaskData(table);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        for(String[] row : taskData){
            model.addRow(row);
        }
        for(int i = 0; i < taskData[0].length; i++) {
            model.setValueAt(rowData[i], table.getSelectedRow(), i);
        }
        table.setModel(model);
        return model;
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
        JPanel emptyPanel1 = new JPanel();
        JPanel emptyPanel2 = new JPanel();

        buttonPanel = new JPanel(new GridLayout(4,1));

        buttonPanel.add(optionButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editTaskButton);
        buttonPanel.add(addNewTaskButton);

        infoPanel.add(emptyPanel1);
        infoPanel.add(emptyPanel2);
        infoPanel.add(buttonPanel);
    }

    private void initListeners(){
        ActionListener addListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(false);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(inputDeadline.getDate());
                String [] newTask = {inputName.getText(),
                        inputDuration.getValue().toString(),
                        inputIntervals.getValue().toString(),
                        date,
                        inputUrgency.getSelectedItem().toString(),
                        inputDescription.getText(),
                        inputCategory.getSelectedItem().toString()
                };
                addToTable(taskList, newTask);
            }
        };
        ActionListener editListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(false);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(inputDeadline.getDate());
                String [] newTask = {inputName.getText(),
                        inputDuration.getValue().toString(),
                        inputIntervals.getValue().toString(),
                        date,
                        inputUrgency.getSelectedItem().toString(),
                        inputDescription.getText(),
                        inputCategory.getSelectedItem().toString()
                };
                editTable(taskList, newTask);
            }
        };
        saveButton.addActionListener(editListener);
        saveButton.addActionListener(addListener);
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
                    deleteButton.setEnabled(true);
                }
                else if(row!=-1){
                    editTaskButton.setEnabled(false);
                    deleteButton.setEnabled(false);
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
            saveButton.removeActionListener(editListener);
            saveButton.removeActionListener(addListener);
            saveButton.addActionListener(addListener);

        });

        editTaskButton.addActionListener(e->{
            inputPanel.setVisible(true);
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(taskList.getModel().getValueAt(taskList.getSelectedRow(),3).toString());
            }
            catch(ParseException ex){
            }

            inputName.setText(taskList.getModel().getValueAt(taskList.getSelectedRow(),0).toString());
            inputDuration.setValue(Integer.valueOf(taskList.getModel().getValueAt(taskList.getSelectedRow(),1).toString()));
            inputIntervals.setValue(Integer.valueOf(taskList.getModel().getValueAt(taskList.getSelectedRow(),2).toString()));
            inputDeadline.setDate(date);
            inputUrgency.setSelectedItem(taskList.getModel().getValueAt(taskList.getSelectedRow(),4).toString());
            inputDescription.setText(taskList.getModel().getValueAt(taskList.getSelectedRow(),5).toString());
            inputCategory.setSelectedItem(taskList.getModel().getValueAt(taskList.getSelectedRow(),6).toString());
            saveButton.removeActionListener(editListener);
            saveButton.removeActionListener(addListener);
            saveButton.addActionListener(editListener);
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

        deleteButton.addActionListener(e->{
            ((DefaultTableModel) taskList.getModel()).removeRow(taskList.getSelectedRow());
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

