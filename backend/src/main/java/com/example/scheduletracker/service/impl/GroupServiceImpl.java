// src/main/java/com/example/scheduletracker/service/impl/GroupServiceImpl.java
package com.example.scheduletracker.service.impl;

import com.example.scheduletracker.entity.Group;
import com.example.scheduletracker.repository.GroupRepository;
import com.example.scheduletracker.service.GroupService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
  private final GroupRepository repo;

  public GroupServiceImpl(GroupRepository repo) {
    this.repo = repo;
  }

  @Override
  public Group save(Group group) {
    return repo.save(group);
  }

  @Override
  public List<Group> findAll() {
    return repo.findAll();
  }

  @Override
  public Optional<Group> findById(UUID id) {
    return repo.findById(id);
  }

  @Override
  public void deleteById(UUID id) {
    repo.deleteById(id);
  }
}
