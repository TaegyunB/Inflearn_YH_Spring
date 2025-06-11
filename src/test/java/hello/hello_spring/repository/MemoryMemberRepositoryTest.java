package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 서로 순서와 관계없이, 서로 의존관계 없이 설계가 되어야 함 -> Test 하나가 끝날 때 마다 repository를 깔끔하게 지워줘야함
    // 테스트가 끝날 때 마다 repository를 깔끔하게 지워주는 코드 -> 아니면 오류 발생 가능성 있음(순서가 상관 없어짐)
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();  // 반환값이 Optional이면 get()으로 꺼낼 수 있음 -> 좋은 방법은 아님(테스트케이스니깐 적용)
//        Assertions.assertEquals(member, result);  -> expected랑 actual이 똑같은 지 확인
        assertThat(member).isEqualTo(result);  // 이 방법을 많이 사용함
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
