package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    //커맨드와 쿼리를 분리하는 원칙에 의해 member를 반환하지 않고 member.getId()로 반환!
    //저장하고 나면 side-effect를 일으키 커맨드성이기 때문에 리턴값을 안 만든다!
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
