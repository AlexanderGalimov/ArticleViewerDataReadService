package cs.vsu.ru.galimov.tasks.articleviewerdatareadservice.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonSerialize
@JsonDeserialize
public class FilterRequestDTO {
    @Schema(description = "Title", example = "title")
    @NotBlank(message = "Title of article cannot be blank")
    @Size(min = 3, max = 300, message = "Title must be from 3 to 300")
    private String title;

    @Schema(description = "Department magazine name", example = "geograph")
    @NotBlank(message = "Department magazine name cannot be blank")
    @Size(min = 3, max = 15, message = "Department magazine name must be from 3 to 50")
    private String departmentMagazineName;
}
