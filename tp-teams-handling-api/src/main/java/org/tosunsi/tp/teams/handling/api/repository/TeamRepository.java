package org.tosunsi.tp.teams.handling.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tosunsi.tp.teams.handling.api.entity.TeamEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
