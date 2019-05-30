package md.codefactory.menuservice.service;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.exceptions.FoodAlreadyExistException;
import md.codefactory.menuservice.exceptions.FoodNotFoundException;
import md.codefactory.menuservice.repository.FoodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public Food saveFood(Food food) throws FoodAlreadyExistException {

        Optional<Food> existFood = foodRepository.findByName(food.getName());
        if (existFood.isPresent()){
            throw  new FoodAlreadyExistException("Food with name = " + food.getName() + " already exist !");
        }

        return foodRepository.save(food);
    }

    public Food updateFood(Long id, Food food) throws FoodNotFoundException, FoodAlreadyExistException {

        Food updatedFood = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException("Food with id = " + id + " not found !"));

        BeanUtils.copyProperties(food, updatedFood, "id");

        Optional<Food> existFood = foodRepository.findByName(updatedFood.getName());
        if (existFood.isPresent() && !existFood.get().getId().equals(id)){
            throw  new FoodAlreadyExistException("Food with name = " + updatedFood.getName() + " already exist !");
        }

        return foodRepository.save(updatedFood);
    }
}
