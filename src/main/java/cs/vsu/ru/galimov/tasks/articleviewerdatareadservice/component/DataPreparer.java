package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper.ArticleMapper;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.ArticleServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataPreparer {

    private final ArticleServiceImpl articleService;

    private final ArticleMapper mapper;

    private final SubjectServiceImpl subjectService;

    @Autowired
    public DataPreparer(ArticleServiceImpl articleService, ArticleMapper mapper, SubjectServiceImpl subjectService) {
        this.articleService = articleService;
        this.mapper = mapper;
        this.subjectService = subjectService;
    }

    public List<ArticleResponseDTO> articlesToDTO(List<Article> articles){
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for (Article article: articles) {
            articleResponseDTOS.add(mapper.toDto(article, getAuthorsNames(article.getPdfParams().getTitle())));
        }
        return articleResponseDTOS;
    }

    public List<String> getAuthorsNames(String articleTitle){
        Subject subject = subjectService.findByTitle(articleTitle);
        return subject.getAuthorsNames();
    }
}
