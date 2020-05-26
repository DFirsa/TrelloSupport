package com.devPlugin.trelloSupport.Views;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.VerticalFlowLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

public class TaskView extends JFrame {

    private JPanel taskDescriptionPanel;

    public TaskView(String _taskDescription, String taskName, String url, String columnName) {

        taskDescriptionPanel = new JPanel(new VerticalFlowLayout());
        taskDescriptionPanel.setBorder(BorderFactory.createTitledBorder(""));

        JLabel taskLabel = new JLabel(taskName);
        taskLabel.setFont(new Font("Verdana",Font.CENTER_BASELINE,12));
        taskDescriptionPanel.add(taskLabel);

        JLabel column = new JLabel("Column: " + columnName);
        column.setFont(new Font("Verdana", Font.PLAIN, 12));
        taskDescriptionPanel.add(column);

        JTextArea taskDescription = new JTextArea(_taskDescription);
        taskDescription.setFont(new Font("Verdana",Font.ITALIC,12));
        taskDescriptionPanel.add(taskDescription);

//        if(end != null){
//            JLabel deadline = new JLabel("Deadline: " + end.toString());
//            taskDescriptionPanel.add(deadline);
//        }

        taskDescriptionPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1)
                    taskDescriptionPanel.setBackground(Color.green);

                if(e.getButton() == MouseEvent.BUTTON3)
                    BrowserUtil.browse(url);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return taskDescriptionPanel;
    }
}
