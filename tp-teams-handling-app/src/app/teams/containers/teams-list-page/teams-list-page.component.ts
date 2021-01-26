import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Team} from '@team-handling/teams/models/team';
import {TeamService} from '@team-handling/teams/services/team.service';
import {Router} from '@angular/router';
import {filter} from 'rxjs/operators';

@Component({
  selector: 'tha-teams-list-page',
  templateUrl: './teams-list-page.component.html',
  styleUrls: ['./teams-list-page.component.scss']
})
export class TeamsListPageComponent implements OnInit {

  teams$: Observable<Team[]> | null;

  constructor(private teamService: TeamService,
              private router: Router) {
    this.teams$ = null;
  }

  ngOnInit(): void {
    this.teams$ = this.teamService.getTeams();
  }

  redirectToTeamAdd(): void {
    this.router.navigate(['team/add']);
  }

  redirectToTeamEdit(team: Team): void {
    this.teamService.pushTeam(team);
    this.router.navigate([`team/${team.id}/edit`]);
  }

  redirectToTeamDetail(team: Team): void {
    this.router.navigate([`team/${team.id}`]);
  }

  deleteTeam(team: Team): void {
    this.teamService.deleteTeam(team.id)
      .subscribe(_ => this.teams$ = this.filterRemovedTeamFromList(team))
  }

  private filterRemovedTeamFromList(removedTeam: Team): Observable<Team[]> | null {
    return this.teams$!.pipe(
      filter(teams => !teams.includes(removedTeam))
    )
  }
}
