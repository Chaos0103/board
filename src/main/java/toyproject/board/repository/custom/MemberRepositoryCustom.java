package toyproject.board.repository.custom;

import toyproject.board.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    List<Member> findByName(String name);

    Optional<Member> loginMember(String loginId, String password);
}
