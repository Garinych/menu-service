package md.codefactory.menuservice.controller;

import md.codefactory.menuservice.domain.Food;
import md.codefactory.menuservice.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<Food> findAllFood(){
        return foodRepository.findAll();
    }
}
