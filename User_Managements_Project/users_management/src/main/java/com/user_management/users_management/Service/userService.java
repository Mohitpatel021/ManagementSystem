package com.user_management.users_management.Service;

import com.user_management.users_management.Model.UserModel;
import com.user_management.users_management.Repository.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class userService {

  private final UserRepo userrepo;

  userService(UserRepo userrepo) {
    this.userrepo = userrepo;
  }

  //this is function is for user deletion
  public void deleteUser(Long id) {
    userrepo.deleteById(id);
  }

  public UserModel getUserById(Long id) {
    return userrepo
      .findById(id)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  //this is for find user by name
  public UserModel getUserByname(String name) {
    return userrepo.findByName(name);
  }

  //this function return a user by finding user by their username
  public UserModel getUserByUsername(String Username) {
    return userrepo.findByUsername(Username);
  }

  //for registering a user
  public UserModel registerUser(UserModel usermmodel) {
    return userrepo.save(usermmodel);
  }

  //for login a user
  public boolean authenticate_User(String password, String username) {
    UserModel usermodel = userrepo.findByUsername(username);
    return usermodel != null && usermodel.getPassword().equals(password);
  }

  //this is for updating a user by admin
  public UserModel updateUser(Long id, UserModel updatedUsermodel) {
    Optional<UserModel> optionalUser = userrepo.findById(id);
    UserModel existingUser = optionalUser.orElse(null);
    if (existingUser != null) {
      existingUser.setName(updatedUsermodel.getName());
      existingUser.setPhone(updatedUsermodel.getPhone());
      existingUser.setEmail(updatedUsermodel.getEmail());
      existingUser.setCity(updatedUsermodel.getCity());
      return userrepo.save(existingUser);
    } else {
      return null;
    }
  }

  public Optional<UserModel> getUserDetailsByUsernameAndPassword(
    String username,
    String password
  ) {
    Optional<UserModel> userOptional = userrepo.getUserDetailsByUsernameAndPassword(
      username,
      password
    );
    return userOptional;
  }

  //user by id
  public UserModel findById(Long id) {
    return userrepo.findById(id).orElse(null);
  }

  public List<UserModel> getAllUsers() {
    return userrepo.findAll();
  }
}
