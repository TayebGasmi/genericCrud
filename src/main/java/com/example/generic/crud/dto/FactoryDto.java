package com.example.generic.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(name = "Factory", description = "Factory DTO")
public class FactoryDto extends BaseDto {
    @Schema(example = "1", required = true)
    private Long id;
    @Schema(example = "Factory 1", required = true)
    private String name;
}
