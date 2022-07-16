package toyproject.board.repository.custom;

import toyproject.board.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByName(String name);
}
