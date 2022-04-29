package com.GroupProject.applicationservice.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DigitalDocRequest {
    @JsonProperty("type")
    private String type;

    @JsonProperty("isRequired")
    private Boolean isRequired;

    @JsonProperty("description")
    private String description;
}
