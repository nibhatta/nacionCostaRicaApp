package com.nacion.android.nacioncostarica.models;

import android.util.Log;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    public static Board createDummyBoardCoreForPhone(){
        Board board = new Board();
        board.id = 1;
        board.modules = new ArrayList<Module>();
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_ONE, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_TWO, 5));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_THREE, 4));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_TWO, 5));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_FOURTH, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_FIVE, 4));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_SIX, 1));
        return board;
    }

    public static Board createDummyBoardCoreForTablet(){
        Board board = new Board();
        board.id = 1;
        board.modules = new ArrayList<Module>();
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_ONE, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_TWO, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_THREE, 4));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_TWO, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_FOURTH, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_SEVEN, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_EIGHT, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_TWO, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_NINE, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_TEN, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_ELEVEN, 1));
        board.modules.add(Module.createDummyModuleCore(NacionConstants.MODULE_SIX, 1));

        return board;
    }

    public List<ContentItemList> getAllContents(){
        List<ContentItemList> contents = new ArrayList<ContentItemList>();
        for(Module module : modules){
            if(module.isAGallery()){
                contents.add(createContentGallery(module));
            }else{
                addSingleRowContent(module, contents);
            }
        }
        return contents;
    }

    public List<ContentItemList> getAllContentsForPhone(){
        List<ContentItemList> contents = new ArrayList<ContentItemList>();
        for(Module module : modules){
            if(!module.isContentToDisplayOnPhone()){
                continue;
            }

            if(module.isAGallery()){
                contents.add(createContentGallery(module));
            }else{
                addSingleRowContent(module, contents);
            }
        }
        return contents;
    }

    private void addSingleRowContent(Module argModule, List<ContentItemList> argContents){
        for(Content content : argModule.getContents()){
            argContents.add(content);
        }
    }

    private ContentGallery createContentGallery(Module argModule){
        ContentGallery gallery = new ContentGallery();
        gallery.setModule(argModule);
        gallery.setContents(argModule.getContents());
        return gallery;
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
