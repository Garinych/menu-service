package md.codefactory.menuservice.controller;

import lombok.RequiredArgsConstructor;
import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.exceptions.MenuNotFoundException;
import md.codefactory.menuservice.mapper.MenuMapper;
import md.codefactory.menuservice.mapper.dto.ViewMenuDto;
import md.codefactory.menuservice.repository.MenuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ViewMenuDto viewMenu(@PathVariable Long id) throws MenuNotFoundException {
        Menu menu = menuRepository.findById(id).orElseThrow(() ->
                new MenuNotFoundException("Menu with id = " + id + " not found !"));

        return menuMapper.menuToViewMenu(menu);
    }

    @GetMapping
    public Page<ViewMenuDto> viewAllMenus(Pageable pageable) {
        return menuRepository.findAll(pageable).map(menuMapper::menuToViewMenu);
    }
}
