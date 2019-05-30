package md.codefactory.menuservice.mapper;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.mapper.dto.NewFoodDto;
import md.codefactory.menuservice.mapper.dto.UpdateFoodDto;
import md.codefactory.menuservice.mapper.dto.ViewFoodDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    Food newFoodDtoToFood(NewFoodDto newFoodDto);

    NewFoodDto foodToNewFoodDto(Food food);

    Food updateFoodDtoToFood(UpdateFoodDto updateFoodDto);

    UpdateFoodDto foodToUpdateFoodDto(Food food);

    ViewFoodDto foodToViewFoodDto(Food food);
}
