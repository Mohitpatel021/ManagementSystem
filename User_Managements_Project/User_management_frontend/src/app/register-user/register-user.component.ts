import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../user.service';
import { AdminService } from '../admin.service';
import { Router, UrlSegment } from '@angular/router';
import { UserModel } from '../UserModel';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css'],
})
export class RegisterUserComponent {
  users: UserModel = {
    id: 0,
    username: '',
    name: '',
    email: '',
    phone: 0,
    password: '',
    city: '',
  };

  constructor(
    private router: Router,
    private http: HttpClient,
    private userservice: UserService,
    private adminservice: AdminService
  ) {}

  onSubmit() {
    console.log('new user ' + this.users);
    this.userservice.registerUser(this.users).subscribe(
      (user) => {
        alert('register Successful');
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Error registering user:', error);
      }
    );
  }
}
