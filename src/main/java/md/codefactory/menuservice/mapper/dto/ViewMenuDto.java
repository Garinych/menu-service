package md.codefactory.menuservice.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ViewMenuDto {

    private String name;

    private Boolean isArchived;

    private Set<ViewFoodDto> food;
}
