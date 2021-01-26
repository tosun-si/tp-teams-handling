import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TeamsListPageComponent} from '@team-handling/teams/containers/teams-list-page/teams-list-page.component';
import {MatCardModule} from '@angular/material/card';
import {TeamAddPageComponent} from '@team-handling/teams/containers/team-add-page/team-add-page.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {TeamListComponent} from '@team-handling/teams/components/team-list/team-list.component';
import {TeamDetailPageComponent} from '@team-handling/teams/containers/team-detail-page/team-detail-page.component';
import {TeamDetailComponent} from '@team-handling/teams/components/team-detail/team-detail.component';
import {MatListModule} from '@angular/material/list';
import {TeamEditPageComponent} from '@team-handling/teams/containers/team-edit-page/team-edit-page.component';

@NgModule({
  declarations: [
    TeamsListPageComponent,
    TeamAddPageComponent,
    TeamListComponent,
    TeamDetailPageComponent,
    TeamDetailComponent,
    TeamEditPageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,

    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatTooltipModule,
    MatInputModule,
    MatButtonModule,
    MatListModule
  ]
})
export class TeamsModule {
}
