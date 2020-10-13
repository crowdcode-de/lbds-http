package org.hzi.sormas.lbds.core.http;

import java.io.Serializable;

public class HttpMethod implements Serializable {

    public enum MethodType {
        GET,
        POST,
        PUT,
        DELETE
    }

    public final MethodType methodType;
    public final String url;
    public final String headers;
    public final String payload;

    public HttpMethod(MethodType methodType, String url, String headers, String payload) {
        this.methodType = methodType;
        this.url = url;
        this.headers = headers;
        this.payload = payload;
    }

    public HttpMethod(MethodType methodType, String url, String payload) {
        this.methodType = methodType;
        this.url = url;
        this.headers = "";
        this.payload = payload;
    }

    public HttpMethod(MethodType methodType, String url) {
        this.methodType = methodType;
        this.url = url;
        this.headers = "";
        this.payload = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpMethod that = (HttpMethod) o;

        if (methodType != that.methodType) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        return payload != null ? payload.equals(that.payload) : that.payload == null;
    }

    @Override
    public int hashCode() {
        int result = methodType != null ? methodType.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PackedHttpMethod{" +
                "methodType=" + methodType +
                ", url='" + url + '\'' +
                ", headers='" + headers + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
