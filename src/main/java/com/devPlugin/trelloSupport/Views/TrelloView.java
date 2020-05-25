package com.devPlugin.trelloSupport.Views;

import com.devPlugin.trelloSupport.TrelloGetter;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import javafx.scene.layout.Pane;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class BoardsChooseView extends JFrame {

    private JPanel panel;
    private JLabel label;
    private JComboBox desks;
    private JPanel child;

    public BoardsChooseView(ToolWindow toolWindow) throws IOException {
        TrelloGetter getter = new TrelloGetter();

        panel = new JPanel();

        label = new JLabel("Your desks: ");
        desks = new ComboBox(getter.getBoards());

        panel.add(label);
        panel.add(desks);
    }

    public JPanel getContent(){
        return panel;
    }
}
