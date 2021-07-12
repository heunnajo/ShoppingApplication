package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //1.회원 가입
    @Transactional
    public Long join(Member member){
        //중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION : 최적화하자면 갯수만 카운팅해서 0보다 크면 으로 해줘도 된다!
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //2. 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    //3. 회원 단건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
