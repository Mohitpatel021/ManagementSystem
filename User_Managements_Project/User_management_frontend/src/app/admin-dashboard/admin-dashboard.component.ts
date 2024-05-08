import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from '../UserModel';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent implements OnInit {
  users: any[] | undefined;
  user: any;
  totalUser: number = 0;

  constructor(
    private adminservice: AdminService,
    private userservice: UserService,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit() {
    this.fetchUserList(); // Call fetchUserList when the component initializes
  }

  private fetchUserList(): void {
    this.adminservice.getAllUser().subscribe(
      (userList: any[]) => {
        this.users = userList;
        this.totalUser = this.users.length;
      },
      (error) => {
        console.error('Error fetching user list:', error);
      }
    );
  }

  deleteUser(userId: number): void {
    if (confirm('Are you sure to delete this user?')) {
      this.adminservice.deleteUser(userId).subscribe(
        () => {
          this.fetchUserList();
        },
        (error) => {
          console.log('Error while deleting the User');
        }
      );
    }
  }
  editUser(userId: number): any {
   
  }

  logout(): void {
    this.router.navigate(['/login']);
  }
}
