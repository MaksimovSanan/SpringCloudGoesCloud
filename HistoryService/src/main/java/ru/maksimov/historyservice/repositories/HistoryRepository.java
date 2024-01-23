package ru.maksimov.historyservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maksimov.historyservice.models.Question;

public interface HistoryRepository extends JpaRepository<Question, Integer> {
}
