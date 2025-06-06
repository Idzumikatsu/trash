package com.example.scheduletracker.controller;

import com.example.scheduletracker.entity.TimeSlot;
import com.example.scheduletracker.service.TimeSlotService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/time-slots")
public class TimeSlotController {
  private final TimeSlotService svc;

  public TimeSlotController(TimeSlotService svc) {
    this.svc = svc;
  }

  @GetMapping
  public List<TimeSlot> all(@RequestParam(required = false) UUID teacherId) {
    if (teacherId != null) {
      return svc.findByTeacherId(teacherId);
    }
    return svc.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TimeSlot> get(@PathVariable UUID id) {
    return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public TimeSlot create(@RequestBody TimeSlot slot) {
    return svc.save(slot);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TimeSlot> update(@PathVariable UUID id, @RequestBody TimeSlot slot) {
    return svc.findById(id)
        .map(
            existing -> {
              slot.setId(id);
              return ResponseEntity.ok(svc.save(slot));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    svc.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
