package com.multiclock.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivityTest {

    @Test
    public void testHandleText() {
        String input = "6:30 AM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time = LocalTime.parse(input, formatter);
        LocalTime newTime = time.plusHours(8).plusMinutes(30);
        String expectedOutput = newTime.format(formatter);

        assertEquals("3:00 PM", expectedOutput);
    }
}