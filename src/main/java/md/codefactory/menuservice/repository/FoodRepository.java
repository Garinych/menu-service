package md.codefactory.menuservice.repository;

import md.codefactory.menuservice.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findById(Long id);
    Optional<Food> findByName(String name);
}
