package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByName(String name);

    List<Author> findByNameContains(String name);
}
