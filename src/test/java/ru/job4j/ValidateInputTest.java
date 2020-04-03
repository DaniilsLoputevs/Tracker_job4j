package ru.job4j;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ValidateInputTest {
    private ByteArrayOutputStream newOutput;
    private PrintStream defaultOutput;

    @Before
    public void changeOutput() {
        newOutput = new ByteArrayOutputStream();
        defaultOutput = System.out;
        System.setOut(new PrintStream(newOutput));
    }

    @After
    public void returnOutput() {
        System.setOut(defaultOutput);
    }

    @Test // Please select key from menu.
    public void wrongSelectKeyFromMenu() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"9", "1", "2"})
        );

        input.askInt("take first answer from arr", 6);

        assertEquals(
                String.format("Please select key from menu.%n"),
                newOutput.toString()
        );
    }

    @Test // Please enter data again.
    public void wrongEnterDate() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "1", "2"})
        );

        input.askInt("take first answer from arr", 4);

        assertEquals(
                String.format("Please enter validate data again.%n"),
                newOutput.toString()
        );
    }

}