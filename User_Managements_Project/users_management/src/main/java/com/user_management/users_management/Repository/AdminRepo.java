package com.user_management.users_management.Repository;

import com.user_management.users_management.Model.AdminModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<AdminModel, String> {
  AdminModel findByUsername(String username);
  Optional<AdminModel> findByUsernameAndPassword(String username, String password);
}
