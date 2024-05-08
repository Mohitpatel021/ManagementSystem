package com.user_management.users_management.Controller;

import com.user_management.users_management.Model.AdminModel;
import com.user_management.users_management.Model.UserModel;
import com.user_management.users_management.Service.AdminService;
import com.user_management.users_management.Service.userService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private userService userservice;
  private AdminService adminservice;

  UserController(userService userservice, AdminService adminservice) {
    this.userservice = userservice;
    this.adminservice = adminservice;
  }

  @PostMapping("/register")
  public ResponseEntity<UserModel> registerUser(
    @RequestBody UserModel usermodel
  ) {
    UserModel registeredUser = userservice.registerUser(usermodel);
    return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(
    @RequestBody Map<String, String> credentials
  ) {
    String username = credentials.get("username");
    String password = credentials.get("password");

    // Authenticate user or admin based on the role
    Optional<UserModel> userOptional = userservice.getUserDetailsByUsernameAndPassword(
      username,
      password
    );
    Optional<AdminModel> adminOptional = adminservice.getAdminDetailsByUsernameAndPassword(
      username,
      password
    );

    if (userOptional.isPresent()) {
      UserModel user = userOptional.get();
      return ResponseEntity.ok(user);
    } else if (adminOptional.isPresent()) {
      AdminModel admin = adminOptional.get();
      List<UserModel> allUsers = userservice.getAllUsers();
      return ResponseEntity.ok(allUsers);
    } else {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body("Invalid credentials");
    }
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<UserModel> UserDetails(
    @PathVariable("username") String username
  ) {
    System.out.println("Finding a user with the username " + username);
    UserModel user = userservice.getUserByUsername(username);
    if (user != null) {
      System.out.println("users data : " + user.getName());
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/user/profile/{id}")
  public ResponseEntity<UserModel> getUserById(@PathVariable("id") Long id) {
    UserModel user = userservice.findById(id);

    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("user/update/{id}")
  public ResponseEntity<UserModel> updateUser(
    @PathVariable("id") Long id,
    @RequestBody UserModel usermodel
  ) {
    UserModel updatedData = userservice.updateUser(id, usermodel);
    if (updatedData != null) {
      return new ResponseEntity<>(updatedData, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
