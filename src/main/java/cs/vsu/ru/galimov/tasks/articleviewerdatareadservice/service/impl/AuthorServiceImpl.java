package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.repository.AuthorRepository;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Author insert(Author author) {
        return repository.insert(author);
    }

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        Author author = findById(id);
        if (author != null) {
            repository.delete(author);
        }
    }

    @Override
    public Author findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Author not found with id: " + id));
    }

    @Override
    public Author update(Author author) {
        return repository.save(author);
    }

    @Override
    public Author findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Author> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }
}
