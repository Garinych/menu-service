package md.codefactory.menuservice.mapper;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.mapper.dto.NewFoodDto;
import md.codefactory.menuservice.mapper.dto.UpdateFoodDto;
import md.codefactory.menuservice.mapper.dto.ViewFoodDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class FoodMapperTest {

    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Test
    public void should_map_newFoodDto_to_food() {
        NewFoodDto newFoodDto = new NewFoodDto();
        newFoodDto.setName("Burito");
        newFoodDto.setPrice("50.0");

        Food food = foodMapper.newFoodDtoToFood(newFoodDto);

        assertThat(food).isNotNull();
        assertThat(food.getName()).isEqualTo(newFoodDto.getName());
        assertThat(food.getPrice().toString()).isEqualTo(newFoodDto.getPrice());
    }

    @Test
    public void should_map_food_to_newFoodDto() {
        Food food = new Food();
        food.setName("Burito");
        food.setPrice(50.0);

        NewFoodDto newFoodDto = foodMapper.foodToNewFoodDto(food);

        assertThat(newFoodDto).isNotNull();
        assertThat(newFoodDto.getName()).isEqualTo(food.getName());
        assertThat(newFoodDto.getPrice()).isEqualTo(food.getPrice().toString());
    }

    @Test
    public void should_map_updateFoodDto_to_food() {
        UpdateFoodDto updateFoodDto = new UpdateFoodDto();
        updateFoodDto.setName("Burito");
        updateFoodDto.setPrice("50.0");

        Food food = foodMapper.updateFoodDtoToFood(updateFoodDto);

        assertThat(food).isNotNull();
        assertThat(food.getName()).isEqualTo(updateFoodDto.getName());
        assertThat(food.getPrice().toString()).isEqualTo(updateFoodDto.getPrice());
    }

    @Test
    public void should_map_food_to_updateFoodDto() {
        Food food = new Food();
        food.setName("Burito");
        food.setPrice(50.0);

        UpdateFoodDto updateFoodDto = foodMapper.foodToUpdateFoodDto(food);

        assertThat(updateFoodDto).isNotNull();
        assertThat(updateFoodDto.getName()).isEqualTo(food.getName());
        assertThat(updateFoodDto.getPrice()).isEqualTo(food.getPrice().toString());
    }

    @Test
    public void should_map_viewFoodDto_to_food() {
        Food food = new Food();
        food.setName("Burito");
        food.setPrice(50.0);

        ViewFoodDto  viewFoodDto = foodMapper.foodToViewFoodDto(food);

        assertThat(viewFoodDto).isNotNull();
        assertThat(viewFoodDto.getName()).isEqualTo(food.getName());
        assertThat(viewFoodDto.getPrice()).isEqualTo(food.getPrice().toString());
    }

}