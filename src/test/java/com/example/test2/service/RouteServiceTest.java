package com.example.test2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Test
    void routesTest() {
        assertTrue(routeService.hasRoute(0, 3));
        assertTrue(routeService.hasRoute(1, 6));
        assertTrue(routeService.hasRoute(2, 4));
        assertFalse(routeService.hasRoute(0, 100));
        assertFalse(routeService.hasRoute(4, 2));
    }
}