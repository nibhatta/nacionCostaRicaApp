package com.nacion.android.nacioncostarica.views.home.listeners;

/**
 * Created by Gustavo Matarrita on 31/10/2014.
 */
public abstract class MenuListener {
    private static int startPosition;

    public static int getStartPosition() {
        return startPosition;
    }

    public static void setStartPosition(int startPosition) {
        MenuListener.startPosition = startPosition;
    }
}
