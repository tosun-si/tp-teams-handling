package org.tosunsi.tp.teams.handling.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tosunsi.tp.teams.handling.api.entity.TeamEntity;
import org.tosunsi.tp.teams.handling.api.exception.TeamInvalidException;
import org.tosunsi.tp.teams.handling.api.exception.TeamNotFoundException;
import org.tosunsi.tp.teams.handling.api.repository.TeamRepository;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamEntity> findTeams() {
        return teamRepository.findAll();
    }

    public TeamEntity findTeamById(final Long id) {
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team was found from database by ID : " + id));
    }

    @Transactional
    public TeamEntity saveTeam(TeamEntity team) {
        if (team.getSlogan().equals("")) {
            throw new TeamInvalidException("No slogan");
        }

        return teamRepository.save(team);
    }

    @Transactional
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
