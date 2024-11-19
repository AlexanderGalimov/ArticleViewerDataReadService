package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component.ArticleDataPreparer;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.RelatedArticlesService;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RelatedArticlesServiceImpl implements RelatedArticlesService {

    private final SubjectService subjectService;
    private final ArticleServiceImpl articleService;
    private final ArticleDataPreparer articleDataPreparer;

    @Autowired
    public RelatedArticlesServiceImpl(SubjectService subjectService, ArticleServiceImpl articleService, ArticleDataPreparer articleDataPreparer) {
        this.subjectService = subjectService;
        this.articleService = articleService;
        this.articleDataPreparer = articleDataPreparer;
    }

    @Override
    public List<ArticleResponseDTO> findRelatedArticles(String title) {
        Subject subject = subjectService.findByTitle(title);
        if (subject == null) {
            throw new NotFoundException("Article with given title not found");
        }
        Set<Subject> subjects = subject.getRelatedSubjects();

        List<Article> articles = new ArrayList<>();
        for (Subject currentSubject : subjects) {
            Article article = articleService.findByPdfParamsTitle(currentSubject.getTitle());
            articles.add(article);
        }
        return articleDataPreparer.articlesToDTO(articles);
    }
}

