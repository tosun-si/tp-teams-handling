import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Team} from '@team-handling/teams/models/team';

@Component({
  selector: 'tha-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.scss']
})
export class TeamListComponent {

  @Input() teams: Team[] | null;
  @Output() deleteTeam = new EventEmitter<Team>();
  @Output() selectTeam = new EventEmitter<Team>();
  @Output() updateTeam = new EventEmitter<Team>();

  constructor() {
    this.teams = null;
  }

  /**
   * Event when the team is deleted in the list.
   */
  onDeleteTeam(team: Team) {
    this.deleteTeam.emit(team);
  }

  /**
   * Event when team is selected.
   */
  onSelectTeam(team: Team) {
    this.selectTeam.emit(team);
  }

  /**
   * Event when team is updated.
   */
  onUpdateTeam(team: Team) {
    this.updateTeam.emit(team);
  }
}
