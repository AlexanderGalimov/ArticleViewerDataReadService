package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDTO {
    private String title;

    private List<String> authorsNames;

    private String pdfLink;
}
