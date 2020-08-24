package com.everyday.repository;

import com.everyday.model.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BoardListRepository extends JpaRepository<BoardList, Integer> {

    BoardList findById(int id);

}
