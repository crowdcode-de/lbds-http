package org.hzi.sormas.lbds.core.http;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.UUID;

public class HttpContainer implements Serializable {
    final UUID id;
    final HttpMethod httpMethod;
    final HttpResult httpResult;

    public HttpContainer(UUID id, HttpMethod httpMethod, HttpResult httpResult) {
        this.id = id;
        this.httpMethod = httpMethod;
        this.httpResult = httpResult;
    }

    public HttpContainer(HttpMethod httpMethod) {
        this.id = UUID.randomUUID();
        this.httpMethod = httpMethod;
        this.httpResult = null;
    }

    public HttpContainer(UUID id, HttpResult httpResult) {
        this.id = id;
        this.httpMethod = null;
        this.httpResult = httpResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpContainer httpContainer = (HttpContainer) o;

        if (!id.equals(httpContainer.id)) return false;
        if (httpMethod != null ? !httpMethod.equals(httpContainer.httpMethod) : httpContainer.httpMethod != null)
            return false;
        return httpResult != null ? httpResult.equals(httpContainer.httpResult) : httpContainer.httpResult == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (httpMethod != null ? httpMethod.hashCode() : 0);
        result = 31 * result + (this.httpResult != null ? this.httpResult.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PackedHttpContainer{" +
                "id=" + id +
                ", httpMethod=" + httpMethod +
                ", httpResult=" + httpResult +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public HttpMethod getMethod() {
        return httpMethod;
    }

    public HttpResult getResult() {
        return httpResult;
    }

    public static String serializePackedHttpContainer(HttpContainer httpContainer) {
        return new Gson().toJson(httpContainer);
    }

    public static HttpContainer deserializePackedHttpContainer(String json) {
        return new Gson().fromJson(json, HttpContainer.class);
    }


}
