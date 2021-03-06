package com.everyday.repository;

import com.everyday.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByBoardKey(int boardKey);

    @Query(value = "select a.boardKey, a.boardName, a.creator, a.host " +
            "from board a join boardList b " +
            "on a.boardKey = b.boardKey " +
            "where b.userId = ?", nativeQuery = true)
    List<Board> findByUserId(String userId);
}
