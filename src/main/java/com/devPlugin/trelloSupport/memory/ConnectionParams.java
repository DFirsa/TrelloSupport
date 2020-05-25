package com.devPlugin.trelloSupport.memory;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class ConnectionParams {

    private static final String DEV_KEY= "afc47f3bd589e313251d6382ca45d129";
    private static final String PATH = System.getProperty("user.dir") + "/.connection";
    private static String TOKEN = "";

    public ConnectionParams() throws IOException {
        readData();
    }

    public void logIn() throws IOException {
        getToken();
        FileWriter writer = new FileWriter(PATH, false);
        writer.append(TOKEN);
        writer.close();
    }

    public void logOut(){
        TOKEN="";
        File data = new File(PATH);
        data.delete();
    }

    private static void readData() throws IOException {
        File data = new File(PATH);
        data.createNewFile();

        BufferedReader br = new BufferedReader(new FileReader(data));
        String line = br.readLine();
        if(line != null) TOKEN = line.trim().replace("\n", "");
        br.close();
    }

    private static void getToken(){
        String link = "https://trello.com/1/connect?key=" + DEV_KEY + "&name=App&response_type=token";
        Messages.showMessageDialog("You'll redirect to your token. Copy it and post later",
                "Information", Messages.getInformationIcon());
        BrowserUtil.browse(link);
        TOKEN = Messages.showInputDialog("Enter your token", "Token input",
                null);
        TOKEN.trim().replace("\n", "");
    }

    public static String getDevKey(){return DEV_KEY;}

    public static String getTOKEN() {
        return TOKEN;
    }
}
