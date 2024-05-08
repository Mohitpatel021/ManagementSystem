import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from './UserModel';
@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}
  private apiServerUrl = 'http://localhost:8080';

  //This function is for register a user
  public registerUser(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(`${this.apiServerUrl}/register`, user);
  }

  //this will login a user
  public loginUser(username: string, password: string): Observable<UserModel> {
    const body = { username, password };
    return this.http.post<UserModel>(`${this.apiServerUrl}/login`, body);
  }

  //this will return a user by username
  public getUserByUsername(username: string): Observable<UserModel> {
    console.log('****calling a user mehtod in userservice*******');
    return this.http.get<UserModel>(
      `${this.apiServerUrl}/user/${username}`
    );
    console.log(username);
  }
//get the user by userId
  public getUserById(userId:number):Observable<UserModel>{
    return this.http.get<UserModel>(`${this.apiServerUrl}/user${userId}`);
  }
  //update a user
  public updateUser(
    userId: number,
    updatedUser: UserModel
  ): Observable<UserModel> {
    return this.http.put<UserModel>(
      `${this.apiServerUrl}/user/update/${userId}`,
      updatedUser
    );
  }
}
