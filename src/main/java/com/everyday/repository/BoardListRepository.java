package com.everyday.repository;

import com.everyday.model.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BoardListRepository extends JpaRepository<BoardList, Integer> {
    List<BoardList> findByBoardKey(int boardKey);
}
