package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;

import java.util.List;

public interface ArticleService {
    Article insert(Article article);

    List<Article> findAll();

    void delete(String id);

    Article findById(String id);

    Article findByUniqUIIDS3(String uniqUIIDS3);

    Article update(Article object);

    List<Article> findByAuthorIdsContaining(String authorId);

    Article findByPdfParamsTitle(String title);

    List<Article> findByPdfParamsTitleContaining(String title);

    List<Article> findByFullTextContaining(String fullText);

    List<Article> findByDepartmentMagazineNameContaining(String departmentMagazineName);
}
