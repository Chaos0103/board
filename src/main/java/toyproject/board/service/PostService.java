package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Board;
import toyproject.board.domain.Member;
import toyproject.board.domain.Post;
import toyproject.board.dto.PostDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.BoardRepository;
import toyproject.board.repository.MemberRepository;
import toyproject.board.repository.PostRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시물 등록
     */
    public Long createPost(PostDto postDto) {
        Post newPost = getNewPost(postDto);
        return postRepository.save(newPost).getId();
    }

    /**
     * 게시물 수정
     */
    public Long modifyPost(PostDto postDto) {
        Post findPost = getPost(postDto.getId());
        findPost.change(postDto.getTitle(), postDto.getContent(), postDto.getAnonymous());
        return findPost.getId();
    }

    /**
     * 게시물 삭제
     */
    public void removePost(Long postId) {
        postRepository.delete(getPost(postId));
    }

    /**
     * 게시물 추천
     */
    public Long good(Long postId) {
        Post findPost = getPost(postId);
        findPost.addGoodCount();
        return findPost.getId();
    }

    /**
     * 전체 게시물 조회
     */
    @Transactional(readOnly = true)
    public List<PostDto> searchPost(Long boardId) {
        return postRepository.findByBoardId(boardId).stream()
                .map(PostDto::new)
                .toList();
    }

    /**
     * 게시물 단건 조회
     */
    @Transactional(readOnly = true)
    public PostDto findOneById(Long postId) {
        Post post = postRepository.findContentById(postId);
        return new PostDto(post);
    }
    //게시물 신고

    private Post getNewPost(PostDto postDto) {
        Board findBoard = getBoard(postDto.getBoardId());
        Member findMember = getMember(postDto.getMemberId());
        return new Post(findMember, findBoard, postDto.getTitle(), postDto.getContent(), postDto.getAnonymous());
    }

    private Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 게시판입니다.");
        });
    }

    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 회원입니다.");
        });
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 게시물입니다.");
        });
    }
}
