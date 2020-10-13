package org.hzi.sormas.lbds.core.http;

import java.io.Serializable;

public class HttpResult implements Serializable {

    public final int httpCode;
    public final String headers;
    public final String body;

    public HttpResult(int httpCode, String headers, String body) {
        this.httpCode = httpCode;
        this.headers = headers;
        this.body = body;
    }

    public HttpResult(int httpCode, String body) {
        this.httpCode = httpCode;
        this.body = body;
        this.headers = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpResult that = (HttpResult) o;

        if (httpCode != that.httpCode) return false;
        if (!headers.equals(that.headers)) return false;
        return body.equals(that.body);
    }

    @Override
    public int hashCode() {
        int result = httpCode;
        result = 31 * result + headers.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PackedHttpResult{" +
                "httpCode=" + httpCode +
                ", headers='" + headers + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
