package com.rafernan.app.task;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.rafernan.app.user.User;


public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findByUser(User user);
}
