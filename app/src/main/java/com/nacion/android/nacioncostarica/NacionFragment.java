package com.nacion.android.nacioncostarica;

/**
 * Created by Gustavo Matarrita on 23/09/2014.
 */
public interface NacionFragment {
    void setFragmentIndex(int argIndex);
    int getFragmentIndex();
    String getTitle();
    void setTitle(String argTitle);
    String getSection();
    void setSection(String argSection);
    void reloadImage();
}
