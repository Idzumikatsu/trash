package com.example.scheduletracker.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;

/** Lesson entity. */
@Entity
@Table(name = "lesson")
public class Lesson {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @Column(nullable = false)
  private OffsetDateTime dateTime;

  @Column(nullable = false)
  private Integer duration; // minutes

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status = Status.SCHEDULED;

  @ManyToOne(optional = false)
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @ManyToOne(optional = false)
  @JoinColumn(name = "group_id")
  private Group group;

  public Lesson() {}

  public Lesson(
      UUID id,
      OffsetDateTime dateTime,
      Integer duration,
      Status status,
      Teacher teacher,
      Group group) {
    this.id = id;
    this.dateTime = dateTime;
    this.duration = duration;
    this.status = status;
    this.teacher = teacher;
    this.group = group;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public OffsetDateTime getDateTime() {
    return dateTime;
  }

  public void setDateTime(OffsetDateTime dateTime) {
    this.dateTime = dateTime;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private UUID id;
    private OffsetDateTime dateTime;
    private Integer duration;
    private Status status = Status.SCHEDULED;
    private Teacher teacher;
    private Group group;

    public Builder id(UUID id) {
      this.id = id;
      return this;
    }

    public Builder dateTime(OffsetDateTime dateTime) {
      this.dateTime = dateTime;
      return this;
    }

    public Builder duration(Integer duration) {
      this.duration = duration;
      return this;
    }

    public Builder status(Status status) {
      this.status = status;
      return this;
    }

    public Builder teacher(Teacher teacher) {
      this.teacher = teacher;
      return this;
    }

    public Builder group(Group group) {
      this.group = group;
      return this;
    }

    public Lesson build() {
      return new Lesson(id, dateTime, duration, status, teacher, group);
    }
  }

  public enum Status {
    SCHEDULED,
    CONFIRMED,
    CANCELED
  }
}
