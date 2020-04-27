package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.codehelpers.IOHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/** Читаем файл конфигурации.
 * Задача: Считать содержимое файла configuration.properties.
 * и сохранить "Настройки" во внутреннюю map<String, String>.
 *
 * @author Daniils Loputevs
 * @version $Id$
 * @since 03.04.20.
 */
public class ConfigLoader {
    private String path;
    private Map<String, String> values = new HashMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);

    public ConfigLoader(final String path) {
        this.path = path;
        load();
    }

    /** Загружает настройки из файла по пути path(path initialize. через конструктор).
     * Фильтруем через stream от комментариев и пустых строк.
     * Собираем в карту:
     * Тип настроек      - Ключ.
     * Значение настроек - Значение.
     */
    private void load() {
        List<String> fileLines = IOHelper.readFileToList(path, ArrayList::new);
        values.putAll(fileLines.stream()
                .filter(line -> !line.startsWith("//"))
                .filter(line -> !line.startsWith("#"))
                .filter(line -> !line.startsWith(" "))
                .filter(line -> line.contains("="))
                .collect(Collectors.toMap(
                        line -> line.substring(0, line.indexOf("=")),
                        line -> line.substring(line.indexOf("=") + 1))));
    }

    /** Получить значение по ключу.
     * @param key ключ
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

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return out.toString();
    }
}