import { HttpBackend, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from './admin';
import { UserModel } from './UserModel';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}
  private apiServerUrl = 'http://localhost:8080';

  //Login a admin
  public loginAdmin(username: string, password: string): Observable<Admin> {
    const body = { username, password };
    return this.http.post<Admin>(`${this.apiServerUrl}/login`, body);
  }

  
  //get All the USER
  public getAllUser(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(`${this.apiServerUrl}/admin/all`);
  }

  //delete a user
  public deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/delete/${userId}`);
  }

  //For updateing a user
  public updateUser(
    userId: number,
    updatedUserData: UserModel
  ): Observable<UserModel> {
    return this.http.put<UserModel>(
      `${this.apiServerUrl}/update/${userId}`,
      updatedUserData
    );
  }
}
