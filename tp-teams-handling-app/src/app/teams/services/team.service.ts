import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {Team} from '@team-handling/teams/models/team';
import {environment} from '@team-handling-env/environment';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private subject = new BehaviorSubject<Team>({name: '', slogan: ''});

  constructor(private http: HttpClient) {
  }

  pushTeam(team: Team) {
    this.subject.next(team);
  }

  pullTeam(): Observable<Team> {
    return this.subject.asObservable();
  }

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(`${environment.backendEnvEndPoint}/teams`);
  }

  getTeamById(teamId: number): Observable<Team> {
    const options = {params: new HttpParams().set("id", String(teamId))};

    return this.http.get<Team>(`${environment.backendEnvEndPoint}/teams/${teamId}`, options);
  }

  createTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(`${environment.backendEnvEndPoint}/teams`, team);
  }

  updateTeam(teamId: number | undefined, team: Team): Observable<Team> {
    return this.http.put<Team>(`${environment.backendEnvEndPoint}/teams/${teamId}`, team);
  }

  deleteTeam(teamId: number | undefined): Observable<void> {
    return this.http.delete<void>(`${environment.backendEnvEndPoint}/teams/${teamId}`);
  }
}
