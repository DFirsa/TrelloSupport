package com.devPlugin.trelloSupport.Views;

import com.devPlugin.trelloSupport.TrelloGetter;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.ClickListener;
import com.intellij.ui.components.JBList;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.trello4j.model.Card;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class TrelloView extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JComboBox desks;
    private JButton show;

    private LinkedList<TaskView> taskList = new LinkedList<>();

    public TrelloView(ToolWindow toolWindow) throws IOException {
        TrelloGetter getter = new TrelloGetter();

        panel = new JPanel();
        label = new JLabel("Your desks: ");
        desks = new ComboBox(getter.getBoards());
        show = new JButton("Show tasks");

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show.setText("Loading...");
                for (TaskView task: taskList) panel.remove(task.getPanel());
                taskList = new LinkedList<>();

                String item = (String) desks.getSelectedItem();
                List<Card> cards = getter.getCards(item);
                for (Card crd: cards){
                    TaskView task = new TaskView(crd.getDesc(), crd.getName(),crd.getUrl());
                    taskList.add(task);
                    panel.add(task.getPanel());
                }
                show.setText("Show tasks");
            }
        });

//        desks.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String item = (String) desks.getSelectedItem();
//                List<Card> cards = getter.getCards(item);
//                for (Card crd: cards){
//                    TaskView task = new TaskView("", crd.getName());
//                    panel.add(task.getPanel());
//                }
//            }
//        });

        panel.add(label);
        panel.add(desks);
        panel.add(show);
    }

    public JPanel getContent(){
        return panel;
    }
}
