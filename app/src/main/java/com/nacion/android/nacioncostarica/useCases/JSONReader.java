package com.nacion.android.nacioncostarica.useCases;

import com.nacion.android.nacioncostarica.models.Site;

/**
 * Created by Gustavo Matarrita on 02/10/2014.
 */
public interface JSONReader {
    public Site createObjectsFromJSONString(String argJson);
}
