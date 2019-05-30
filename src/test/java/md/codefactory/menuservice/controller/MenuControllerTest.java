package md.codefactory.menuservice.controller;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.mapper.FoodMapper;
import md.codefactory.menuservice.mapper.MenuMapper;
import md.codefactory.menuservice.mapper.dto.NewFoodDto;
import md.codefactory.menuservice.mapper.dto.NewMenuDto;
import md.codefactory.menuservice.mapper.dto.UpdateFoodDto;
import md.codefactory.menuservice.mapper.dto.UpdateMenuDto;
import md.codefactory.menuservice.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql(value = {"/data.sql"})
public class MenuControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void should_save_menu() throws Exception {
        Food food = new Food();
        food.setId(6L);
        food.setName("Crecers");
        food.setPrice(50.0);

        NewMenuDto newMenuDto = new NewMenuDto();
        newMenuDto.setName("American Menu");
        newMenuDto.setIsArchived(true);
        newMenuDto.setFood(Collections.singleton(food));

        this.mockMvc.perform(post("/api/menus")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(newMenuDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_update_food() throws Exception {
        Food food = new Food();
        food.setId(6L);
        food.setName("Crecers");
        food.setPrice(50.0);

        UpdateMenuDto updateMenuDto = new UpdateMenuDto();
        updateMenuDto.setName("1 Mexican Menu");
        updateMenuDto.setIsArchived(true);
        updateMenuDto.setFood(Collections.singleton(food));

        this.mockMvc.perform(put("/api/menus/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(updateMenuDto)))
                .andExpect(status().isOk());

    }

    @Test
    public void should_update_menu_with_already_exist_data() throws Exception {
        Food food = new Food();
        food.setId(1L);
        food.setName("Burito");
        food.setPrice(50.0);

        UpdateMenuDto updateMenuDto = new UpdateMenuDto();
        updateMenuDto.setName("2 Mexican Menu");
        updateMenuDto.setIsArchived(true);
        updateMenuDto.setFood(Collections.singleton(food));

        this.mockMvc.perform(put("/api/menus/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(updateMenuDto)))
                .andExpect(jsonPath("errorMessage", is("Menu with name = 2 Mexican Menu already exist!")))
                .andExpect(status().isConflict());

    }

    @Test
    public void should_find_all_menus() throws Exception {

        this.mockMvc.perform(get("/api/menus")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}