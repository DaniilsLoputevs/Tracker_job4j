package ru.job4j.acodehelp;

import java.util.concurrent.atomic.AtomicReference;

/**
 * manual tool - not for production.
 */
public class ManualExperiment {

    public static void main(String[] args) {
        var arr = new String[] {"one", "two", "three"};

        AtomicReference<String> answer = new AtomicReference<>();

        ArrayHelp.forEach(arr, elem -> {
            if (elem.equals("two")) {
                answer.set(elem);
            }
        });

        ConslLog.log("answer", answer.get());

    }

}