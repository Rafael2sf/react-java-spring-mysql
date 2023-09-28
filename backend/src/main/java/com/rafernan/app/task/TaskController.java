package com.rafernan.app.task;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafernan.app.task.dto.CreateTaskDto;
import com.rafernan.app.user.User;
import com.rafernan.app.user.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
	
  @Autowired
  private final TaskRepository taskRepository;

  private final UserRepository userRepository;

  @GetMapping
  public List<Task> getMany(Principal principal) {
    User user = userRepository.findByUsername(principal.getName()).get();
    return taskRepository.findByUser(user);
  }

  @PostMapping
  public Task createUnique(
    @Valid @RequestBody CreateTaskDto createTaskDto,
    Principal principal)
  {
    User user = userRepository.findByUsername(principal.getName()).get();
    return taskRepository.save(
      Task.builder().createdAt(new Date()).description(createTaskDto.description()).title(createTaskDto.title()).user(user).build()
    );
  }

  @DeleteMapping("/{id}")
  public void deleteUnique(
    @PathVariable(value="id") String id,
    Principal principal
  ) {
    final Integer ID = Integer.parseInt(id);
    Optional<Task> task = taskRepository.findById(ID);
    if (!task.isPresent())
      throw new RuntimeException("Task not found");
    User user = userRepository.findByUsername(principal.getName()).get();
    if (!task.get().getUser().getId().equals(user.getId()))
      throw new RuntimeException("Cannot delete this task");
    taskRepository.deleteById(ID);
  }
}
