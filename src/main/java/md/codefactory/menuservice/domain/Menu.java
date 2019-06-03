package md.codefactory.menuservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = "food")
@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "menu_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "menu_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_archived", columnDefinition = "false")
    private Boolean isArchived;

    @ManyToMany
    @JoinTable(name = "menu_food",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> food;

    public Menu(String name,Boolean isArchived, Food... food) {
        this.name = name;
        this.isArchived = isArchived;
        this.food = Stream.of(food).collect(Collectors.toSet());
        this.food.forEach(x -> x.getMenus().add(this));
    }
}
