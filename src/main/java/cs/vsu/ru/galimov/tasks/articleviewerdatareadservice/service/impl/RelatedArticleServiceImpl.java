package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component.DataPreparer;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.ArticleService;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.RelatedArticleService;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RelatedArticleServiceImpl implements RelatedArticleService {

    private final SubjectService subjectService;
    private final ArticleService articleService;
    private final DataPreparer dataPreparer;

    @Autowired
    public RelatedArticleServiceImpl(SubjectService subjectService, ArticleService articleService, DataPreparer dataPreparer) {
        this.subjectService = subjectService;
        this.articleService = articleService;
        this.dataPreparer = dataPreparer;
    }

    @Override
    public List<ArticleResponseDTO> findRelatedArticles(String title) {
        Subject subject = subjectService.findByTitle(title);
        if (subject == null) {
            throw new NotFoundException("Article not found");
        }
        Set<Subject> subjects = subject.getRelatedSubjects();

        List<Article> articles = new ArrayList<>();
        for (Subject currentSubject : subjects) {
            Article article = articleService.findByPdfParamsTitle(currentSubject.getTitle());
            articles.add(article);
        }
        return dataPreparer.articlesToDTO(articles);
    }
}

