package com.example.scheduletracker.repository;

import com.example.scheduletracker.entity.VerificationToken;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {
  Optional<VerificationToken> findByToken(UUID token);
}
