package md.codefactory.menuservice.repository;

import md.codefactory.menuservice.domain.Food;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = {"/data.sql"})
public class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void should_find_food_by_id() {
        Optional<Food> actualFood = foodRepository.findById(1L);

        assertTrue(actualFood.isPresent());
    }

    @Test
    public void should_find_food_by_name() {
        Optional<Food> actualFood = foodRepository.findByName("Burito");

        assertTrue(actualFood.isPresent());
    }

    @Test
    public void shoul_find_food_by_name_not_exist() {
        Optional<Food> actualFood = foodRepository.findByName("Chezar");

        assertFalse(actualFood.isPresent());
    }
}