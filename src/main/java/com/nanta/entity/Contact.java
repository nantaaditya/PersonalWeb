package com.nanta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "N_CONTACT")
public class Contact extends BaseEntity {
  private String name;
  private String email;
  @Column(columnDefinition = "TEXT")
  private String message;
  private String status;

  public Contact() {}

  private Contact(String name, String email, String message) {
    this.name = name;
    this.email = email;
    this.message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
