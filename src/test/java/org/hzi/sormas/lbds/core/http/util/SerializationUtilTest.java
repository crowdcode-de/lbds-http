package org.hzi.sormas.lbds.core.http.util;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;
import org.hzi.sormas.lbds.core.http.HttpResult;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SerializationUtilTest {

    static final KeyPairGenerator rda;
    static final KeyPair keyPair;

    static {
        try {
            rda = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPair = rda.generateKeyPair();
    }

    @Test
    void serializePackedHttpContainer() {
        HttpMethod t1 = new HttpMethod(HttpMethod.MethodType.GET, "https://www.google.de", "Accept: application/json");
        HttpResult t2 = new HttpResult(200, "{ result =  \"OK\";}");
        HttpContainer container = new HttpContainer(UUID.randomUUID(), t1, t2);
        assertNotNull(SerializationUtil.serializePackedHttpContainer(container));

    }

    @Test
    void deserializePackedHttpContainer() {
        HttpMethod t1 = new HttpMethod(HttpMethod.MethodType.GET, "https://www.google.de", "Accept: application/json");
        HttpResult t2 = new HttpResult(200, "{ result =  \"OK\";}");
        HttpContainer container = new HttpContainer(UUID.randomUUID(), t1, t2);
        String json = SerializationUtil.serializePackedHttpContainer(container);
        final HttpContainer container1 = SerializationUtil.deserializePackedHttpContainer(json);
        assertEquals(container, container1);
    }

    @Test
    void serializePublicKey() {
        String json = SerializationUtil.serializePublicKey(keyPair.getPublic());
        assertNotNull(json);
    }

    @Test
    void deserializePublicKey() {
        String json = SerializationUtil.serializePublicKey(keyPair.getPublic());
        assertNotNull(json);
        PublicKey pk = SerializationUtil.deserializePublicKey(json);
        assertEquals(keyPair.getPublic(), pk);
    }
}