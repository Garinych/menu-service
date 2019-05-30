package md.codefactory.menuservice.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateFoodDto {

    @NotBlank
    private String name;

    @NotBlank(message = "Only double format!")
    private String price;
}
