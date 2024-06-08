package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.controller;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.component.DataPreparer;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Author;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.model.Subject;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.ArticleServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.AuthorServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.SubjectServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/article")
@Validated
public class ArticleController {

    private final SubjectServiceImpl subjectService;

    private final ArticleServiceImpl articleService;

    private final AuthorServiceImpl authorService;

    private final DataPreparer dataPreparer;

    @Autowired
    public ArticleController(SubjectServiceImpl subjectService, ArticleServiceImpl articleService, AuthorServiceImpl authorService, DataPreparer dataPreparer) {
        this.subjectService = subjectService;
        this.articleService = articleService;
        this.authorService = authorService;
        this.dataPreparer = dataPreparer;
    }

    @GetMapping("/findByTitleText")
    @Operation(summary = "find Articles by title", description = "Find Article by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> getArticlesByTitle(@RequestParam @Size(min = 3, max = 300) String title) {
        List<Article> articles = articleService.findByPdfParamsTitleContaining(title);
        if (articles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ArticleResponseDTO> articleResponseDTOS = dataPreparer.articlesToDTO(articles);

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findByAuthorName")
    @Operation(summary = "find Articles by author", description = "Find Article by author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findByAuthorName(@RequestBody @Size(min = 3, max = 300) String authorName) {
        List<Author> authors = authorService.findByNameContains(authorName);
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Article> articles = new ArrayList<>();
        for (Author author : authors) {
            List<Article> currentAuthorArticles = articleService.findByAuthorIdsContaining(author.getId());
            articles.addAll(currentAuthorArticles);
        }
        List<ArticleResponseDTO> articleResponseDTOS = dataPreparer.articlesToDTO(articles);

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findByText")
    @Operation(summary = "find Articles by text", description = "Find Article by text")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findByText(@RequestBody @Size(min = 3, max = 300) String text) {
        List<Article> articles = articleService.findByFullTextContaining(text);
        if (articles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ArticleResponseDTO> articleResponseDTOS = dataPreparer.articlesToDTO(articles);

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findRelatedArticles")
    @Operation(summary = "find related Articles by title", description = "Find related Articles by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findRelatedArticles(@RequestParam @Size(min = 3, max = 300) String title) {
        Subject subject = subjectService.findByTitle(title);
        if (subject == null) {
            throw new NotFoundException("Article not found");
        }
        Set<Subject> subjects;
        subjects = subject.getRelatedSubjects();

        List<Article> articles = new ArrayList<>();
        for (Subject currentSubject : subjects) {
            Article article = articleService.findByPdfParamsTitle(currentSubject.getTitle());
            articles.add(article);
        }
        List<ArticleResponseDTO> articleResponseDTOS = dataPreparer.articlesToDTO(articles);

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findArticlesByDepartmentMagazine")
    @Operation(summary = "find Articles by department magazine name", description = "Find related Articles by department magazine name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findArticlesByDepartmentMagazine(@RequestParam @Size(min = 3, max = 15) String departmentMagazineName) {
        List<Article> articles = articleService.findByDepartmentMagazineNameContaining(departmentMagazineName);
        if (articles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ArticleResponseDTO> articleResponseDTOS = dataPreparer.articlesToDTO(articles);

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }
}

