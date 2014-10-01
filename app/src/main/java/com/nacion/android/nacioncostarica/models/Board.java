package com.nacion.android.nacioncostarica.models;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

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
}
