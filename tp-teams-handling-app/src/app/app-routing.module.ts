import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TeamsListPageComponent} from '@team-handling/teams/containers/teams-list-page/teams-list-page.component';
import {TeamAddPageComponent} from '@team-handling/teams/containers/team-add-page/team-add-page.component';
import {TeamDetailPageComponent} from '@team-handling/teams/containers/team-detail-page/team-detail-page.component';
import {TeamEditPageComponent} from '@team-handling/teams/containers/team-edit-page/team-edit-page.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'teams',
    pathMatch: 'full'
  },
  {
    path: 'team/add',
    component: TeamAddPageComponent
  },
  {
    path: 'team/:{id}/edit',
    component: TeamEditPageComponent
  },
  {
    path: 'teams',
    component: TeamsListPageComponent
  },
  {
    path: 'team/:id',
    component: TeamDetailPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
