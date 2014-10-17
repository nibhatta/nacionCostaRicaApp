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
public class Module{

    private static final List<String> GALLERY_MODULES_ON_PHONE = new ArrayList<String>(){
        {
            add(NacionConstants.MODULE_ONE);
            add(NacionConstants.MODULE_THREE);
            add(NacionConstants.MODULE_EIGHT);
        }
    };

    private final static List<String> CONTENTS_FOR_PHONE = new ArrayList<String>(){
        {
            add(NacionConstants.MODULE_ONE);
            add(NacionConstants.MODULE_TWO);
            add(NacionConstants.MODULE_THREE);
            add(NacionConstants.MODULE_FOURTH);
            add(NacionConstants.MODULE_FIVE);
            add(NacionConstants.MODULE_EIGHT);
        }
    };

    private static final List<String> GALLERY_MODULES_ON_TABLET = new ArrayList<String>(){
        {
            add(NacionConstants.MODULE_ONE);
            add(NacionConstants.MODULE_TWO);
            add(NacionConstants.MODULE_THREE);
            add(NacionConstants.MODULE_SIX);
            add(NacionConstants.MODULE_SEVEN);
            add(NacionConstants.MODULE_EIGHT);
            add(NacionConstants.MODULE_TEN);
        }
    };

    private final static List<String> CONTENTS_FOR_TABLET = new ArrayList<String>(){
        {
            add(NacionConstants.MODULE_ONE);
            add(NacionConstants.MODULE_TWO);
            add(NacionConstants.MODULE_THREE);
            add(NacionConstants.MODULE_FOURTH);
            add(NacionConstants.MODULE_SIX);
            add(NacionConstants.MODULE_SEVEN);
            add(NacionConstants.MODULE_EIGHT);
            add(NacionConstants.MODULE_TEN);
            add(NacionConstants.MODULE_ELEVEN);
        }
    };

    private String type;
    private int order;
    private int count;
    private ContentModule contentModule;
    private List<Content> contents;

    public ContentModule getContentModule() {
        return contentModule;
    }

    public void setContentModule(ContentModule contentModule) {
        this.contentModule = contentModule;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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
        if(NacionConstants.MODULES.containsKey(type)) {
            code = NacionConstants.MODULES.get(type);
        }
        return code;
    }

    public boolean isAGalleryForPhoneDevice(){
        return GALLERY_MODULES_ON_PHONE.contains(type);
    }

    public boolean isAGalleryForTabletDevice(){
        return GALLERY_MODULES_ON_TABLET.contains(type);
    }

    public boolean isContentToDisplayOnPhone(){
        return CONTENTS_FOR_PHONE.contains(type);
    }

    public boolean isContentToDisplayOnTablet(){
        return CONTENTS_FOR_TABLET.contains(type);
    }

    public List<Module> buildModuleListFromJSONObject(JSONArray argModules){
        List<Module> modules = new ArrayList<Module>();
        int size = argModules.length();
        try {
            for (int i = 0; i < size; i++) {
                JSONObject jsonModule = argModules.getJSONObject(i);
                Module module = buildModuleFromJSONObject(jsonModule);
                modules.add(module);
            }
        }catch(JSONException e){
            Log.d(Module.class.getName(), e.getLocalizedMessage());
        }
        return modules;
    }

    public Module buildModuleFromJSONObject(JSONObject argJSONModule) throws JSONException{
        Module module = new Module();
        module.setType(argJSONModule.getString("type"));
        module.setOrder(argJSONModule.getInt("order"));

        String countTag = "count";
        if(argJSONModule.has(countTag)) {
            module.setCount(argJSONModule.getInt(countTag));
        }

        String contentsTag = "contents";
        if(argJSONModule.has(contentsTag)) {
            JSONObject contentModuleJSON = argJSONModule.getJSONObject(contentsTag);
            ContentModule contentModule = new ContentModule().buildContentModuleFromJSONObject(contentModuleJSON);
            contentModule.setModule(module);
            module.setContentModule(contentModule);
        }

        String listTag = "list";
        if(argJSONModule.has(listTag)){
            JSONArray jsonArray = argJSONModule.getJSONArray(listTag);
            List<Content> contents = new Content().buildContentListFromJSONObject(jsonArray, module);
            module.setContents(contents);
        }

        return module;
    }
}
