package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.AuthorResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public AuthorResponseDTO toDto(Author author) {
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
        authorResponseDTO.setAuthorName(author.getName());
        return authorResponseDTO;
    }
}
