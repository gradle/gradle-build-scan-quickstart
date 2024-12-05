package org.gradle.test.performance.largejavamultiproject.project0.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Sleeper {

    private final static Duration DEFAULT_SLEEP_DURATION = Duration.ofMillis(50);

    public static void sleep() {
        sleep(DEFAULT_SLEEP_DURATION);
    }

    public static void sleep(Duration sleepDuration) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepDuration.toMillis());
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

}
