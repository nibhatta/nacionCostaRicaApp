package com.nacion.android.nacioncostarica.views.content.fragments;

/**
 * Created by Gustavo Matarrita on 23/09/2014.
 */
public interface ContentFragment {
    void setFragmentIndex(int index);
    int getFragmentIndex();
    String getPhotoAuthor();
    void setPhotoAuthor(String photoAuthor);
    String getTitle();
    void setTitle(String title);
}
