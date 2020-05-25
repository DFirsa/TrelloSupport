package com.devPlugin.trelloSupport;

import com.devPlugin.trelloSupport.memory.ConnectionParams;
import org.jetbrains.annotations.NotNull;
import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.io.IOException;
import java.util.*;

public class TrelloGetter {

    private Trello trello;
    private Member member;

    public TrelloGetter() throws IOException {
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
}
