package md.codefactory.menuservice.controller;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.mapper.dto.NewMenuDto;
import md.codefactory.menuservice.mapper.dto.UpdateMenuDto;
import md.codefactory.menuservice.mapper.dto.ViewFoodDto;
import md.codefactory.menuservice.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Sql(value = {"/data.sql"})
public class AdminControllerTest {

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
        ViewFoodDto food = new ViewFoodDto();
        food.setId(6L);
        food.setName("Crecers");
        food.setPrice("50.0");

        NewMenuDto newMenuDto = new NewMenuDto();
        newMenuDto.setName("American Menu");
        newMenuDto.setIsArchived(true);
        newMenuDto.setFood(Collections.singleton(food));

        this.mockMvc.perform(post("/api/admin/menus")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(newMenuDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_update_food() throws Exception {
        ViewFoodDto food = new ViewFoodDto();
        food.setId(6L);
        food.setName("Crecers");
        food.setPrice("50.0");

        UpdateMenuDto updateMenuDto = new UpdateMenuDto();
        updateMenuDto.setName("1 Mexican Menu");
        updateMenuDto.setIsArchived(true);
        updateMenuDto.setFood(Collections.singleton(food));

        this.mockMvc.perform(put("/api/admin/menus/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(updateMenuDto)))
                .andExpect(status().isOk());

    }

    @Test
    public void should_update_menu_with_already_exist_data() throws Exception {
        ViewFoodDto food = new ViewFoodDto();
        food.setId(6L);
        food.setName("Crecers");
        food.setPrice("50.0");

        UpdateMenuDto updateMenuDto = new UpdateMenuDto();
        updateMenuDto.setName("2 Mexican Menu");
        updateMenuDto.setIsArchived(true);
        updateMenuDto.setFood(Collections.singleton(food));

        this.mockMvc.perform(put("/api/admin/menus/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectAsBytes(updateMenuDto)))
                .andExpect(jsonPath("errorMessage", is("Menu with name = 2 Mexican Menu already exist!")))
                .andExpect(status().isConflict());

    }

}