package com.devPlugin.trelloSupport;

import com.devPlugin.trelloSupport.Views.TrelloView;
import com.devPlugin.trelloSupport.memory.ConnectionParams;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ToolWindowRealize implements ToolWindowFactory {

    private static final String DEV_KEY = "afc47f3bd589e313251d6382ca45d129";
    private static String TOKEN = "";

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        TrelloView panel = null;
        try {
            ConnectionParams params = new ConnectionParams();
            if(params.getTOKEN().equals(""))params.logIn();
            panel = new TrelloView(toolWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ContentFactory factory = ContentFactory.SERVICE.getInstance();
        Content content = factory.createContent(panel.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    private void getToken(){
        String link = "https://trello.com/1/connect?key=" + DEV_KEY + "&name=App&response_type=token";
        Messages.showMessageDialog("You'll redirect to your token. Copy it and post later",
                "Information", Messages.getInformationIcon());
        BrowserUtil.browse(link);
        TOKEN = Messages.showInputDialog("Enter your token", "Token input",
                null);
        TOKEN.trim().replace("\n", "");
    }
}
