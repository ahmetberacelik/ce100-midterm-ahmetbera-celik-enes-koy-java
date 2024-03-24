/**
 * @file User.java
 *
 * @brief This file contains the implementation of the User class.
 */
package com.bera.farmermarket;
/**
 * Represents a user with username and password information.
 */
public class User {
    private String username; /**< Username of the user. */
    private String password; /**< Password of the user. */
    /**
     * Constructs a User with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }
}
