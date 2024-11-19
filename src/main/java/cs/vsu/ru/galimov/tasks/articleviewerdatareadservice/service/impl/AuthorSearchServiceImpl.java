package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component.AuthorDataPreparer;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.AuthorResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.AuthorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorSearchServiceImpl implements AuthorSearchService {

    private final AuthorServiceImpl authorService;


    private final AuthorDataPreparer preparer;

    @Autowired
    public AuthorSearchServiceImpl(AuthorServiceImpl authorService, AuthorDataPreparer preparer) {
        this.authorService = authorService;
        this.preparer = preparer;
    }

    @Override
    public List<AuthorResponseDTO> findAllAuthors() {
        List<Author> authors = authorService.findAll();
        if (authors.isEmpty()) {
            throw new NotFoundException("Authors not found");
        }
        return preparer.authorsToDTO(authors);
    }
}
