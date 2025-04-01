package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.AuthorResponseDTO;

import java.util.List;

public interface AuthorSearchService {
    List<AuthorResponseDTO> findAllAuthors();
}
