package toyproject.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Board;
import toyproject.board.domain.Member;
import toyproject.board.domain.Post;
import toyproject.board.dto.PostDto;
import toyproject.board.repository.BoardRepository;
import toyproject.board.repository.MemberRepository;
import toyproject.board.repository.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired PostService postService;
    @Autowired PostRepository postRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired BoardRepository boardRepository;

    @Test
    void createPost() {
        Member member = getMember();
        Board board = getBoard();
        PostDto postDto = new PostDto("test post title", "test post content", true);
        postDto.setMemberId(member.getId());
        postDto.setBoardId(board.getId());

        Long postId = postService.createPost(postDto);

        Optional<Post> findPost = postRepository.findById(postId);
        assertThat(findPost).isPresent();
    }

    @Test
    void modifyPost() {
        Post post = new Post(getMember(), getBoard(), "test post title", "test post content", true);
        Post savedPost = postRepository.save(post);

        PostDto postDto = new PostDto("", "change post content", true);
        postDto.setId(savedPost.getId());
        Long postId = postService.modifyPost(postDto);

        Post findPost = postRepository.findById(postId).get();
        assertThat(findPost.getTitle()).isEqualTo("test post title");
        assertThat(findPost.getContent()).isEqualTo("change post content");
    }

    @Test
    void removePost() {
        Post post = new Post(getMember(), getBoard(), "test post title", "test post content", true);
        Post savedPost = postRepository.save(post);

        postService.removePost(savedPost.getId());

        Optional<Post> findPost = postRepository.findById(savedPost.getId());
        assertThat(findPost).isEmpty();
    }

    @Test
    void good() {
        Post post = new Post(getMember(), getBoard(), "test post title", "test post content", true);
        Post savedPost = postRepository.save(post);

        Long postId = postService.good(savedPost.getId());

        Post findPost = postRepository.findById(postId).get();
        assertThat(findPost.getGoodCount()).isEqualTo(1);
    }

    private Member getMember() {
        Member newMember = new Member("testId", "testPw", "tester", "0101234568", "testNickname");
        return memberRepository.save(newMember);
    }

    private Board getBoard() {
        return boardRepository.save(new Board("자유게시판", "설명", "공지사항"));
    }
}