package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

//관례로 order가 되버리는 것을 방지하기 위해 반드시 @Table 애노테이션을 붙여준다!
@Entity
@Table(name="orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;//(FK)Member-Order 연관관계 주인.

    @OneToMany(mappedBy = "order",cascade=CascadeType.ALL)//orderItem의 order에 의해 매핑된다!
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade=CascadeType.ALL)//LAZY로 하지않으면 delivery 조회되는 순간 delivery order 찾고 난리남. JPA 성능 저하.
    @JoinColumn(name="delivery_id")//원래는 delivery, order 각각 persist해줘야하지만
    private Delivery delivery;//캐스케이드 추가하면 order 저장할 때 delivery도 같이 persist

    private LocalDateTime orderDate;//주문시간. Date를 쓰면 애노테이션 달아야하지만 자바 LDT 이용하면 Hibernate가 자동 지원.

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//enum 타입으로 주문상태 [ORDER,CANCEL]

    //==연관관계 편의 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
