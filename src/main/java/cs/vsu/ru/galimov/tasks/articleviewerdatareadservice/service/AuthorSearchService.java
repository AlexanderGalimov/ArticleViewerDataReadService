package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.AuthorResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorSearchService {
    List<AuthorResponseDTO> findAllAuthors();
}
