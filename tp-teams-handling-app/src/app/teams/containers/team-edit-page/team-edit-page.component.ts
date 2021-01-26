import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {TeamService} from '@team-handling/teams/services/team.service';
import {Team} from '@team-handling/teams/models/team';

@Component({
  selector: 'tha-team-edit-page',
  templateUrl: './team-edit-page.component.html',
  styleUrls: ['./team-edit-page.component.scss']
})
export class TeamEditPageComponent implements OnInit, OnDestroy {

  // paramsSubscription$: Subscription;
  currentTeam: Team;

  currentTeamId: number;
  editTeamForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private teamService: TeamService) {
  }

  ngOnDestroy(): void {
    // this.paramsSubscription$.unsubscribe();
  }

  ngOnInit(): void {
    this.teamService.pullTeam()
      .subscribe(team => this.initElements(team))

    //this.route.params
    //   .subscribe(params => this.currentTeamId = params['id'])
  }

  cancel() {
    this.router.navigate(['teams']);
  }

  updateTeam() {
    if (this.editTeamForm.invalid) {
      return
    }

    const team: Team = {
      name: this.editTeamForm.value.name,
      slogan: this.editTeamForm.value.slogan
    };

    this.teamService
      .updateTeam(this.currentTeam.id, team)
      .subscribe(_ => this.router.navigate(['teams']));
  }

  private initElements(currentTeam: Team) {
    this.currentTeam = currentTeam;

    this.editTeamForm = this.fb.group({
      name: [currentTeam.name, Validators.required],
      slogan: [currentTeam.slogan, Validators.required]
    });
  }
}
