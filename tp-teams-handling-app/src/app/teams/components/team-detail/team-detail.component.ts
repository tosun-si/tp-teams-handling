import {Component, Input} from '@angular/core';
import {Team} from '@team-handling/teams/models/team';

@Component({
  selector: 'tha-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent {

  @Input() team: Team | null;

  constructor() {
    this.team = null;
  }
}
