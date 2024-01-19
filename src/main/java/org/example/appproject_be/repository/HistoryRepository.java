package org.example.appproject_be.repository;

import org.example.appproject_be.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
