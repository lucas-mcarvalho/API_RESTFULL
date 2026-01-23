package com.udemyCourse.course.controllers.docs;

import com.udemyCourse.course.dataDTO.v1.PersonDTO;
import com.udemyCourse.course.dataDTO.v2.PersonDTOv2;
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

public interface PersonControllerDocs {
    @Operation(summary = "Finds a person", description = "Findby a specific id", tags = {"Peoples"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PersonDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "finadAllPeople", tags = "{Peoples}", responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                    )
            }),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    List<PersonDTO> findAll();


    @Operation(summary = "Create a person", description = "Create a person", tags = {"Peoples"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PersonDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO create(@RequestBody PersonDTO PersonDTO);

    @Operation(summary = "Update a person", description = "Update a person in specific id", tags = {"Peoples"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PersonDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTO update(@RequestBody PersonDTO PersonDTO);

    @Operation(summary = "Delete a person", description = "Delete a person in specific id", tags = {"Peoples"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PersonDTO.class))
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

    @Operation(summary = "Api v2 a person", description = "Api v2 a person i", tags = {"Peoples"}, responses =
            {@ApiResponse(description = "Succes", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PersonDTO.class))
            }
            ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
            }
    )
    PersonDTOv2 createV2(@RequestBody PersonDTOv2 PersonDTOv2);
}
