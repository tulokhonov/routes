package com.example.test2;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestOne {

    Logger log = LoggerFactory.getLogger(Test2ApplicationTests.class);
    private List<Integer> foundRoutes = new ArrayList<>();
    private Map<Integer, LinkedHashSet<Integer>> routes = new HashMap<>();


    @Test
    void name() throws FileNotFoundException {
        int from = 0;
        int to = 1;

        load();

        log.info(routes.toString());

        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).contains(from) && routes.get(i).contains(to))
                foundRoutes.add(i);
        }
        for (Integer r: foundRoutes) {
            if (gotcha(r, from, to))
                log.info(String.valueOf(r));
        }
    }

    private List<Integer> getStops(String routeLine) {
        return Arrays.stream(routeLine.split(" ")).mapToInt(Integer::parseInt).boxed().toList();
    }

    boolean gotcha(Integer r, Integer from, Integer to) {
        boolean c = false;
        for (Integer i : routes.get(r)) {
            if (i.equals(from)) {
                c = true;
                continue;
            }
            if (i.equals(to) && !c) return false;
            if (i.equals(to)) return true;
        }
        return false;
    }

    private void load() {
        try {
            Scanner scanner = new Scanner(new File("src/test/resources/routes.txt"));

            while (scanner.hasNextLine()) {
                List<Integer> s = getStops(scanner.nextLine());
                routes.put(s.get(0), new LinkedHashSet<>(s.subList(1, s.size())));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
