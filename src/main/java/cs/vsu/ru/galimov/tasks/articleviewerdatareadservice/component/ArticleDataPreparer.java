package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper.ArticleMapper;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.ArticleServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ArticleDataPreparer {

    private final ArticleMapper articleMapper;

    private final ArticleServiceImpl articleService;

    private final AuthorServiceImpl authorService;

    public List<ArticleResponseDTO> articlesToDTO(List<Article> articles) {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for (Article article : articles) {
            articleResponseDTOS.add(articleMapper.toDto(article, findAuthorsNames(article.getPdfParams().getTitle())));
        }
        return articleResponseDTOS;
    }

    private List<String> findAuthorsNames(String title){
        Article article = articleService.findByPdfParamsTitle(title);
        List<String> authorsNames = new ArrayList<>();
        List<String> ids = article.getAuthorIds();
        for (String id: ids) {
            Author author = authorService.findById(id);
            authorsNames.add(author.getName());
        }
        return authorsNames;
    }
}
