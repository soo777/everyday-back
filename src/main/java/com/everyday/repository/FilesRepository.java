package com.everyday.repository;

import com.everyday.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FilesRepository extends JpaRepository<Files, Integer> {
}
