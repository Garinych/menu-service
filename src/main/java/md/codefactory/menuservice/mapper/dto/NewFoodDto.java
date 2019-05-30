package md.codefactory.menuservice.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import md.codefactory.menuservice.domain.Food;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class NewFoodDto {

    @NotBlank
    private String name;

    @NotBlank(message = "Only double format!")
    private String price;
}
