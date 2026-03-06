package org.example.initializer;

import jakarta.servlet.ServletContext;
import org.example.config.JavaConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Класс‑инициализатор Spring‑приложения для веб‑контейнера.
 * Создаёт контекст Spring и регистрирует DispatcherServlet.
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // Создаём контекст Spring на основе Java‑конфигурации
        final var context = new AnnotationConfigWebApplicationContext();
        // Сканируем пакет org.example на предмет @Component и других Spring‑аннотаций
        context.register(JavaConfig.class);
        // Инициализация контекста
        context.refresh();

        // Создаём DispatcherServlet, который будет обрабатывать запросы
        final var dispatcher = new DispatcherServlet(context);
        // Регистрируем сервлет в контейнере под именем "app"
        final var registration = servletContext.addServlet("dispatcher", dispatcher);

        // Говорим контейнеру загружать сервлет при старте приложения
        registration.setLoadOnStartup(1);
        // Привязываем сервлет к корневому пути "/"
        registration.addMapping("/");
    }
}
