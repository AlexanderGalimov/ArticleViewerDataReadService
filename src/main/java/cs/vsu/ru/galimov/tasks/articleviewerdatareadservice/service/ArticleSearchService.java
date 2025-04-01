package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;

import java.util.List;

public interface ArticleSearchService {
    List<ArticleResponseDTO> findArticlesByTitle(String title);

    List<ArticleResponseDTO> findByAuthorName(String authorName);

    List<ArticleResponseDTO> findByCoincidenceInFullText(String text);

    List<ArticleResponseDTO> findArticlesWithDepartmentMagazine(String departmentMagazineName);
}
