import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Team} from '@team-handling/teams/models/team';
import {Router} from '@angular/router';
import {TeamService} from '@team-handling/teams/services/team.service';

@Component({
  selector: 'tha-team-add-page',
  templateUrl: './team-add-page.component.html',
  styleUrls: ['./team-add-page.component.scss']
})
export class TeamAddPageComponent implements OnInit {

  addTeamForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private teamService: TeamService) {
  }

  ngOnInit(): void {
    this.createForm();
  }

  cancel() {
    this.router.navigate(['teams']);
  }

  createTeam() {
    if (this.addTeamForm.invalid) {
      return
    }

    const team: Team = {
      name: this.addTeamForm.value.name,
      slogan: this.addTeamForm.value.slogan
    };

    this.teamService
      .createTeam(team)
      .subscribe(_ => this.router.navigate(['teams']));
  }

  private createForm(): void {
    this.addTeamForm = this.fb.group({
      name: ['', Validators.required],
      slogan: ['', Validators.required]
    });
  }
}
