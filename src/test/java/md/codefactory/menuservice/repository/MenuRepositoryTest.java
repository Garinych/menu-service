package md.codefactory.menuservice.repository;

import md.codefactory.menuservice.domain.Menu;
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
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void should_find_menu_by_id() {
        Optional<Menu> actualMenu = menuRepository.findById(1L);

        assertTrue(actualMenu.isPresent());
    }

    @Test
    public void should_find_menu_by_name() {
        Optional<Menu> actualMenu = menuRepository.findByName("1 Mexican Menu");

        assertTrue(actualMenu.isPresent());
    }

    @Test
    public void should_find_menu_by_name_not_exist() {
        Optional<Menu> actualMenu = menuRepository.findByName("7 Mexican Menu");

        assertFalse(actualMenu.isPresent());
    }

}