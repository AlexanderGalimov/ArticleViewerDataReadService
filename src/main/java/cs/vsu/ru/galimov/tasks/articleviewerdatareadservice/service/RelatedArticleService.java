package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;

import java.util.List;

public interface RelatedArticleService {
    List<ArticleResponseDTO> findRelatedArticles(String title);
}