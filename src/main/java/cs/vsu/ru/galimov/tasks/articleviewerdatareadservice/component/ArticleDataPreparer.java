package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper.ArticleMapper;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper.AuthorMapper;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDataPreparer {

    private final ArticleMapper articleMapper;

    private final AuthorMapper authorMapper;

    private final SubjectServiceImpl subjectService;

    @Autowired
    public ArticleDataPreparer(ArticleMapper articleMapper, AuthorMapper authorMapper, SubjectServiceImpl subjectService) {
        this.articleMapper = articleMapper;
        this.authorMapper = authorMapper;
        this.subjectService = subjectService;
    }

    public List<ArticleResponseDTO> articlesToDTO(List<Article> articles) {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for (Article article : articles) {
            articleResponseDTOS.add(articleMapper.toDto(article, getAuthorsNames(article.getPdfParams().getTitle())));
        }
        return articleResponseDTOS;
    }

    public List<String> getAuthorsNames(String articleTitle) {
        Subject subject = subjectService.findByTitle(articleTitle);
        return subject.getAuthorsNames();
    }
}
