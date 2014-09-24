package com.nacion.android.nacioncostarica.models;

import java.util.ArrayList;
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

    public static Board createDummyBoardCore(){
        Board board = new Board();
        board.id = 1;
        board.modules = new ArrayList<Module>();
        board.modules.add(Module.createDummyModuleCore("destacado", 1));
        board.modules.add(Module.createDummyModuleCore("listado", 5));
        return board;
    }

    public List<Content> getAllContents(){
        List<Content> contents = new ArrayList<Content>();
        for(Module module : modules){
            for(Content content : module.getContents()){
                contents.add(content);
            }
        }
        return contents;
    }
}
