package org.example.repository;

import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
// Stub
public class PostRepository {
    // Возвращает список всех Post + с помеченные на удаление
    private final List<Post> posts = new ArrayList<>();

    // Возвращает список только не удаление
    public List<Post> all() {
        return posts.stream()
                .filter(post -> !post.isRemoved())
                .toList();
    }

    // Возвращает Post по идентификатору, если он не удален
    public Optional<Post> getById(long id) {

        return posts.stream()
                .filter(post -> !post.isRemoved())
                .filter(post -> post.getId() == id)
                .findFirst();
    }

    // Сохраняет новый Post или обновляет старый
    public Post save(Post post) {
        // Проверяем наличие
        final var optional = posts.stream()
                .filter(p -> p.getId() == post.getId())
                .findFirst();
        if (optional.isPresent()) {
            var saved = optional.get();
            // если помечен на удаление, то выбрасывает исключение
            if (saved.isRemoved()) {
                throw new NotFoundException("Пост удалено!");
            }
            // в противном случае обновляем
            saved.setContent(post.getContent());
            return saved;
        } else {
            // Если не найден создается новый
            if (post.getId() == 0) {
                // Генерируем id
                long maxId = posts.isEmpty() ? 0 : posts.stream()
                        .mapToLong(Post::getId)
                        .max()
                        .orElse(0);
                post.setId(maxId + 1);
            }
            posts.add(post);
            return post;
        }
    }

    // Удаляет по Post идентификатору
    public void removeById(long id) {
        posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .ifPresent(post -> post.setRemoved(true));
    }
}

