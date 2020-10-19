package com.everyday.repository;

import com.everyday.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findById(int id);

    List<Item> findAllByBoardKey(Integer boardKey);

    void deleteByItemKey(Integer itemKey);
}
