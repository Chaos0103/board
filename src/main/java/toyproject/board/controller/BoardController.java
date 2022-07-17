package toyproject.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.board.controller.form.BoardForm;
import toyproject.board.controller.form.CommentForm;
import toyproject.board.controller.form.PostForm;
import toyproject.board.domain.ReportType;
import toyproject.board.dto.BoardDto;
import toyproject.board.dto.CommentDto;
import toyproject.board.dto.PostDto;
import toyproject.board.dto.ReportDto;
import toyproject.board.service.BoardService;
import toyproject.board.service.CommentService;
import toyproject.board.service.PostService;
import toyproject.board.service.ReportService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;
    private final CommentService commentService;
    private final ReportService reportService;

    @GetMapping("/board/new")
    public String createBoard(@ModelAttribute("boardForm") BoardForm boardForm) {
        return "/board/createBoardForm";
    }

    @PostMapping("/board/new")
    public String create(BoardForm boardForm) {
        BoardDto boardDto = getBoardDto(boardForm);
        boardService.createBoard(boardDto);
        return "redirect:/";
    }

    @GetMapping("/board")
    public String boardList(Model model) {
        List<BoardDto> boardDtoList = boardService.searchAll();
        model.addAttribute("boardDtoList", boardDtoList);
        return "/board/boardList";
    }

    @GetMapping("/board/{boardId}/notion")
    public String notion(@PathVariable Long boardId) {
        return "/board/notion";
    }

    @GetMapping("/board/{boardId}/post")
    public String postList(@PathVariable Long boardId, Model model) {
        List<PostDto> postDtoList = postService.searchPost(boardId);
        model.addAttribute("boardId", boardId);
        model.addAttribute("postDtoList", postDtoList);
        return "/post/postList";
    }

    @GetMapping("/board/{boardId}/post/new")
    public String createPost(@PathVariable Long boardId, @ModelAttribute("postForm") PostForm postForm) {
        return "/post/createPostForm";
    }

    @PostMapping("/board/{boardId}/post/new")
    public String create(@PathVariable Long boardId, PostForm postForm, HttpSession session) {
        PostDto postDto = new PostDto(postForm.getTitle(), postForm.getContent(), postForm.getAnonymous());
        postDto.setBoardId(boardId);
        postDto.setMemberId((Long) session.getAttribute("loginId"));
        postService.createPost(postDto);
        return "redirect:/board/{boardId}/post";
    }

    @GetMapping("/board/{boardId}/post/{postId}/content")
    public String postContent(@PathVariable Long boardId, @PathVariable Long postId, @ModelAttribute("commentForm") CommentForm commentForm, Model model) {
        PostDto postDto = postService.findOneById(postId);
        List<CommentDto> commentDtoList = commentService.searchComment(postId);
        model.addAttribute("postDto", postDto);
        model.addAttribute("commentDtoList", commentDtoList);
        return "/post/postContent";
    }

    @PostMapping("/board/{boardId}/post/{postId}/content")
    public String comment(@PathVariable Long boardId, @PathVariable Long postId, CommentForm commentForm, HttpSession session) {
        CommentDto commentDto = new CommentDto(commentForm.getContent(), commentForm.getAnonymous());
        commentDto.setPostId(postId);
        commentDto.setMemberId((Long) session.getAttribute("loginId"));
        commentService.createComment(commentDto);
        return "redirect:/board/{boardId}/post/{postId}/content";
    }

    @PostMapping("/board/{boardId}/post/{postId}/content/{commentId}/good")
    public String commentGood(@PathVariable Long commentId, @PathVariable Long boardId, @PathVariable Long postId) {
        commentService.goodComment(commentId);
        return "redirect:/board/{boardId}/post/{postId}/content";
    }

    @PostMapping("/board/{boardId}/post/{postId}/good")
    public String postGood(@PathVariable Long boardId, @PathVariable Long postId) {
        postService.good(postId);
        return "redirect:/board/{boardId}/post/{postId}/content";
    }

    @PostMapping("/board/{boardId}/post/{postId}/report")
    public String postReport(@PathVariable Long boardId, @PathVariable Long postId, HttpSession session) {
        ReportDto reportDto = new ReportDto(ReportType.TYPE1);
        reportDto.setPostId(postId);
        reportDto.setMemberId((Long) session.getAttribute("loginId"));
        reportService.reportPost(reportDto);
        return "redirect:/board/{boardId}/post/{postId}/content";
    }

    private BoardDto getBoardDto(BoardForm boardForm) {
        return new BoardDto(boardForm.getTitle(), boardForm.getExplanation(), boardForm.getNotion());
    }
}
