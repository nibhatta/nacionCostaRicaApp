package com.nacion.android.nacioncostarica;

/**
 * Created by Gustavo Matarrita on 23/09/2014.
 */
public interface NacionFragment {
    void setFragmentIndex(int index);
    int getFragmentIndex();
    String getTitle();
    void setTitle(String title);
    String getSection();
    void setSection(String section);
    int getBoardId();
    void setBoardId(int boardId);
}
