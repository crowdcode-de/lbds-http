package org.hzi.sormas.lbds.core.http.util;

import org.hzi.sormas.lbds.core.http.HttpContainer;
import org.hzi.sormas.lbds.core.http.HttpMethod;
import org.hzi.sormas.lbds.core.http.HttpResult;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SerializationUtilTest {

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

}