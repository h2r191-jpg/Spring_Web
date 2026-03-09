package org.example.controller;

import org.example.model.Post;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST‑контроллер для работы с сущностью Post (статья/пост).
 * Все эндпоинты доступны по префиксу "/api/post".
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {
    /* Сервер отвечающий за бизнес-логику работы с Post.
    Внедряется через конструктор */
    private final PostService service;

    /* Конструктор контроллера с внедрением зависимости PostServise
     Spring автоматически подставляет экземпляр PostService */
    public PostController(PostService service) {
        this.service = service;
    }

    // Возвращает список всех Post
    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    // Возвращает один Post по id
    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        return service.getById(id);
    }

    // Сохраняет Post
    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    // Удаляет Post
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}