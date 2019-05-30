package md.codefactory.menuservice.service;

import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.exceptions.MenuAlreadyExistException;
import md.codefactory.menuservice.exceptions.MenuNotFoundException;
import md.codefactory.menuservice.repository.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Menu saveMenu(Menu menu) throws MenuAlreadyExistException {

        Optional<Menu> existMenu = menuRepository.findByName(menu.getName());
        if (existMenu.isPresent()){
            throw  new MenuAlreadyExistException("Menu with name = " + menu.getName() + " already exist!");
        }

        return menuRepository.save(menu);
    }

    public Menu updateMenu(Long id, Menu menu) throws MenuNotFoundException, MenuAlreadyExistException {

        Menu updatedMenu = menuRepository.findById(id).orElseThrow(() ->
                new MenuNotFoundException("Menu with id = " + id + " not found !"));

        BeanUtils.copyProperties(menu, updatedMenu, "id");

        Optional<Menu> existMenu = menuRepository.findByName(menu.getName());
        if (existMenu.isPresent() && !existMenu.get().getId().equals(id)){
            throw  new MenuAlreadyExistException("Menu with name = " + menu.getName() + " already exist!");
        }

        return menuRepository.save(updatedMenu);
    }
}
