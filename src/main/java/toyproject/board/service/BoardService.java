package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.domain.Board;
import toyproject.board.dto.BoardDto;
import toyproject.board.exception.NoSuchException;
import toyproject.board.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시판 등록
     */
    public Long createBoard(BoardDto boardDto) {
        Board savedBoard = boardRepository.save(dtoToEntity(boardDto));
        return savedBoard.getId();
    }

    /**
     * 게시판 수정
     */
    public Long modifiedBoard(BoardDto boardDto) {
        Board findBoard = checkBoard(boardDto.getId());
        findBoard.change(boardDto.getExplanation(), boardDto.getNotion());
        return findBoard.getId();
    }

    /**
     * 게시판 삭제
     */
    public void deleteBoard(Long boardId) {
        boardRepository.delete(checkBoard(boardId));
    }

    //게시판 조회


    private Board dtoToEntity(BoardDto boardDto) {
        return new Board(boardDto.getTitle(), boardDto.getExplanation(), boardDto.getNotion());
    }

    private Board checkBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> {
            throw new NoSuchException("등록되지 않은 게시판입니다.");
        });
    }
}
