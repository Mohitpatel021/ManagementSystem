package com.user_management.users_management.Model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_Details")
public class AdminModel {

  @Id
  private String username;

  private String password;

  @Override
  public String toString() {
    return (" username=" + username + ", Password=" + password + "]");
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
