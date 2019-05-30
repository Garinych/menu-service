package md.codefactory.menuservice.mapper;

import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.mapper.dto.NewMenuDto;
import md.codefactory.menuservice.mapper.dto.UpdateMenuDto;
import md.codefactory.menuservice.mapper.dto.ViewMenuDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    Menu newMenuToMenu(NewMenuDto newMenuDto);

    NewMenuDto menuToNewMenu(Menu menu);

    Menu updateMenuToMenu(UpdateMenuDto updateMenuDto);

    UpdateMenuDto menuToUpdateMenu(Menu menu);

    Menu viewMenuToMenu(ViewMenuDto viewMenuDto);

    ViewMenuDto menuToViewMenu(Menu menu);
}
