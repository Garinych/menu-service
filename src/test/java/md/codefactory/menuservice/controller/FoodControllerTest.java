package md.codefactory.menuservice.controller;

import md.codefactory.menuservice.mapper.FoodMapper;
import md.codefactory.menuservice.mapper.dto.NewFoodDto;
import md.codefactory.menuservice.mapper.dto.UpdateFoodDto;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql(value = {"/data.sql"})
public class FoodControllerTest {

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
    public void should_save_food() throws Exception {
        NewFoodDto newFoodDto = new NewFoodDto();
        newFoodDto.setName("Crackers");
        newFoodDto.setPrice("10.0");

        this.mockMvc.perform(post("/api/food")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(newFoodDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_update_food() throws Exception {
        UpdateFoodDto updateFoodDto = new UpdateFoodDto();
        updateFoodDto.setName("Buritos");
        updateFoodDto.setPrice("40.0");

        this.mockMvc.perform(put("/api/food/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(updateFoodDto)))
                .andExpect(status().isOk());

    }

    @Test
    public void should_update_food_with_already_exist_data() throws Exception {
        UpdateFoodDto updateFoodDto = new UpdateFoodDto();
        updateFoodDto.setName("Burito");
        updateFoodDto.setPrice("50.0");

        this.mockMvc.perform(put("/api/food/2")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(updateFoodDto)))
                .andExpect(jsonPath("errorMessage", is("Food with name = Burito already exist !")))
                .andExpect(status().isConflict());

    }

    @Test
    public void should_find_all_food() throws Exception {

        this.mockMvc.perform(get("/api/food")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void should_find_food_by_menu_id() throws Exception {

        this.mockMvc.perform(get("/api/food/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void should_find_food_by_menu_id_not_exist() throws Exception {

        this.mockMvc.perform(get("/api/food/7")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("errorMessage", is("Menu with id = 7 not found !")));

    }

}