package GaBom.Bom.repository;

import GaBom.Bom.model.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired BoardRepository boardRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    public void Save() throws Exception{
        // given
        Board board2 = new Board(1L, "TITLE", "COMMENT", "2022-03-17", "2023-03-18", 3000L);
        System.out.println("board2 = " + board2);

        boardRepository.save(board2);

        System.out.println("board2 = " + board2);

        // when
        List<Board> all = boardRepository.findAll();
        // then
        System.out.println("all = " + all);
        Assertions.assertThat(all.size()).isEqualTo(1);
    }
}