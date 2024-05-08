package com.user_management.users_management.Controller;

import com.user_management.users_management.Model.UserModel;
import com.user_management.users_management.Service.AdminService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

 
  private final AdminService adminservice;

  public AdminController( AdminService adminservice) {
    this.adminservice = adminservice;
  }
  //This is for  getting all the user
  @GetMapping("/all")
  public ResponseEntity<List<UserModel>> getAllEmployees() {
    List<UserModel> users = adminservice.findAllUser();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  //This is for deleting an existing User
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
    adminservice.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // for updating the user by the user ID
  @PutMapping("/update/{id}")
  public ResponseEntity<UserModel> updateUser(
    @PathVariable("id") Long id,
    @RequestBody UserModel usermodel
  ) {
    UserModel updatedData = adminservice.updateUser(id, usermodel);
    if (updatedData != null) {
      return new ResponseEntity<>(updatedData, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
