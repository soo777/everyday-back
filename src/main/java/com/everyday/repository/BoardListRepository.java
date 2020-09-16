package com.everyday.repository;

import com.everyday.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BoardListRepository extends JpaRepository<Board, Integer> {
    Board findById(int id);
}
