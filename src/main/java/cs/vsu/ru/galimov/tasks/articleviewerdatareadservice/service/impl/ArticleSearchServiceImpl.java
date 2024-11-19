package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component.ArticleDataPreparer;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.ArticleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {

    private final ArticleDataPreparer preparer;

    private final ArticleServiceImpl articleService;

    private final AuthorServiceImpl authorService;

    @Autowired
    public ArticleSearchServiceImpl(ArticleDataPreparer preparer, ArticleServiceImpl articleService, AuthorServiceImpl authorService) {
        this.preparer = preparer;
        this.articleService = articleService;
        this.authorService = authorService;
    }

    @Override
    public List<ArticleResponseDTO> findArticlesByTitle(String title) {
        List<Article> articles = articleService.findByPdfParamsTitleContaining(title);
        if (articles.isEmpty()) {
            throw new NotFoundException("Articles with given title not found");
        }
        return preparer.articlesToDTO(articles);
    }

    @Override
    public List<ArticleResponseDTO> findByAuthorName(String authorName) {
        List<Author> authors = authorService.findByNameContains(authorName);
        if (authors.isEmpty()) {
            throw new NotFoundException("Articles with given author name not found");
        }
        List<Article> articles = new ArrayList<>();
        for (Author author : authors) {
            List<Article> currentAuthorArticles = articleService.findByAuthorIdsContaining(author.getId());
            articles.addAll(currentAuthorArticles);
        }
        return preparer.articlesToDTO(articles);
    }

    @Override
    public List<ArticleResponseDTO> findByCoincidenceInFullText(String text) {
        List<Article> articles = articleService.findByFullTextContaining(text);
        if (articles.isEmpty()) {
            throw new NotFoundException("Articles with given text not found");
        }
        return preparer.articlesToDTO(articles);
    }

    @Override
    public List<ArticleResponseDTO> findArticlesWithDepartmentMagazine(String departmentMagazineName) {
        List<Article> articles = articleService.findByDepartmentMagazineNameContaining(departmentMagazineName);
        if (articles.isEmpty()) {
            throw new NotFoundException("Articles with given department magazine not found");
        }
        return preparer.articlesToDTO(articles);
    }
}
