package com.GroupProject.applicationservice.domain.response;


import com.GroupProject.applicationservice.entity.dto.DigitalDocumentDto;
import lombok.Builder;
import lombok.Data;


import java.util.List;


@Data
@Builder
public class AllDigitalDocResponse {
    private ResponseStatus status;
    private List<DigitalDocumentDto> documentDtoList;

}
