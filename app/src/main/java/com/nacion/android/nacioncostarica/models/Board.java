package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Board{

    private int id;
    private List<Module> modules;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<ContentItemList> getAllContentsForPhoneDevice(){
        List<ContentItemList> contents = new ArrayList<ContentItemList>();
        for(Module module : modules){
            if(!module.isContentToDisplayOnPhone()){
                continue;
            }
            if(module.isAGalleryForPhoneDevice()){
                addComplexRowContent(module, contents);
            }else{
                addSingleRowContent(module, contents);
            }
        }
        return contents;
    }

    public List<ContentItemList> getAllContentsForTabletDevice(){
        List<ContentItemList> contents = new ArrayList<ContentItemList>();
        for(Module module : modules){
            if(!module.isContentToDisplayOnTablet()){
                continue;
            }
            if(module.isAGalleryForTabletDevice()){
                addComplexRowContent(module, contents);
            }else{
                addSingleRowContent(module, contents);
            }
        }
        return contents;
    }

    private void addSingleRowContent(Module argModule, List<ContentItemList> argContents){
        if(moduleHasListItem(argModule)){
            for (Content content : argModule.getContents()) {
                argContents.add(content);
            }
        }else {
            argContents.add(argModule.getContentModule());
        }
    }

    private boolean moduleHasListItem(Module argModule){
        return(argModule.getContents() != null && !argModule.getContents().isEmpty());
    }

    private void addComplexRowContent(Module argModule, List<ContentItemList> argContents){
        argContents.add(createContentComplex(argModule));
    }

    private ContentComplex createContentComplex(Module argModule){
        ContentComplex complex = new ContentComplex();
        complex.setModule(argModule);
        return complex;
    }

    public List<Board> buildBoardListFromJSONObject(JSONArray argBoards){
        List<Board> boards = new ArrayList<Board>();
        int size = argBoards.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonBoard = argBoards.getJSONObject(i);
                Board board = buildBoardFromJSONObject(jsonBoard);
                boards.add(board);
            }
        }catch(JSONException e){
            Log.d(Board.class.getName(), e.getLocalizedMessage());
        }
        return boards;
    }

    public Board buildBoardFromJSONObject(JSONObject argJSONBoard) throws JSONException{
        Board board = new Board();
        Iterator<String> iterator = argJSONBoard.keys();
        String key = (iterator.hasNext())? iterator.next() : null;
        if(key != null){
            board.id = Integer.parseInt(key);
            JSONObject jsonBoard = argJSONBoard.getJSONObject(key);
            JSONArray jsonModules = jsonBoard.getJSONArray("modules");
            board.modules = new Module().buildModuleListFromJSONObject(jsonModules);
        }
        return board;
    }
}
