package md.codefactory.menuservice.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ViewFoodDto {

    private Long id;

    private String name;

    private String price;
}
