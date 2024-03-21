package com.example.test2.controller;

import com.example.test2.dto.Response;
import com.example.test2.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    private final RouteService routeService;

    public MainController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/direct")
    public ResponseEntity<Response> hasRoute(Integer from, Integer to) {

        boolean hasDirectRoute = routeService.hasRoute(from, to);

        return ResponseEntity.ok(new Response(from, to, hasDirectRoute));
    }
}
