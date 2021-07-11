package jpabook.jpashop.domain;

import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;//Item과 OrderItem과 관계 매핑!

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;//FK가 된다. order와 매핑 :다대일(ManytoOne). 하나의 order는 여러개의 orderitem을 가질 수 있다!

    private int orderPrice;//주문 가격
    private int count;//주문 수량
}
