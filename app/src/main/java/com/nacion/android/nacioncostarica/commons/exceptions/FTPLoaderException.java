package com.nacion.android.nacioncostarica.commons.exceptions;

/**
 * Created by Gustavo Matarrita on 28/08/2014.
 */
public class FTPLoaderException extends Exception {
    public FTPLoaderException(String argMessage){
        super(argMessage);
    }

    public FTPLoaderException(Throwable argThrowable){
        super(argThrowable);
    }
}
