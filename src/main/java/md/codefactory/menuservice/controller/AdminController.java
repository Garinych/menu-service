package md.codefactory.menuservice.controller;

import lombok.RequiredArgsConstructor;
import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.exceptions.MenuAlreadyExistException;
import md.codefactory.menuservice.exceptions.MenuNotFoundException;
import md.codefactory.menuservice.mapper.MenuMapper;
import md.codefactory.menuservice.mapper.dto.NewMenuDto;
import md.codefactory.menuservice.mapper.dto.UpdateMenuDto;
import md.codefactory.menuservice.repository.MenuRepository;
import md.codefactory.menuservice.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/menus")
public class AdminController {

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


}
