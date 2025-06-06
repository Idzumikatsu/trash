// src/main/java/com/example/scheduletracker/service/GroupService.java
package com.example.scheduletracker.service;

import com.example.scheduletracker.entity.Group;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupService {
  Group save(Group group);

  List<Group> findAll();

  Optional<Group> findById(UUID id);

  void deleteById(UUID id);
}
