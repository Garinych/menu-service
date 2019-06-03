package md.codefactory.menuservice.controller;

import lombok.RequiredArgsConstructor;
import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.domain.Menu;
import md.codefactory.menuservice.exceptions.FoodAlreadyExistException;
import md.codefactory.menuservice.exceptions.FoodNotFoundException;
import md.codefactory.menuservice.exceptions.MenuNotFoundException;
import md.codefactory.menuservice.mapper.FoodMapper;
import md.codefactory.menuservice.mapper.dto.NewFoodDto;
import md.codefactory.menuservice.mapper.dto.UpdateFoodDto;
import md.codefactory.menuservice.mapper.dto.ViewFoodDto;
import md.codefactory.menuservice.repository.FoodRepository;
import md.codefactory.menuservice.repository.MenuRepository;
import md.codefactory.menuservice.service.FoodService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final FoodService foodService;
    private final MenuRepository menuRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewFoodDto saveFood(@Valid @RequestBody NewFoodDto newFoodDto) throws FoodAlreadyExistException {

        Food food = foodMapper.newFoodDtoToFood(newFoodDto);
        foodService.saveFood(food);

        return foodMapper.foodToNewFoodDto(food);
    }

    @PutMapping("/{id}")
    public UpdateFoodDto updateFood(@PathVariable Long id, @Valid @RequestBody UpdateFoodDto updateFoodDto) throws FoodNotFoundException, FoodAlreadyExistException {

        Food updatedFood = foodMapper.updateFoodDtoToFood(updateFoodDto);

        foodService.updateFood(id, updatedFood);

        return foodMapper.foodToUpdateFoodDto(updatedFood);
    }


    @GetMapping
    public Page<ViewFoodDto> findAllFood(Pageable pageable) {
        return foodRepository.findAll(pageable).map(food -> foodMapper.foodToViewFoodDto(food));
    }

    @GetMapping("/{id}")
    public List<ViewFoodDto> findAllMenuFood(@PathVariable Long id) throws MenuNotFoundException {

        Menu menu = menuRepository.findById(id).orElseThrow(() ->
                new MenuNotFoundException("Menu with id = " + id + " not found !"));

        List<Food> foodList = foodRepository.findByMenusId(id);

        return foodList.stream().map(foodMapper::foodToViewFoodDto).collect(Collectors.toList());
    }

}
