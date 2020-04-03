package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigLoaderTest {

    @Test
    public void testConfigLoading() {
        String path = "./src/main/resources/connection_config.properties";
        var config = new ConfigLoader(path);
        assertEquals("postgres", config.value("username"));
    }
}