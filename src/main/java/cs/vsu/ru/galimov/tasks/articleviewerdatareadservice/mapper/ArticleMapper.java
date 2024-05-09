package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleMapper {
    public ArticleResponseDTO toDto(Article article, List<String> authorsNames){
        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO();
        articleResponseDTO.setTitle(article.getPdfParams().getTitle());
        articleResponseDTO.setAuthorsNames(authorsNames);
        articleResponseDTO.setPdfLink(article.getPdfParams().getLink());
        return articleResponseDTO;
    }
}
