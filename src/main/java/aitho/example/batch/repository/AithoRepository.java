package aitho.example.batch.repository;

import aitho.example.batch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AithoRepository extends JpaRepository<User, Integer> {
}
