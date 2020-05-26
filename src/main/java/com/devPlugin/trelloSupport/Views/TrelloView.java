package com.devPlugin.trelloSupport.Views;

import com.devPlugin.trelloSupport.TrelloDataWorker;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.wm.ToolWindow;
import org.trello4j.model.Card;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TrelloView extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JComboBox desks;
    private JButton show;
    private TrelloDataWorker getter;

    private LinkedList<TaskView> taskList = new LinkedList<>();

    public TrelloView(ToolWindow toolWindow) throws IOException {
        getter = new TrelloDataWorker();

        panel = new JPanel(new VerticalFlowLayout());

        JMenuBar toolBar = new JMenuBar();
        toolBar.setBorderPainted(true);
        JButton openAll = new JButton("Browse all desks");
        JButton openCurrent = new JButton("Browse current desk");
        JButton addTask = new JButton("Add task in current desk");
        JButton refresh = new JButton("Refresh");

        openAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowserUtil.browse(getter.getUserUrl() + "/boards");
            }
        });

        openCurrent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowserUtil.browse(getter.getDesckUrl((String) desks.getSelectedItem()));
            }
        });

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refresh();
                    showT();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        addTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String column =  Messages.showInputDialog("", "column", null);
               column.trim().replace("\n","");
               String card = Messages.showInputDialog("", "name", null);
               getter.createTask(card, column, (String) desks.getSelectedItem());
            }
        });

        toolBar.add(openAll);
        toolBar.add(openCurrent);
        toolBar.add(addTask);
        toolBar.add(refresh);

        panel.add(toolBar);

        JPanel deskPanel = new JPanel();
        label = new JLabel("Your desks: ");
        desks = new ComboBox(getter.getBoards());
        show = new JButton("Show tasks");

        deskPanel.add(label);
        deskPanel.add(desks);
        deskPanel.add(show);

        panel.add(deskPanel);

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showT();
            }
        });

//        panel.add(scrollable);
    }

    private void refresh() throws IOException {
        getter = new TrelloDataWorker();
    }

    private void showT(){
        show.setText("Loading...");
        for (TaskView task: taskList) panel.remove(task.getPanel());
        taskList = new LinkedList<>();

        String item = (String) desks.getSelectedItem();
        List<Card> cards = getter.getCards(item);

        for (Card crd: cards){

            TaskView task = new TaskView(crd.getDesc(), crd.getName(),crd.getUrl(),
                    getter.getListName(crd.getIdList()));
            taskList.add(task);
            panel.add(task.getPanel());
//                    scrollable.add(task.getPanel());
        }
        show.setText("Show tasks");
    }

    public JPanel getContent(){
        return panel;
    }
}
