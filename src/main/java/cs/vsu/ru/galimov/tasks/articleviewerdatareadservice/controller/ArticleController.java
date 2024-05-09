package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.controller;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.request.FilterRequestDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.mapper.ArticleMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final SubjectServiceImpl subjectService;

    private final ArticleServiceImpl articleService;

    private final AuthorServiceImpl authorService;

    private final ArticleMapper mapper;

    @Autowired
    public ArticleController(SubjectServiceImpl subjectService, ArticleServiceImpl articleService, AuthorServiceImpl authorService, ArticleMapper mapper) {
        this.subjectService = subjectService;
        this.articleService = articleService;
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping("/findByTitleText")
    @Operation(summary = "find Articles by title", description = "Find Article by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> getArticlesByTitle(@RequestBody FilterRequestDTO filterRequestDTO) {
        List<Subject> subjects = subjectService.findByTitleContaining(filterRequestDTO.getTitle());
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for (Subject subject : subjects) {
            Article article = articleService.findByPdfParamsTitle(subject.getTitle());
            articleResponseDTOS.add(mapper.toDto(article, subject.getAuthorsNames()));
        }

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findByAuthorName")
    @Operation(summary = "find Articles by title", description = "Find Article by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findByAuthorName(@RequestBody String authorName) {
        List<Subject> subjects = subjectService.findByAuthorsNamesContaining(authorName);
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for (Subject subject : subjects) {
            Article article = articleService.findByPdfParamsTitle(subject.getTitle());
            articleResponseDTOS.add(mapper.toDto(article, subject.getAuthorsNames()));
        }

        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

}

