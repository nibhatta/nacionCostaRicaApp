package com.nacion.android.nacioncostarica.models;

/**
 * Created by Gustavo Matarrita on 19/09/2014.
 */
public class Image {
    private String phoneUrl;
    private String tabPortraitUrl;
    private String tabLandscapeUrl;
    private String caption;

    public String getPhoneUrl() {
        return phoneUrl;
    }

    public void setPhoneUrl(String phoneUrl) {
        this.phoneUrl = phoneUrl;
    }

    public String getTabPortraitUrl() {
        return tabPortraitUrl;
    }

    public void setTabPortraitUrl(String tabPortraitUrl) {
        this.tabPortraitUrl = tabPortraitUrl;
    }

    public String getTabLandscapeUrl() {
        return tabLandscapeUrl;
    }

    public void setTabLandscapeUrl(String tabLandscapeUrl) {
        this.tabLandscapeUrl = tabLandscapeUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
