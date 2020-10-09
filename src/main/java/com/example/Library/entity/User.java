package com.example.Library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname")
    @ApiModelProperty(notes = "The first name of the user")
    private String firstName;

    @Column(name = "lastname")
    @ApiModelProperty(notes = "The last name of the user")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "roleid")
    @ApiModelProperty(notes = "The role ID of the user")
    private Role roleId;

    @Email
    @Column(name = "email", unique=true)
    @ApiModelProperty(notes = "The email of the user")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(notes = "The password of the user")
    @Column(name = "password")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + roleId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
