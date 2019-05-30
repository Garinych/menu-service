package md.codefactory.menuservice.mapper;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.mapper.dto.NewFoodDto;
import md.codefactory.menuservice.mapper.dto.UpdateFoodDto;
import md.codefactory.menuservice.mapper.dto.ViewFoodDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    Food newFoodToFood(NewFoodDto newFoodDto);

    NewFoodDto foodToNewFood(Food food);

    Food updateFoodToFood(UpdateFoodDto updateFoodDto);

    UpdateFoodDto foodToUpdateFood(Food food);

    Food viewFoodToFood(ViewFoodDto viewFoodDto);

    ViewFoodDto foodToViewFood(Food food);
}
