import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../admin.service';
import { UserService } from '../user.service';
import { UserModel } from '../UserModel';
import { NavigationExtras } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  //isLogin:boolean=false;

  constructor(
    private http: HttpClient,
    private adminservice: AdminService,
    private userservice: UserService,
    private router: Router
  ) {}
  //this function  invokes when i click on login button
  onSubmit() {
    console.log('*****Username and password ****');
    console.log('username ' + this.username);
    console.log('password ' + this.password);
    if (
      this.username.toLowerCase() === 'admin' &&
      this.password === 'admin123'
    ) {
      this.adminservice.loginAdmin(this.username, this.password);
      this.router.navigate(['/admin-dashboard']);
     // this.isLogin=true;
    } else {
      this.userservice.getUserByUsername(this.username).subscribe(
        (user: UserModel) => {
          if (user.username && user.password === this.password&& this.username) {
            console.log('logging  with the username ' + this.username);
            this.router.navigate(['/user-dashboard', this.username]);
           // this.isLogin=true;
          } else {
            console.error('Invalid credentials for user');
          } 
        },
        (error) => {
          console.error('Error logging in as user', error);
        }
      );
    }
  }
  //this function is for user register dashboard when user clicking on the register button
  public navigateToRegisterDashboard(): void {
    this.router.navigate(['/register-user']);
  }
}
