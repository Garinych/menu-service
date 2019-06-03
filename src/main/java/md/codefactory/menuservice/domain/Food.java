package md.codefactory.menuservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "food")
public class Food {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "food_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "food_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToMany(mappedBy = "food")
    private Set<Menu> menus = new HashSet<>();

}
