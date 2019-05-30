package md.codefactory.menuservice.controller;

import lombok.RequiredArgsConstructor;
import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.exceptions.MenuAlreadyExistException;
import md.codefactory.menuservice.exceptions.MenuNotFoundException;
import md.codefactory.menuservice.mapper.MenuMapper;
import md.codefactory.menuservice.mapper.dto.NewMenuDto;
import md.codefactory.menuservice.mapper.dto.UpdateMenuDto;
import md.codefactory.menuservice.mapper.dto.ViewMenuDto;
import md.codefactory.menuservice.repository.MenuRepository;
import md.codefactory.menuservice.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    private final MenuService menuService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewMenuDto saveMenu(@Valid @RequestBody NewMenuDto newMenuDto) throws MenuAlreadyExistException {

        Menu menu = menuMapper.newMenuToMenu(newMenuDto);
        menuService.saveMenu(menu);

        return menuMapper.menuToNewMenu(menu);
    }

    @PutMapping("/{id}")
    public UpdateMenuDto updateMenu(@PathVariable Long id, @Valid @RequestBody UpdateMenuDto updateMenuDto) throws MenuNotFoundException, MenuAlreadyExistException {

        Menu updatedMenu = menuMapper.updateMenuToMenu(updateMenuDto);
        menuService.updateMenu(id, updatedMenu);

        return menuMapper.menuToUpdateMenu(updatedMenu);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ViewMenuDto viewMenu(@PathVariable Long id) throws MenuNotFoundException {
        Menu menu = menuRepository.findById(id).orElseThrow(() ->
                new MenuNotFoundException("Menu with id = " + id + " not found !"));

        return menuMapper.menuToViewMenu(menu);
    }

    @GetMapping
    public Page<ViewMenuDto> viewAllMenus(Pageable pageable) {
        return menuRepository.findAll(pageable).map(menu -> menuMapper.menuToViewMenu(menu));
    }
}
