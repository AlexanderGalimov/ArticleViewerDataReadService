package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.controller;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.ArticleResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.exception.NotFoundException;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.minio.MinioTemplate;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.ArticleSearchServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.RelatedArticlesServiceImpl;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article")
@Validated
public class ArticleController {

    private final RelatedArticlesServiceImpl relatedArticleService;

    private final ArticleSearchServiceImpl articleSearchService;

    private final MinioTemplate template;

    @GetMapping("/findByTitleText")
    @Operation(summary = "find Articles by title", description = "Find Article by title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles with given title not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> getArticlesByTitle(@RequestParam @Size(min = 3, max = 300) String title) {
        List<ArticleResponseDTO> articleResponseDTOS = articleSearchService.findArticlesByTitle(title);
        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findByAuthorName")
    @Operation(summary = "find Articles by author", description = "Find Article by author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles with given author name not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findByAuthorName(@RequestBody @Size(min = 3, max = 300) String authorName) {
        List<ArticleResponseDTO> articleResponseDTOS = articleSearchService.findByAuthorName(authorName);
        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findByCoincidenceInFullText")
    @Operation(summary = "find Articles by text", description = "Find Article by coincidence in full text")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles with given text not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findByText(@RequestBody @Size(min = 3, max = 300) String text) {
        List<ArticleResponseDTO> articleResponseDTOS = articleSearchService.findByCoincidenceInFullText(text);
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
        List<ArticleResponseDTO> articleResponseDTOS = relatedArticleService.findRelatedArticles(title);
        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/findArticlesByDepartmentMagazine")
    @Operation(summary = "find Articles by department magazine name", description = "Find related Articles by department magazine name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of articles retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Articles with given department magazine not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Articles not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<ArticleResponseDTO>> findArticlesByDepartmentMagazine(@RequestParam @Size(min = 3, max = 15) String departmentMagazineName) {
        List<ArticleResponseDTO> articleResponseDTOS = articleSearchService.findArticlesWithDepartmentMagazine(departmentMagazineName);
        return new ResponseEntity<>(articleResponseDTOS, HttpStatus.OK);
    }


    @GetMapping(value = "/getPDFDownloadLink/{uuid}")
    @Operation(summary = "Get Pdf link", description = "Get pdf link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "PDF not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "PDF not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<String> getPictures(@PathVariable String uuid) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String link = template.getPresignedUrl(uuid);

        if (link == null || link.isEmpty()) {
            throw new NotFoundException("PDF with this unique name not found");
        }

        return new ResponseEntity<>(link, HttpStatus.OK);
    }
}

