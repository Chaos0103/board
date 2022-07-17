package toyproject.board.repository.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;
import toyproject.board.domain.Member;
import toyproject.board.domain.QMember;
import toyproject.board.repository.custom.MemberRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.*;
import static toyproject.board.domain.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> findByName(String name) {
        return queryFactory
                .selectFrom(member)
                .where(
                        nameEq(name)
                )
                .fetch();
    }

    @Override
    public Optional<Member> loginMember(String loginId, String password) {
        Member member = queryFactory
                .selectFrom(QMember.member)
                .where(
                        QMember.member.loginId.eq(loginId),
                        QMember.member.password.eq(password)
                )
                .fetchOne();
        return Optional.ofNullable(member);
    }

    private BooleanExpression nameEq(String name) {
        return hasText(name) ? member.name.eq(name) : null;
    }
}
