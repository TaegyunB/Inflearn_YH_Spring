package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;  // 테스트 대상
    MemoryMemberRepository memberRepository;  // 실제 DB 없이 메모리로 회원 저장 (테스트용)

    /*
     * 각 테스트 실행 전에 상항 새로 객체를 생성
     * 테스트마다 깨끗한 상태로 시작하도록 보장
     * 테스트끼리 영향을 주면 안 됨 -> 독립성 유지
     */
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    /*
     * 각 테스트가 끝날 때마다 저장소 비움(초기화)
     */
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();  // MemoryMemberRepository 내부에 있는 store을 비워줌
    }

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