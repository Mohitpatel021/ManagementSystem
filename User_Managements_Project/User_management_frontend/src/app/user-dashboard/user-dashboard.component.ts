import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AdminService } from '../admin.service';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserModel } from '../UserModel';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css'],
})
export class UserDashboardComponent {
  user: any | undefined;
  username: string = '';
  userForm: FormGroup | any;
  userId: number | any;
  showModal: boolean = false;
  // isLogin:boolean=true;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private http: HttpClient,
    private adminservice: AdminService,
    private userservice: UserService,
    private formBuilder: FormBuilder
  ) {
    this.userForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      city: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    // Get the username from the route parameters
    this.route.params.subscribe((params) => {
      this.username = params['username'];
      console.log('getting user name ' + this.username);
      // Fetch user data based on the username
      this.userservice.getUserByUsername(this.username).subscribe(
        (userData: any) => {
          this.user = userData;
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
    });
  }

  updateDetails(userId: number): void {
    if (this.userForm.valid) {
      const updatedUser: UserModel = this.userForm.value;
      this.userservice.updateUser(userId, updatedUser).subscribe(
        (Response) => {
          alert('User Updated successfully: ' + Response);
          this.userForm.reset();
          window.location.reload();
          this.showModal = false;
        },
        (error) => {
          alert('error updating user!!Thankyou' + error);
        }
      );
    }
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      const updatedUser: UserModel = this.userForm.value;
      this.userservice.updateUser(this.userId, updatedUser).subscribe(
        (Response) => {
          alert('updated successfully ' + Response);
          window.location.reload();
        },
        (error) => {
          alert('User Not found!!');
        }
      );
    }
  }
  openEditModal(): void {
    this.showModal = true;
  }

  closeEditModal(): void {
    this.showModal = false;
  }
  logout(): void {
    this.router.navigate(['/login']);
    // this.isLogin=false;
  }
}
