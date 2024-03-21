package com.example.test2.service;

public interface RouteService {

    /**
     * Checks if there is a route between stops
     * @param from from
     * @param to to
     * @return true if there is a direct route and false otherwise
     */
    boolean hasRoute(Integer from, Integer to);
}
