package com.user_management.users_management.Service;

import com.user_management.users_management.Model.AdminModel;
import com.user_management.users_management.Model.UserModel;
import com.user_management.users_management.Repository.AdminRepo;
import com.user_management.users_management.Repository.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  private final AdminRepo adminrepo;
  private final UserRepo userrepo;

  AdminService(UserRepo userrepo, AdminRepo adminrepo) {
    this.userrepo = userrepo;
    this.adminrepo = adminrepo;
  }

  //get all the user
  public List<UserModel> findAllUser() {
    return this.userrepo.findAll();
  }

  //delete a user
  public void deleteUser(Long Id) {
    userrepo.deleteById(Id);
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

  public Optional<AdminModel> getAdminDetailsByUsernameAndPassword(
    String username,
    String password
  ) {
    return adminrepo.findByUsernameAndPassword(username, password);
  }
}
