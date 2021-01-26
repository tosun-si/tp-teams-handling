package org.tosunsi.tp.teams.handling.api.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.tosunsi.tp.teams.handling.api.controller.handler.TeamExceptionHandler;
import org.tosunsi.tp.teams.handling.api.entity.TeamEntity;
import org.tosunsi.tp.teams.handling.api.exception.TeamInvalidException;
import org.tosunsi.tp.teams.handling.api.exception.TeamNotFoundException;
import org.tosunsi.tp.teams.handling.api.service.TeamService;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @InjectMocks
    private TeamExceptionHandler teamExceptionHandler;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(teamController, teamExceptionHandler);
    }

    @Test
    public void when_getTeams_thenStatus200() {
        TeamEntity teamEntity = new TeamEntity()
                .id(1L)
                .name("test")
                .slogan("test");

        // Given.
        final var mockedTeams = List.of(teamEntity);

        when(teamService.findTeams()).thenReturn(mockedTeams);

        // When / then.
        final var path = "teams";
        given()
                .when()
                .get(path)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void givenExistingTeamId_whenGetTeamById_thenStatus200() {

        // Given.
        final Long teamId = 1L;
        final var mockedTeam = new TeamEntity()
                .id(1L)
                .name("test")
                .slogan("test");

        when(teamService.findTeamById(teamId)).thenReturn(mockedTeam);

        // When / then.
        final var path = "teams/{id}";
        given()
                .when()
                .get(path, teamId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void givenNonExistingTeamId_whenGetTeamById_thenStatus404() {
        // Given.
        final Long teamId = 20L;

        when(teamService.findTeamById(teamId)).thenThrow(new TeamNotFoundException("Team not found"));

        // When / then.
        final var path = "teams/{id}";
        given()
                .when()
                .get(path, teamId)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void givenTeam_whenPostTeam_thenStatus200() {
        // Given.
        final var mockedTeam = new TeamEntity()
                .id(1L)
                .name("test")
                .slogan("test");

        when(teamService.saveTeam(any())).thenReturn(mockedTeam);

        // When / then.
        final var path = "teams";
        given()
                .contentType(ContentType.JSON)
                .body(mockedTeam)
                .when()
                .post(path)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void givenTeamWithoutSloganField_whenPostTeam_thenStatus400() {
        // Given.
        final var mockedTeam = new TeamEntity()
                .id(1L)
                .name("test")
                .slogan("");

        when(teamService.saveTeam(any())).thenThrow(new TeamInvalidException("No slogan"));

        // When / then.
        final var path = "teams";
        given()
                .contentType(ContentType.JSON)
                .body(mockedTeam)
                .when()
                .post(path)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenTeam_whenPutTeam_thenStatus200() {
        // Given.
        final var teamId = 1L;
        final var mockedTeam = new TeamEntity()
                .id(teamId)
                .name("test")
                .slogan("test");

        when(teamService.saveTeam(any())).thenReturn(mockedTeam);

        // When / then.
        final var path = "teams/{id}";
        given()
                .contentType(ContentType.JSON)
                .body(mockedTeam)
                .when()
                .put(path, teamId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void givenTeamWithoutSloganField_whenPutTeam_thenStatus400() {
        // Given.
        final var teamId = 1L;
        final var mockedTeam = new TeamEntity()
                .id(teamId)
                .name("test")
                .slogan("");

        when(teamService.saveTeam(any())).thenThrow(new TeamInvalidException("No slogan"));

        // When / then.
        final var path = "teams/{id}";
        given()
                .contentType(ContentType.JSON)
                .body(mockedTeam)
                .when()
                .put(path, teamId)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void givenTeamId_when_deleteTeam_thenStatus200() {

        // Given.
        final var teamId = 1L;

        // When / then.
        final var path = "teams/{id}";
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(path, teamId)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
