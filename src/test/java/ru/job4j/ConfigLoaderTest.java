package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigLoaderTest {
    private String path = "./src/test/resources/connection_config.properties";
    private ConfigLoader config;

    @Before
    public void setUp() {
        config = new ConfigLoader(path);
    }

    @Test
    public void testConfigLoading() {
        assertEquals("psql", config.value("username"));
    }

    @Test
    public void testConfigToString() {
        var result = config.toString();
        var expected = "Config: {password=default, url=test.url, username=psql}";
        assertEquals(expected, result);
    }
}