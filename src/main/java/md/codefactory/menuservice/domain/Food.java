package md.codefactory.menuservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "food")
public class Food {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "food_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

}
