package com.example.test2.dto;

public class Response {

    private final Integer from;

    private final Integer to;

    private final boolean direct;

    public Response(Integer from, Integer to, boolean direct) {
        this.from = from;
        this.to = to;
        this.direct = direct;
    }

    public Integer getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public boolean isDirect() {
        return direct;
    }
}
