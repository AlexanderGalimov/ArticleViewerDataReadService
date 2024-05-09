package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;


import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorService {
    Author insert(Author author);

    List<Author> findAll();

    void delete(String id);

    Author findById(String id);

    Author update(Author author);

    Author findByName(String name);

    List<Author> findByNameContains(String name);
}
