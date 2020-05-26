package com.devPlugin.trelloSupport;

import com.devPlugin.trelloSupport.memory.ConnectionParams;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;
import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.io.IOException;
import java.util.*;

public class TrelloDataWorker {

    private Trello trello;
    private Member member;

    public TrelloDataWorker() throws IOException {
        ConnectionParams params = new ConnectionParams();
        trello = new TrelloImpl(params.getDevKey(), params.getTOKEN());
        member = trello.getMemberByToken(params.getTOKEN());
    }

    public String[] getBoards(){
        List<Board> boards = trello.getBoardsByMember(member.getId());
        String[] boardNames = new String[boards.size()];
        for (int i = 0; i < boardNames.length; i++) boardNames[i] = boards.get(i).getName();
        return boardNames;
    }

    public List<Card> getCards(String boardName){
        List<Board> boards = trello.getBoardsByMember(member.getId());
        Board board = new Board();
        for (int i = 0; i < boards.size(); i++) if(boards.get(i).getName().equals(boardName)) board = boards.get(i);

        return trello.getCardsByBoard(board.getId());
    }

    public String getListName(String id){
        org.trello4j.model.List list =  trello.getList(id);
        return list.getName();
    }

    public String getUserUrl(){
        return member.getUrl();
    }

    public String getDesckUrl(String deskName){
        List<Board> allBoards = trello.getBoardsByMember(member.getId());
        for (Board board: allBoards)
            if(board.getName().equals(deskName))
                return board.getUrl();

            return "";
    }

    public void createTask(String name,String column, String desk){
        List<Board> allBoards = trello.getBoardsByMember(member.getId());
        Board board = null;
        for (Board brd: allBoards)
            if(brd.getName().equals(desk)){
                board = brd;
            }

        if(board != null){
            boolean ok = false;
            for (org.trello4j.model.List list: trello.getListByBoard(board.getId()))
                if(list.getName().equals(column)){
                    ok = true;
                    trello.createCard(list.getId(), name, null);
                    break;
                }

            if (ok) Messages.showMessageDialog("This column doesn't' exist", "Invalid operation", Messages.getErrorIcon());
        }
        else Messages.showMessageDialog("This board doesn't' exist", "Invalid operation", Messages.getErrorIcon());
    }
}
