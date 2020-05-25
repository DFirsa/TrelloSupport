package com.devPlugin.trelloSupport.Actions;

import com.devPlugin.trelloSupport.memory.ConnectionParams;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LogOut extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try{
            ConnectionParams params = new ConnectionParams();
            if(!params.getTOKEN().equals(""))params.logOut();
            else Messages.showMessageDialog("You are already logout", "", Messages.getErrorIcon());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
