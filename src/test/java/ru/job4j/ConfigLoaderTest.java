package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigLoaderTest {
    private ConfigLoader config;

    @Before
    public void setUp() {
        config = new ConfigLoader(ConfigLoader.getPsqlConfigPath());
    }

    @Test
    public void testConfigLoading() {
        assertEquals("postgres", config.value("username"));
    }

    @Test
    public void testConfigToString() {
        var result = config.toString();
        var expected = "Config: "
                + "{password=password,"
                + " url=jdbc:postgresql://localhost:5432/tracker,"
                + " username=postgres}";
        assertEquals(expected, result);
    }
}