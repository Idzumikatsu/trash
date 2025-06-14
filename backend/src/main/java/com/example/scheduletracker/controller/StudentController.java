package com.example.scheduletracker.controller;

import com.example.scheduletracker.entity.Student;
import com.example.scheduletracker.service.StudentService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
  private final StudentService svc;

  public StudentController(StudentService svc) {
    this.svc = svc;
  }

  @GetMapping
  public List<Student> all() {
    return svc.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Student> get(@PathVariable UUID id) {
    return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Student create(@RequestBody Student student) {
    return svc.save(student);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> update(@PathVariable UUID id, @RequestBody Student s) {
    return svc.findById(id)
        .map(
            existing -> {
              s.setId(id);
              return ResponseEntity.ok(svc.save(s));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    svc.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
