package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {//구현체를 가지고 할 것이기 때문에 추상클래스로 만든다. 상속관계로 되어있고 이것들을 매핑해야한다.
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy ="items")
//    @JoinTable(name="category_item")
    private List<Category> categories = new ArrayList<>();
}
