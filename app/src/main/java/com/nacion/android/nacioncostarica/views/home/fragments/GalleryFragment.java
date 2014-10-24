package com.nacion.android.nacioncostarica.views.home.fragments;

/**
 * Created by Gustavo Matarrita on 23/09/2014.
 */
public interface GalleryFragment {
    void setFragmentIndex(int index);
    int getFragmentIndex();
    String getTitle();
    void setTitle(String title);
    String getSection();
    void setSection(String section);
}
