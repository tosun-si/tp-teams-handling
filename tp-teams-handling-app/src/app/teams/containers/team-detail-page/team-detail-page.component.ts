import {Component, OnInit} from '@angular/core';
import {TeamService} from '@team-handling/teams/services/team.service';
import {Observable} from 'rxjs';
import {Team} from '@team-handling/teams/models/team';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap} from 'rxjs/operators';

@Component({
  selector: 'tha-team-detail-page',
  templateUrl: './team-detail-page.component.html',
  styleUrls: ['./team-detail-page.component.scss']
})
export class TeamDetailPageComponent implements OnInit {
  team$: Observable<Team> | null;

  constructor(private teamService: TeamService,
              private route: ActivatedRoute,
              private router: Router) {
    this.team$ = null;
  }

  ngOnInit(): void {
    this.team$ = this.route.params.pipe(
      mergeMap(params => this.teamService.getTeamById(params['id']))
    );
  }

  redirectToTeamList(): void {
    this.router.navigate(['teams']);
  }
}
