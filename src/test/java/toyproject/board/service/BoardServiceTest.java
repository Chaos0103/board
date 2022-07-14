package toyproject.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Board;
import toyproject.board.dto.BoardDto;
import toyproject.board.repository.BoardRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired BoardService boardService;
    @Autowired BoardRepository boardRepository;

    @Test
    void createBoard() {
        BoardDto boardDto = new BoardDto("자유게시판", "설명", "공지사항");

        Long boardId = boardService.createBoard(boardDto);

        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
    }

    @Test
    void modifiedBoard() {
        Board newBoard = new Board("자유게시판", "설명", "공지사항");
        Board savedBoard = boardRepository.save(newBoard);

        BoardDto boardDto = new BoardDto(savedBoard.getTitle(), "", "수정된 공지사항");
        boardDto.setId(savedBoard.getId());
        Long boardId = boardService.modifiedBoard(boardDto);

        Board findBoard = boardRepository.findById(boardId).get();
        assertThat(findBoard.getNotion()).isEqualTo("수정된 공지사항");
        assertThat(findBoard.getExplanation()).isEqualTo("설명");
    }

    @Test
    void deleteBoard() {
        Board newBoard = new Board("자유게시판", "설명", "공지사항");
        Board savedBoard = boardRepository.save(newBoard);

        boardService.deleteBoard(savedBoard.getId());

        Optional<Board> findBoard = boardRepository.findById(savedBoard.getId());
        assertThat(findBoard).isEmpty();
    }
}