package com.udemyCourse.course.controllers.docs;

import com.udemyCourse.course.dataDTO.v1.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface BookControllerDocs {
    @Operation(summary = "Finds a Book", description = "Findby a specific id", tags = {"Books"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    BookDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "finadAllBook", tags = "{Books}", responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                    )
            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    List<BookDTO> findAll();


    @Operation(summary = "Create a Book", description = "Create a Book", tags = {"Books"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    BookDTO create(@RequestBody BookDTO BookDTO);

    @Operation(summary = "Update a Book", description = "Update a Book in specific id", tags = {"Books"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    BookDTO update(@RequestBody BookDTO BookDTO);

    @Operation(summary = "Delete a Book", description = "Delete a Book in specific id", tags = {"Books"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<?> delete(@PathVariable("id") Long id);

    @Operation(summary = "Api v2 a Book", description = "Api v2 a Book i", tags = {"Books"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = BookDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    BookDTO createV2(@RequestBody BookDTO BookDTOv2);
}
