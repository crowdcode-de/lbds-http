package org.hzi.sormas.lbds.core.http.util;

import com.google.gson.Gson;
import org.hzi.sormas.lbds.core.http.HttpContainer;

public class SerializationUtil {

    public static String serializePackedHttpContainer(HttpContainer httpContainer) {
        return new Gson().toJson(httpContainer);
    }

    public static HttpContainer deserializePackedHttpContainer(String json) {
        return new Gson().fromJson(json, HttpContainer.class);
    }

}
