package md.codefactory.menuservice.controller;

import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping
    public Menu saveMenu(@RequestBody Menu menu){

        return menuRepository.save(menu);
    }
}
