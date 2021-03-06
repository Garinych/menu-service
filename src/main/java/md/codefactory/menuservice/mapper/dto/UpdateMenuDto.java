package md.codefactory.menuservice.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UpdateMenuDto {

    @NotBlank
    private String name;

    @NotNull(message = "Must contain true or false !")
    private Boolean isArchived;

    @NotNull
    private Set<ViewFoodDto> food;
}
