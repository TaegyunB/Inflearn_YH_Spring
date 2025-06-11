package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

/*
    동시성 문제가 고려되어 있음 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null 값 반환 가능성이 있으면 Optional로 묶어
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // store의 모든 값들을 스트림으로 변환 -> Stream으로 변환하면 컬렉션의 데이터를 순차적으로 처리할 수 있는 파이프라인이 생성됨
                .filter(member -> member.getName().equals(name))  // 각 멤버의 이름이 매개변수로 받은 name과 정확히 일치하는 것만 필터링
                .findAny();  // 조건에 맞는 요소 중 아무거나 하나를 찾아서 Optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
