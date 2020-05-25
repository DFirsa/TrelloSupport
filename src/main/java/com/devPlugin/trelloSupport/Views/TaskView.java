package com.devPlugin.trelloSupport.Views;

import com.intellij.ide.BrowserUtil;
import com.intellij.ui.ClickListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class TaskView {
    private static final JButton sendButton = new JButton("Send");
    private static final JButton commentButton = new JButton("Comment");
    private static final JCheckBox isDone = new JCheckBox("is Done");;
    private JPanel panel;
    private JLabel taskTest;
    private JPanel mainPanel;

    public TaskView(String _taskDescription,String taskName, String url) {
        Font font = new Font("Verdana",Font.ITALIC,12);

        JPanel taskButtonsPanel = new JPanel(new FlowLayout());
        JPanel taskDescriptionPanel = new JPanel(new FlowLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                BrowserUtil.browse(url);
                taskDescriptionPanel.setBackground(Color.GREEN);
            }
        });


        taskDescriptionPanel.setBorder(BorderFactory.createTitledBorder(""));

        JTextArea taskDescription = new JTextArea("=== "+ taskName + " === \n" + _taskDescription);


        taskDescription.setFont(font);
        taskDescriptionPanel.add(taskDescription);



        taskButtonsPanel.add(isDone);
        taskButtonsPanel.add(commentButton);
//        taskDescriptionPanel.add(isDone);
//        taskDescriptionPanel.add(commentButton);

        mainPanel.add(taskDescriptionPanel,BorderLayout.NORTH);
        mainPanel.add(taskButtonsPanel,BorderLayout.CENTER);

    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
