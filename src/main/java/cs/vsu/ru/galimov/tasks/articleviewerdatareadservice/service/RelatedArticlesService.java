package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RelatedArticlesService {
    List<ArticleResponseDTO> findRelatedArticles(String title);
}
