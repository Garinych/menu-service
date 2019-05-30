package md.codefactory.menuservice.mapper.dto;

import lombok.Getter;
import lombok.Setter;
import md.codefactory.menuservice.domain.Food;

import java.util.Set;

@Getter
@Setter
public class ViewMenuDto {

    private String name;

    private Boolean isArchived;

    private Set<Food> food;
 }
