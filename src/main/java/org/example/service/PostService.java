package org.example.service;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для бизнес‑логики работы с постами.
 */
@Service
public class PostService {
    private final PostRepository repository;

    //Конструктор с внедрением зависимости PostRepository.
    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    // возвращает список Post
    public List<Post> all() {
        return repository.all();
    }

    // Возвращает один Post по идентификатору.
    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    // Сохраняет Post
    public Post save(Post post) {
        return repository.save(post);
    }

    // Удаляет Post по идентификатору или исключение если не найден
    public void removeById(long id) {
        if (repository.getById(id).isEmpty()) {
            throw new NotFoundException("Пост с id=" + id + " не найден");
        }
        repository.removeById(id);
    }
}
