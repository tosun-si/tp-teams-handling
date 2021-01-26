package org.tosunsi.tp.teams.handling.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tosunsi.tp.teams.handling.api.dto.TeamDto;
import org.tosunsi.tp.teams.handling.api.entity.TeamEntity;
import org.tosunsi.tp.teams.handling.api.service.TeamService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeams() {
        return teamService.findTeams().stream()
                .map(this::toDto)
                .collect(toList());
    }

    @GetMapping("/teams/{id}")
    public TeamDto getTeamById(@PathVariable Long id) {
        final TeamEntity foundTeam = teamService.findTeamById(id);

        return toDto(foundTeam);
    }

    @PostMapping("/teams")
    public TeamDto addTeam(@RequestBody TeamDto team) {
        final TeamEntity savedTeam = teamService.saveTeam(toEntity(team));

        return toDto(savedTeam);
    }

    @PutMapping("/teams/{id}")
    public TeamDto updateTeam(@PathVariable Long id, @RequestBody TeamDto team) {
        var teamWithId = new TeamDto(id, team.slogan(), team.slogan());
        final TeamEntity savedTeam = teamService.saveTeam(toEntity(teamWithId));

        return toDto(savedTeam);
    }

    @DeleteMapping("/teams/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        teamService.deleteTeam(id);
    }

    private TeamDto toDto(final TeamEntity teamEntity) {
        return new TeamDto(teamEntity.getId(), teamEntity.getName(), teamEntity.getSlogan());
    }

    private TeamEntity toEntity(final TeamDto teamDto) {
        return new TeamEntity()
                .name(teamDto.name())
                .slogan(teamDto.slogan());
    }
}
