package vn.elca.training.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author gtn
 *
 */
@Builder
@Data
public class ProjectDto {
    @NotNull(message = "ID must not be null")
    private Long id;

    private Integer projectNumber;

    @NotBlank(message = "Name must not be blank")
    private String projectName;

    @NotBlank(message = "Customer must not be blank")
    private String customer;

    private String group;

    private List<String> member;

    private String status;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    private  Long version;


}
