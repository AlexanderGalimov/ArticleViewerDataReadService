package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.controller;

import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.responce.AuthorResponseDTO;
import cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.service.impl.AuthorSearchServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@Validated
public class AuthorController {

    private final AuthorSearchServiceImpl authorSearchService;

    @Autowired
    public AuthorController(AuthorSearchServiceImpl authorSearchService) {
        this.authorSearchService = authorSearchService;
    }

    @GetMapping("/findByDepartmentMagazine")
    @Operation(summary = "Find Authors by department magazine", description = "Find Authors by department magazine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of authors retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "Authors not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Authors not found"))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "Internal server error")))
    })
    public ResponseEntity<List<AuthorResponseDTO>> getAuthorsByDepartmentMagazineName() {
        List<AuthorResponseDTO> authorResponseDTOS = authorSearchService.findAllAuthors();
        return new ResponseEntity<>(authorResponseDTOS, HttpStatus.OK);
    }


}