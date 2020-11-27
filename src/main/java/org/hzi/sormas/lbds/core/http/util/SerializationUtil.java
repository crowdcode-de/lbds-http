package org.hzi.sormas.lbds.core.http.util;

import com.google.gson.Gson;
import org.hzi.sormas.lbds.core.http.HttpContainer;
import sun.security.rsa.RSAPublicKeyImpl;

import java.security.PublicKey;

public class SerializationUtil {

    public static String serializePackedHttpContainer(HttpContainer httpContainer) {
        return new Gson().toJson(httpContainer);
    }

    public static HttpContainer deserializePackedHttpContainer(String json) {
        return new Gson().fromJson(json, HttpContainer.class);
    }

    public static String serializePublicKey(PublicKey key) {
        return new Gson().toJson(key);
    }

    public static PublicKey deserializePublicKey(String json) {
        return new Gson().fromJson(json, RSAPublicKeyImpl.class);
    }
}
