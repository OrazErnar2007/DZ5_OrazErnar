package Prac5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Тестирование паттерна Одиночка (Singleton) на Java ===");

        ConfigurationManager[] instances = new ConfigurationManager[5];
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            Thread thread = new Thread(() -> {
                instances[threadId] = ConfigurationManager.getInstance();

                instances[threadId].setSetting("ThreadKey_" + threadId, "Value_" + threadId);

                System.out.println("Поток " + threadId + " отработал. Хэш-код экземпляра: " + instances[threadId].hashCode());
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        boolean isSameInstance = true;
        for (int i = 1; i < instances.length; i++) {
            if (instances[0] != instances[i]) {
                isSameInstance = false;
                break;
            }
        }

        System.out.println("\nВсе потоки вернули один и тот же экземпляр? -> " + isSameInstance);

        String filePath = "config.txt";
        ConfigurationManager config = ConfigurationManager.getInstance();

        System.out.println("\n--- Тестирование сохранения/загрузки ---");
        config.setSetting("Environment", "Production");
        config.saveToFile(filePath);

        try {
            config.loadFromFile(filePath);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- Тестирование внешних источников и исключений ---");
        config.loadFromDatabase("jdbc:postgresql://localhost:5432/mydb");

        try {
            System.out.println("Чтение настройки 'Environment': " + config.getSetting("Environment"));
            System.out.println("Чтение настройки 'DbTimeout': " + config.getSetting("DbTimeout"));

            System.out.println(config.getSetting("NonExistentKey"));
        } catch (IllegalArgumentException e) {
            System.out.println("\nПерехвачено ожидаемое исключение: " + e.getMessage());
        }
    }
}

