package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        //중복 이름 회원 차단
        //validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getUserName())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException("이미 존재하는 회원입니다.");
//                        });
//    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 아이디로 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
