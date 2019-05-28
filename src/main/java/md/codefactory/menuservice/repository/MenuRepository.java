package md.codefactory.menuservice.repository;

import md.codefactory.menuservice.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Optional<Menu> findById(Long id);
    Optional<Menu> findByName(String name);
}
