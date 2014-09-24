package com.nacion.android.nacioncostarica.models;

import com.nacion.android.nacioncostarica.constants.NacionConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Module{
    private String type;
    private int order;
    private List<Content> contents;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public int getTypeCode(){
        int code = 0;
        if(type.equals(NacionConstants.MODULE_ONE)){
            code = NacionConstants.MODULE_CODE_ONE;
        }
        if(type.equals(NacionConstants.MODULE_TWO)){
            code = NacionConstants.MODULE_CODE_TWO;
        }
        return code;
    }

    public static Module createDummyModuleCore(String argType, int argContents){
        Module module = new Module();
        module.type = argType;
        module.contents = createDummyContentList(argContents, module);
        return module;
    }

    public static List<Content> createDummyContentList(int argContents, Module argModule){
        List<Content> contents = new ArrayList<Content>();
        for(int i=1; i<=argContents; i++) {
            contents.add(Content.createDummyContentCore(i, argModule));
        }
        return contents;
    }
}
