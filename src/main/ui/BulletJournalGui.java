package ui;

import model.Event;
import model.EventLog;
import model.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// YouTube tutorials were used to help construct this class
// 1. https://www.youtube.com/watch?v=Kmgo00avvEw
// 2. https://www.youtube.com/watch?v=5o3fMLPY7qY&t=338s

// Other sources:
// 1. https://stackoverflow.com/questions/9347076/how-to-remove-all-components-from-a-jframe-in-java
// 2. https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/
//    uiswing/examples/components/TextDemoProject/src/components/TextDemo.java
// 3. https://stackoverflow.com/questions/1842223/java-linebreaks-in-jlabels
// 4. https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
// 5. https://stackoverflow.com/questions/16295942/java-swing-adding-action-listener-for-exit-on-close

// This class represents the GUI for the Bullet Journal
public class BulletJournalGui extends JFrame implements ActionListener {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 700;
    private JPanel panel;
    private JLabel title;
    private JLabel printBujo;
    private JButton newPage;
    private JButton save;
    private JButton load;
    private JButton view;
    private JButton todo;
    private JButton journal;
    private JButton submitTodoList;
    private JButton submitJournal;
    private JButton returnToMenu;
    private JTextField todoTask;
    private JTextField todoDeadline;
    private JTextField journalEntry;
    private final ImageIcon titleImage = new ImageIcon("./data/bulletjournaltitle.png");

    private JTextField pageNum;
    private JButton submitPreviousTodoList;
    private JButton submitPreviousJournal;
    private JButton enter;
    private JButton previousPage;
    private JButton todoPrevious;
    private JButton journalPrevious;
    private BulletJournalApp bulletJournalApp;


    // EFFECTS: initializes all the components
    public BulletJournalGui() {
        try {
            bulletJournalApp = new BulletJournalApp();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        initializeGraphics();
        initializeCover();
    }



    // MODIFIES: this
    // EFFECTS: intilizes the graphics for the app and gui
    private void initializeGraphics() {
        setTitle("Bullet Journal");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(WIDTH, HEIGHT));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                e.getWindow().dispose();
            }
        });
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    public void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            System.out.println(next.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: clears the panel of the gui
    private void clearPanel() {
        panel.removeAll();
        revalidate();
        repaint();
        panel.setLayout(null);
    }


    // MODIFIES: this
    // EFFECTS: initializes the cover/title of the bullet journal and the menu page with menu options
    private void initializeCover() {
        clearPanel();
        panel.setLayout(null);
        drawTitle();
        drawMenuPage();
    }


    // MODIFIES: this
    // EFFECTS: draws the menu page for the bullet journal with previous page, new page, view, save, and load option
    private void drawMenuPage() {
        newPage = new JButton("New Page");
        newPage.setBounds(312, 350, 400, 50);
        newPage.addActionListener(this);

        previousPage = new JButton("Previous Page");
        previousPage.setBounds(312, 400, 400, 50);
        previousPage.addActionListener(this);

        view = new JButton("View Bullet Journal");
        view.setBounds(312, 450, 400, 50);
        view.addActionListener(this);

        save = new JButton("Save");
        save.setBounds(312, 500, 400, 50);
        save.addActionListener(this);

        load = new JButton("Load");
        load.setBounds(312, 550, 400, 50);
        load.addActionListener(this);

        panel.add(newPage);
        panel.add(previousPage);
        panel.add(view);
        panel.add(save);
        panel.add(load);
    }


    // MODIFIES: this
    // EFFECTS: intializes the panel with menu options
    private void intializeEditPageMenuOptions() {
        clearPanel();
        panel.setLayout(null);
        drawEditPageMenu();
    }

    // MODIFIES:
    // EFFECTS: draws a menu that provides editing options for previous menu (to-do list and journal entry)
    private void drawEditPageMenu() {
        todo = new JButton("Todo List");
        todo.setBounds(312, 350, 400, 50);
        todo.addActionListener(this);

        journal = new JButton("Journal Entry");
        journal.setBounds(312, 400, 400, 50);
        journal.addActionListener(this);

        returnToMenu = new JButton("Return to display menu");
        returnToMenu.setBounds(312, 450, 400, 50);
        returnToMenu.addActionListener(this);

        panel.add(todo);
        panel.add(journal);
        panel.add(returnToMenu);
    }

    // MODIFIES: this
    // EFFECTS: intializes the panel with to-do list textfields
    private void initializeTodoList() {
        clearPanel();
        panel.setLayout(null);
        drawToDoList();
    }

    // MODIFIES: this
    // EFFECTS: draws a panel for to-do list textfields (description and deadline)
    private void drawToDoList() {
        JLabel task = new JLabel("Please enter a task:");
        task.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 200, 500, 20);

        todoTask = new JTextField();
        todoTask.setPreferredSize(new Dimension(200, 10));
        todoTask.setBounds(WIDTH / 2 - 125, HEIGHT / 2 - 170, 250, 40);
        todoTask.addActionListener(this);

        JLabel deadline = new JLabel("Please enter a deadline:");
        deadline.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 80, 500, 20);

        todoDeadline = new JTextField();
        todoDeadline.setPreferredSize(new Dimension(200, 10));
        todoDeadline.setBounds(WIDTH / 2 - 125, HEIGHT / 2 - 50, 250, 40);
        todoDeadline.addActionListener(this);

        submitTodoList = new JButton("Submit");
        submitTodoList.setBounds(312, 500, 400, 50);
        submitTodoList.addActionListener(this);

        panel.add(task);
        panel.add(todoTask);
        panel.add(deadline);
        panel.add(todoDeadline);
        panel.add(submitTodoList);
    }

    // MODIFIES: this
    // EFFECTS: intializes the panel with journal entry textfields
    private void initializeJournalEntry() {
        clearPanel();
        panel.setLayout(null);
        drawJournalEntry();

    }

    // MODIFIES: this
    // EFFECTS: draws a panel for journal entry textfields to enter text
    private void drawJournalEntry() {
        JLabel enterJournal = new JLabel("Please enter a journal entry:");
        enterJournal.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 150, 500, 20);

        journalEntry = new JTextField();
        journalEntry.setPreferredSize(new Dimension(200, 10));
        journalEntry.setBounds(WIDTH / 2 - 250, HEIGHT / 2 - 100, 500, 100);

        submitJournal = new JButton("Submit");
        submitJournal.setBounds(312, 500, 400, 50);
        submitJournal.addActionListener(this);

        panel.add(enterJournal);
        panel.add(journalEntry);
        panel.add(submitJournal);
    }

    // MODIFIES: this
    // EFFECTS: draws the title image on the cover of the app
    private void drawTitle() {
        title = new JLabel();
        title.setIcon(titleImage);
        title.setBounds(81 / 2, JLabel.TOP, 943, 294);
//        title.setHorizontalAlignment(JLabel.CENTER);
//        title.setVerticalAlignment(JLabel.TOP);

        panel.add(title);
    }


    // MODIFIES: this
    // EFFECTS: intializes the panel so that the bullet journal can be viewed
    private void viewBulletJournal() {
        clearPanel();
        panel.setLayout(null);

        String bulletJournalString = "";
        ArrayList<Page> pageList = bulletJournalApp.getBulletJournalClass().getBulletJournalField();

        bulletJournalString = bulletJournalString.concat("<html>");
        for (Page p : pageList) {
            bulletJournalString = bulletJournalString.concat("<br>").concat("Todo List: ").concat("<br>")
                    .concat(p.getTodoList().viewTodoList()).concat("<br>")
                    .concat("Journal: ").concat("<br>").concat(p.getJournalEntry());

        }
        bulletJournalString = bulletJournalString.concat("</html>");

        printBujo = new JLabel(bulletJournalString);

        printBujo.setBounds(100, 0, WIDTH, 700);

        returnToMenu = new JButton("Return to display menu");
        returnToMenu.setBounds(312, 500, 400, 50);
        returnToMenu.addActionListener(this);

        panel.add(printBujo);
        panel.add(returnToMenu);
    }

    // MODIFIES: this
    // EFFECTS: draws a menu that provides editing options for previous menu (to-do list and journal entry)
    public void intializeEditPreviousPageMenuOptions() {
        clearPanel();
        panel.setLayout(null);
        drawEditPreviousPageMenuOptions();
    }

    public void drawEditPreviousPageMenuOptions() {
        todoPrevious = new JButton("To-do List");
        todoPrevious.setBounds(312, 350, 400, 50);
        todoPrevious.addActionListener(this);

        journalPrevious = new JButton("Journal Entry");
        journalPrevious.setBounds(312, 400, 400, 50);
        journalPrevious.addActionListener(this);

        returnToMenu = new JButton("Return to display menu");
        returnToMenu.setBounds(312, 450, 400, 50);
        returnToMenu.addActionListener(this);

        panel.add(todoPrevious);
        panel.add(journalPrevious);
        panel.add(returnToMenu);
    }


    // MODIFIES: this
    // EFFECTS: intializes a page to enter the previous page number you want to edit
    private void initializePreviousPageSelection() {
        clearPanel();
        panel.setLayout(null);
        drawPreviousPageSelection();
    }

    // MODIFIES: this
    // EFFECTS: draws a page to enter the previous page number you want to edit
    private void drawPreviousPageSelection() {
        JLabel enterPageNum = new JLabel("Page number to edit:");
        enterPageNum.setBounds(WIDTH / 2 - 250, HEIGHT / 2 - 200, 500, 100);

        pageNum = new JTextField();
        pageNum.setPreferredSize(new Dimension(200, 10));
        pageNum.setBounds(WIDTH / 2 - 250, HEIGHT / 2 - 100, 500, 40);
        pageNum.addActionListener(this);

        enter = new JButton("Enter");
        enter.setBounds(312, 500, 400, 50);
        enter.addActionListener(this);

        panel.add(enterPageNum);
        panel.add(pageNum);
        panel.add(enter);
    }

    // MODIFIES: this
    // EFFECTS: intializes the panel with (previous) to-do list textfields
    private void initializePreviousTodoList() {
        clearPanel();
        panel.setLayout(null);
        drawPreviousTodoList();
    }


    // MODIFIES: this
    // EFFECTS: draws a panel for (previous) to-do list textfields (description and deadline)
    private void drawPreviousTodoList() {
        JLabel task = new JLabel("Please enter a task:");
        task.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 200, 500, 20);

        todoTask = new JTextField();
        todoTask.setPreferredSize(new Dimension(200, 10));
        todoTask.setBounds(WIDTH / 2 - 125, HEIGHT / 2 - 170, 250, 40);
        todoTask.addActionListener(this);

        JLabel deadline = new JLabel("Please enter a deadline:");
        deadline.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 80, 500, 20);

        todoDeadline = new JTextField();
        todoDeadline.setPreferredSize(new Dimension(200, 10));
        todoDeadline.setBounds(WIDTH / 2 - 125, HEIGHT / 2 - 50, 250, 40);
        todoDeadline.addActionListener(this);

        submitPreviousTodoList = new JButton("Submit");
        submitPreviousTodoList.setBounds(312, 500, 400, 50);
        submitPreviousTodoList.addActionListener(this);

        panel.add(task);
        panel.add(todoTask);
        panel.add(deadline);
        panel.add(todoDeadline);
        panel.add(submitPreviousTodoList);
    }


    // MODIFIES: this
    // EFFECTS: intializes the panel with journal entry textfields to edit previous entry
    private void initializePreviousJournalEntry() {
        clearPanel();
        panel.setLayout(null);
        drawPreviousJournalEntry();
    }

    // MODIFIES: this
    // EFFECTS: draws a panel for journal entry textfields to enter text (edit previous journal)
    private void drawPreviousJournalEntry() {
        JLabel enterJournal = new JLabel("Please enter a journal entry:");
        enterJournal.setBounds(WIDTH / 2 - 90, HEIGHT / 2 - 150, 500, 20);

        journalEntry = new JTextField();
        journalEntry.setPreferredSize(new Dimension(200, 10));
        journalEntry.setBounds(WIDTH / 2 - 250, HEIGHT / 2 - 100, 500, 100);

        submitPreviousJournal = new JButton("Submit");
        submitPreviousJournal.setBounds(312, 500, 400, 50);
        submitPreviousJournal.addActionListener(this);

        panel.add(enterJournal);
        panel.add(journalEntry);
        panel.add(submitPreviousJournal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newPage) {
            bulletJournalApp.addPageToBulletJournal();
            intializeEditPageMenuOptions();
        }
        if (e.getSource() == view) {
            viewBulletJournal();
        }
        if (e.getSource() == save) {
            bulletJournalApp.saveBulletJournal();
        }
        if (e.getSource() == load) {
            bulletJournalApp.loadBulletJournal();
        }
        if (e.getSource() == todo) {
            initializeTodoList();
        } else {
            actionPerformedTwo(e);
        }
    }

    public void actionPerformedTwo(ActionEvent e) {
        if (e.getSource() == journal) {
            initializeJournalEntry();
        }
        if (e.getSource() == submitTodoList) {
            bulletJournalApp.editToDoList(todoTask.getText(), todoDeadline.getText());
            intializeEditPageMenuOptions();
        }
        if (e.getSource() == submitJournal) {
            bulletJournalApp.editJournalEntry(journalEntry.getText());
            intializeEditPageMenuOptions();
        }
        if (e.getSource() == returnToMenu) {
            initializeCover();
        } else {
            actionPerformedPrevious(e);
        }
    }

    public void actionPerformedPrevious(ActionEvent e) {

        if (e.getSource() == previousPage) {
            if (bulletJournalApp.getBulletJournalClass().length() <= 1) {
                initializeCover();
            } else {
                initializePreviousPageSelection();
            }
        }
        if (e.getSource() == submitPreviousTodoList) {
            int pageNumInteger = Integer.parseInt(pageNum.getText());
            bulletJournalApp.editPreviousTodoList(todoTask.getText(), todoDeadline.getText(), pageNumInteger);
            intializeEditPageMenuOptions();
        }
        if (e.getSource() == submitPreviousJournal) {
            int pageNumInteger = Integer.parseInt(pageNum.getText());
            bulletJournalApp.editPreviousJournalEntry(journalEntry.getText(), pageNumInteger);
            intializeEditPageMenuOptions();
        } else {
            actionPerformedPreviousTwo(e);
        }
    }

    public void actionPerformedPreviousTwo(ActionEvent e) {

        if (e.getSource() == enter) {
            intializeEditPreviousPageMenuOptions();
        }

        if (e.getSource() == todoPrevious) {
            initializePreviousTodoList();
        }

        if (e.getSource() == journalPrevious) {
            initializePreviousJournalEntry();
        }

    }
}


