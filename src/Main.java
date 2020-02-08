import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.border.Border;

public class Main {

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
    //private JPanel buttonPanel;

    private JScrollPane dayScrollPane;
    private JScrollPane taskScrollPane;

    private Dimension screenSize;
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel timetablePanel;

    private CardLayout cardLayout;

    private JPanel infoDisplayPanel;

    private JCalendar calendar;


    public Main() {

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
        dayScrollPane = new JScrollPane(dayTimetable);

        String[][] taskData = {
                {"Homework", "2 hrs", "2", "14/2/20", "Urgent", "Revision for Test", "Work"},
                {"Nap", "1 hr", "1", "today" , " Non-urgent", "Rest", "Sleep"}
        };

        String[] columnNamesTask = {"Name","Total Duration", "Intervals", "Deadline", "Urgency", "Description", "Category"};

        taskList = new JTable(taskData, columnNamesTask);
        taskScrollPane = new JScrollPane(taskList);

        JTextArea info = new JTextArea("data");

        infoDisplayPanel.add(new JTextArea(13,10), BorderLayout.CENTER);
        infoDisplayPanel.setVisible(false);

        JPanel dayScrollPaneHolder = new JPanel(new BorderLayout());
        dayScrollPaneHolder.add(dayScrollPane,BorderLayout.CENTER);
        dayScrollPaneHolder.add(infoDisplayPanel,BorderLayout.SOUTH);
        dayScrollPaneHolder.setVisible(true);
        timetablePanel.add(taskButton, BorderLayout.SOUTH);
        timetablePanel.add(dayScrollPaneHolder, BorderLayout.CENTER);

        listPanel.add(nextButton, BorderLayout.SOUTH);
        listPanel.add(taskScrollPane, BorderLayout.CENTER);

        dayPanel.add(listPanel, "tasks");
        dayPanel.add(timetablePanel, "timetable");

        calendarPanel.add(calendar);

        infoPanel.add(calendarPanel);
        infoPanel.add(optionButton);
        infoPanel.add(editTaskButton);
        infoPanel.add(addNewTaskButton);



        nextButton.addActionListener(e->{
            cardLayout.show(dayPanel, "timetable");
        });

        taskButton.addActionListener(e->{
            cardLayout.show(dayPanel, "tasks");
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

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        mainPanel.add(infoPanel, BorderLayout.WEST);
        mainPanel.add(dayPanel, BorderLayout.CENTER);

        f.getContentPane().add(mainPanel);
        f.setVisible(true);
        f.setSize(screenSize.width/2,screenSize.height/2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {

        new Main();
    }

}

