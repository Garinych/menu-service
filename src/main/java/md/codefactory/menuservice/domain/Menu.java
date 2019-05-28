package md.codefactory.menuservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "menus")
public class Menu {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "menu_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_archived")
    private Boolean isArchived;

    @ManyToMany
    @JoinTable(name = "menu_food",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> food = new HashSet<>();
}
