package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.AuthorResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper.AuthorMapper;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDataPreparer {

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorDataPreparer(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public List<AuthorResponseDTO> authorsToDTO(List<Author> authors) {
        List<AuthorResponseDTO> authorResponseDTOS = new ArrayList<>();
        for (Author author : authors) {
            authorResponseDTOS.add(authorMapper.toDto(author));
        }
        return authorResponseDTOS;
    }
}
