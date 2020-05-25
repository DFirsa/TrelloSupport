package com.devPlugin.trelloSupport;

import com.devPlugin.trelloSupport.memory.ConnectionParams;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LogIn extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try{
            ConnectionParams params = new ConnectionParams();
            if(params.getTOKEN().equals(""))params.logIn();
            else Messages.showMessageDialog("You are already login", "", Messages.getErrorIcon());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
