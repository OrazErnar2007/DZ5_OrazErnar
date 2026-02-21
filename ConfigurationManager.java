package Prac5;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationManager {

    private static volatile ConfigurationManager instance;
    private final Object dictLock = new Object();

    private final Map<String, String> settings;

    private ConfigurationManager() {
        settings = new HashMap<>();
        settings.put("AppVersion", "1.0.0");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public void setSetting(String key, String value) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым.");
        }
        synchronized (dictLock) {
            settings.put(key, value);
        }
    }

    public String getSetting(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым.");
        }
        synchronized (dictLock) {
            if (!settings.containsKey(key)) {
                throw new IllegalArgumentException("Ошибка: Настройка с ключом '" + key + "' не найдена.");
            }
            return settings.get(key);
        }
    }


    public void saveToFile(String filePath) {
        synchronized (dictLock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Map.Entry<String, String> entry : settings.entrySet()) {
                    writer.write(entry.getKey() + "=" + entry.getValue());
                    writer.newLine();
                }
                System.out.println("[Файл] Настройки успешно сохранены в " + filePath);
            } catch (IOException e) {
                System.err.println("Ошибка при сохранении в файл: " + e.getMessage());
            }
        }
    }

    public void loadFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл конфигурации не найден: " + filePath);
        }

        synchronized (dictLock) {
            settings.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty() || !line.contains("=")) continue;

                    String[] parts = line.split("=", 2);
                    settings.put(parts[0].trim(), parts[1].trim());
                }
                System.out.println("[Файл] Настройки успешно загружены из " + filePath);
            } catch (IOException e) {
                System.err.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
    }


    public void loadFromDatabase(String connectionString) {
        System.out.println("[База данных] Подключение к БД по строке: " + connectionString + "...");
        synchronized (dictLock) {
            settings.put("DbTimeout", "30");
            settings.put("MaxConnections", "100");
        }
        System.out.println("[База данных] Настройки успешно загружены из БД.");
    }
}
