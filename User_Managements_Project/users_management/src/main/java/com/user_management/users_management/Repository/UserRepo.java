package com.user_management.users_management.Repository;


import com.user_management.users_management.Model.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
  public UserModel findByName(String name);
  public Optional<UserModel>findById(Long id);
  public UserModel findByUsername(String username);
  public Optional<UserModel> getUserDetailsByUsernameAndPassword(String username,String password);
}
