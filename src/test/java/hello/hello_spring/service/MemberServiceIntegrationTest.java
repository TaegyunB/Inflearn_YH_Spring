package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest  // SpringTest 할 때 사용
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;  // 테스트 대상
    @Autowired MemberRepository memberRepository;  // 실제 DB 없이 메모리로 회원 저장 (테스트용)

    @Test
    void 회원가입() {
        // given(뭔가가 주어줬을 때)
        Member member = new Member();
        member.setName("spring");

        // when(이거를 실행했을 때)
        Long saveId = memberService.join(member);

        // then(결과가 이게 나와야 함)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());  // assertThat(): 실제값과 기대값 비교
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // memberService.join(member2)를 실행했을 때 IllegalStateException이 발생하는지 확인하고, 실제 발생한 예외를 e 변수에 담음
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}