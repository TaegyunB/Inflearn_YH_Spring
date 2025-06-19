package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /*
        JPA는 EntityManager로 모든 걸 동작함
        JPA를 쓰려면 EntityManager를 주입 받아야함
        이걸 통해 DB 저장, 조회, 삭제, 퀴리 작성 등을 실행함
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);  // 객체를 DB에 영속 상태로 저장함
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);  // em.find()는 PK 기준으로 조회함
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)  // :name은 파라미터를 바인딩하기 때문에 파라미터 추가
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
