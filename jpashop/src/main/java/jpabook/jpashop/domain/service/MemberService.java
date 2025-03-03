package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)//데이터 변경하거나 그럴땐 Transactional 필수. 또 lazy같은거 쓰려면 필요.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional//기본적으로 readOnly=false 임. 읽기만 있을 경우엔 (조회) true로 해야 성능 좋음.
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한명 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}