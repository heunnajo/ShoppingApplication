package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        //JPA에 저장하기 전까지는 id값이 없다.새로 생성하는 객체라는 뜻
        if(item.getId() == null){//처음에는 id라는 게 없기 때문에.
            em.persist(item);
        } else{//이미 DB에 등록된 것이라는 뜻.
            em.merge(item);
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class,id);
    }
    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
