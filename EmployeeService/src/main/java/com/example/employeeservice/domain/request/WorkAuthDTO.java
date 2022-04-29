package com.example.employeeservice.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkAuthDTO {
    //“Are you a citizen or permanent resident of the U.S?”
    private String isCitizenAnswer; // yes or No

    private String workAuthType; // if have this field if no, work type
//    @JsonFormat(pattern="yyyy-MM-dd")
    private Date authStartDate;

    private Date  authEndDate;
    //Choose other:
    private String otherWorkType;
}
