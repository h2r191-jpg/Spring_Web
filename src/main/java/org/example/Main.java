package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, LifecycleException {
        // Создаём экземпляр встроенного сервера Tomcat.
        final var tomcat = new Tomcat();
        // Создаем временную директорию
        final var daseDir = Files.createTempDirectory("tomsat");

        // Удаляем временной директории после завершения программы
        daseDir.toFile().deleteOnExit();
        // Устанавливаем базовую директорию Tomcat
        tomcat.setBaseDir(daseDir.toAbsolutePath().toString());

        // Создаем новый HTTP- коннектор
        final var connector = new Connector();
        // Настраиваем на порт 8081
        connector.setPort(8081);
        // Устанавливаем как основной для Tomcat
        tomcat.setConnector(connector);

        // Получаем localhost
        tomcat.getHost().setAppBase(".");
        /* Добавляем веб‑приложение к корневому контексту "/".
        Второй аргумент "." это путь к веб‑приложению */
        tomcat.addWebapp("", ".");

        // Запускаем Tomcat
        tomcat.start();
        // Блокируем текущий поток, пока сервер не будет остановлен.
        tomcat.getServer().await();
    }
}
