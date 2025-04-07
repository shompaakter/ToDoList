package juiapplication;

import java.awt.*;
import javax.swing.*;

public class main {
    public JFrame frame;
    public DefaultListModel<String> tasklistmodel;
    public JList<String> taskList;
    public DefaultListModel<String> completedlistmodel;
    public DefaultListModel<String> deletedlistmodel;
    public DefaultListModel<String> checkedlistmodel;
    public JList<String> checkedlist;
    public JTextField taskinput;

    public main() {
        frame = new JFrame("To-Do List");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        taskinput = new JTextField();
        taskinput.setBounds(10, 10, 550, 25);
        frame.add(taskinput);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(570, 10, 100, 25);
        addTaskButton.setBackground(Color.WHITE);
        addTaskButton.addActionListener(e -> addTask());
        frame.add(addTaskButton);

        tasklistmodel = new DefaultListModel<>();
        taskList = new JList<>(tasklistmodel);
        JScrollPane taskScrollPane = new JScrollPane(taskList);
        taskScrollPane.setBounds(10, 40, 660, 100);
        frame.add(taskScrollPane);

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.setBounds(10, 150, 150, 30);
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteTask());
        frame.add(deleteButton);

        JButton completeButton = new JButton("Mark as Completed");
        completeButton.setBounds(170, 150, 180, 30);
        completeButton.setBackground(Color.BLACK);
        completeButton.setForeground(Color.WHITE);
        completeButton.addActionListener(e -> markAsCompleted());
        frame.add(completeButton);

        JButton progressButton = new JButton("In progress");
        progressButton.setBounds(360, 150, 150, 30);
        progressButton.setBackground(Color.BLACK);
        progressButton.setForeground(Color.WHITE);
        progressButton.addActionListener(e -> checkTask());
        frame.add(progressButton);

        JButton uncheckButton = new JButton("Uncheck Task");
        uncheckButton.setBounds(520, 150, 150, 30);
        uncheckButton.setBackground(Color.BLACK);
        uncheckButton.setForeground(Color.WHITE);
        uncheckButton.addActionListener(e -> uncheckTask());
        frame.add(uncheckButton);

        JLabel checkedLabel = new JLabel("In progress list:");
        checkedLabel.setBounds(10, 190, 200, 20);
        frame.add(checkedLabel);

        checkedlistmodel = new DefaultListModel<>();
        checkedlist = new JList<>(checkedlistmodel );
        JScrollPane checkedScrollPane = new JScrollPane(checkedlist);
        checkedScrollPane.setBounds(10, 210, 320, 100);
        frame.add(checkedScrollPane);

        JLabel completedLabel = new JLabel("Completed Tasks:");
        completedLabel.setBounds(350, 190, 200, 20);
        frame.add(completedLabel);

        completedlistmodel = new DefaultListModel<>();
        JList<String> completedList = new JList<>(completedlistmodel);
        JScrollPane completedScrollPane = new JScrollPane(completedList);
        completedScrollPane.setBounds(350, 210, 320, 100);
        frame.add(completedScrollPane);

        JLabel deletedLabel = new JLabel("Deleted Tasks:");
        deletedLabel.setBounds(10, 320, 200, 20);
        frame.add(deletedLabel);

        deletedlistmodel = new DefaultListModel<>();
        JList<String> deletedList = new JList<>(deletedlistmodel);
        JScrollPane deletedScrollPane = new JScrollPane(deletedList);
        deletedScrollPane.setBounds(10, 340, 660, 100);
        frame.add(deletedScrollPane);

        frame.setVisible(true);
    }

    public void addTask() {
        String task = taskinput.getText().trim().replaceAll("\\s+", " ");;
        if (!task.isEmpty()) {
            tasklistmodel.addElement(task);
            taskinput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Enter a task!");
        }
    }
    public void deleteTask() {
        if (!completedlistmodel.isEmpty()) {
            String task = completedlistmodel.remove(completedlistmodel.getSize() - 1);
            deletedlistmodel.addElement(task);
        } else {
            JOptionPane.showMessageDialog(frame, "No completed tasks to delete!");
        }
    }
  

    public void markAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String task = tasklistmodel.getElementAt(selectedIndex);
            completedlistmodel.addElement(task);
            tasklistmodel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Select a task to mark as completed!");
        }
    }

    public void checkTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String task = tasklistmodel.getElementAt(selectedIndex);
            checkedlistmodel.addElement(task);
            tasklistmodel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Select a task to check!");
        }
    }

    public void uncheckTask() {
        if (!checkedlistmodel.isEmpty()) {
            String task = checkedlistmodel.remove(checkedlistmodel.getSize() - 1);
            tasklistmodel.addElement(task);
        } else {
            JOptionPane.showMessageDialog(frame, "No tasks to uncheck!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(main::new);
    }
}
