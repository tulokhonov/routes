package com.example.test2.service;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class RouteServiceImpl implements RouteService {

    Logger log = LoggerFactory.getLogger(RouteServiceImpl.class);

    private final Map<Integer, LinkedHashSet<Integer>> routes = new HashMap<>();

    @Override
    public boolean hasRoute(Integer from, Integer to) {

        final List<Integer> directRouteCandidates = new ArrayList<>();

        for (int i = 0; i < routes.size(); i++) {
            boolean routeHasFromAndTo = routes.get(i).contains(from) && routes.get(i).contains(to);
            if (routeHasFromAndTo)
                directRouteCandidates.add(i);
        }
        for (Integer route: directRouteCandidates) {
            if (routeIsDirect(route, from, to)) return true;
        }

        return false;
    }

    private boolean routeIsDirect(Integer route, Integer from, Integer to) {
        boolean routeCandidate = false;
        for (Integer stop : routes.get(route)) {
            if (stop.equals(from)) {
                routeCandidate = true;
                continue;
            }
            if (stop.equals(to) && !routeCandidate) return false;
            if (stop.equals(to)) return true;
        }
        return false;
    }

    @PostConstruct
    private void init() {
        try {
            Scanner scanner = new Scanner(new File("src/test/resources/routes.txt"));

            while (scanner.hasNextLine()) {
                List<Integer> s = getStops(scanner.nextLine());
                routes.put(s.get(0), new LinkedHashSet<>(s.subList(1, s.size())));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            log.error("Route file not found!");
        }
    }

    private List<Integer> getStops(String routeLine) {
        return Arrays.stream(routeLine.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
}
