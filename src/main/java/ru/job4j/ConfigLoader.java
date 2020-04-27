package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Читаем файл конфигурации.
 * Задача: Считать содержимое файла configuration.properties.
 * и сохранить "Настройки" во внутреннюю map<String, String>.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 27.04.20.
 */
public class ConfigLoader {
    private String path;
    private Map<String, String> values = new HashMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);

    public ConfigLoader(final String path) {
        this.path = path;
        load();
    }

    /**
     * Загружает настройки из файла по пути {@code path}.
     * * {@code path} - загружается через конструктор.
     * Фильтруем через stream от комментариев и пустых строк.
     *
     * Собираем в карту:
     * key   - имя настройки.
     * value - значение настройки.
     */
    private void load() {
        List<String> fileLines = this.readFileToList(path);
        values.putAll(fileLines.stream()
                .filter(line -> !line.startsWith("//"))
                .filter(line -> !line.startsWith("#"))
                .filter(line -> !line.startsWith(" "))
                .filter(line -> line.contains("="))
                .collect(Collectors.toMap(
                        line -> line.substring(0, line.indexOf("=")),
                        line -> line.substring(line.indexOf("=") + 1))));
    }

    /**
     * Получить значение по ключу.
     *
     * @param key - ключ
     * @return value/null
     */
    public String value(String key) {
        if (this.values.isEmpty()) {
            throw new UnsupportedOperationException("Config wasn't loaded || it's empty");
        }
        String result = this.values.get(key);
        if (result == null) {
            throw new IllegalArgumentException("Doesn't contains this key || value == null");
        }
        return result;
    }

    /**
     * Преобразовать содержимое файла в {@code List}, далее работать текстом в виде {@code List}.
     *
     * @param path - Путь файла.
     * @return List<String> Все строчки из файла.
     */
    private List<String> readFileToList(String path) {
        List<String> fileLines = new LinkedList<>();
        try (var bufferedReader = new BufferedReader(new FileReader(path))) {
            fileLines = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return fileLines;
    }

    @Override
    public String toString() {
        return "Config: " + values;
    }
}