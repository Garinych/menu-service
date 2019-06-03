package md.codefactory.menuservice.mapper;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.mapper.dto.NewMenuDto;
import md.codefactory.menuservice.mapper.dto.UpdateMenuDto;
import md.codefactory.menuservice.mapper.dto.ViewFoodDto;
import md.codefactory.menuservice.mapper.dto.ViewMenuDto;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuMapperTest {

    private final MenuMapper menuMapper = Mappers.getMapper(MenuMapper.class);
    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Test
    public void should_map_newMenuDto_to_food() {

        ViewFoodDto food = new ViewFoodDto();
        food.setName("Burito");
        food.setPrice("50.0");

        NewMenuDto newMenuDto = new NewMenuDto();
        newMenuDto.setName("Mexican Menu");
        newMenuDto.setIsArchived(true);
        newMenuDto.setFood(Collections.singleton(food));

        Menu menu = menuMapper.newMenuToMenu(newMenuDto);

        assertThat(menu).isNotNull();
        assertThat(menu.getName()).isEqualTo(newMenuDto.getName());
        assertThat(menu.getIsArchived()).isEqualTo(newMenuDto.getIsArchived());
        assertThat(menu.getFood().contains("Burito")).isEqualTo(newMenuDto.getFood().contains("Burito"));
    }

    @Test
    public void should_map_food_to_newMenuDto() {
        Food food = new Food();
        food.setName("Burito");
        food.setPrice(50.0);

        Menu menu = new Menu();
        menu.setName("Mexican Menu");
        menu.setIsArchived(true);
        menu.setFood(Collections.singleton(food));

        NewMenuDto newMenuDto = menuMapper.menuToNewMenu(menu);

        assertThat(newMenuDto).isNotNull();
        assertThat(newMenuDto.getName()).isEqualTo(menu.getName());
        assertThat(newMenuDto.getIsArchived()).isEqualTo(menu.getIsArchived());
        assertThat(newMenuDto.getFood().contains("Burito")).isEqualTo(menu.getFood().contains("Burito"));
    }

    @Test
    public void should_map_updateMenuDto_to_food() {
        ViewFoodDto food = new ViewFoodDto();
        food.setName("Burito");
        food.setPrice("50.0");

        UpdateMenuDto updateMenuDto = new UpdateMenuDto();
        updateMenuDto.setName("Mexican Menu");
        updateMenuDto.setIsArchived(true);
        updateMenuDto.setFood(Collections.singleton(food));

        Menu menu = menuMapper.updateMenuToMenu(updateMenuDto);

        assertThat(menu).isNotNull();
        assertThat(menu.getName()).isEqualTo(updateMenuDto.getName());
        assertThat(menu.getIsArchived()).isEqualTo(updateMenuDto.getIsArchived());
        assertThat(menu.getFood().contains("Burito")).isEqualTo(updateMenuDto.getFood().contains("Burito"));
    }

    @Test
    public void should_map_food_to_updateMenuDto() {
        Food food = new Food();
        food.setName("Burito");
        food.setPrice(50.0);

        Menu menu = new Menu();
        menu.setName("Mexican Menu");
        menu.setIsArchived(true);
        menu.setFood(Collections.singleton(food));

        UpdateMenuDto updateMenuDto = menuMapper.menuToUpdateMenu(menu);

        assertThat(updateMenuDto).isNotNull();
        assertThat(updateMenuDto.getName()).isEqualTo(menu.getName());
        assertThat(updateMenuDto.getIsArchived()).isEqualTo(menu.getIsArchived());
        assertThat(updateMenuDto.getFood().contains("Burito")).isEqualTo(menu.getFood().contains("Burito"));
    }

    @Test
    public void should_map_viewMenuDto_to_food() {
        Food food = new Food();
        food.setName("Burito");
        food.setPrice(50.0);

        Menu menu = new Menu();
        menu.setName("Mexican Menu");
        menu.setIsArchived(true);
        menu.setFood(Collections.singleton(food));

        ViewMenuDto viewMenuDto = menuMapper.menuToViewMenu(menu);

        assertThat(viewMenuDto).isNotNull();
        assertThat(viewMenuDto.getName()).isEqualTo(menu.getName());
        assertThat(viewMenuDto.getIsArchived()).isEqualTo(menu.getIsArchived());
        assertThat(viewMenuDto.getFood().contains("Burito")).isEqualTo(menu.getFood().contains("Burito"));
    }
}