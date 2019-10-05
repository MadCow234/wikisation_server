package com.madcow234.wikisation_server

import com.madcow234.wikisation_server.auth.Role
import com.madcow234.wikisation_server.auth.User
import com.madcow234.wikisation_server.auth.UserRole
import com.madcow234.wikisation_server.auth.UserService
import grails.validation.ValidationException
import grails.gorm.transactions.Transactional

@Transactional
class RegistrationService {

    UserService userService

    User registerNewUser(User user) throws ValidationException {
        log.info("Registering new user. Username: $user.username")

        User registeredUser = userService.save(
                new User(
                        username: user.username,
                        email: user.email,
                        password: user.password,
                        passwordExpired: false,
                        accountLocked: false,
                        accountExpired: false,
                        enabled: true,
                        characterPool: 1000,
                        lastActivityDate: new Date()
                )
        )

        // TODO create real admin/user registration logic and controls (REST/GUIs to elevate privileges)
        // Make me an admin, make everyone else a user
        String authority = (registeredUser.username == "MadCow234") ? "ROLE_ADMIN" : "ROLE_USER"

        Role role = Role.findByAuthority(authority)
        UserRole.create(registeredUser, role)

        log.info("Successfully registered new user. Username: $registeredUser.username [$role]")
        return registeredUser
    }

    Boolean isUsernameAvailable(String username) {
        return userService.countByUsername(username) == 0
    }

    Boolean isEmailAvailable(String email) {
        return userService.countByEmail(email) == 0
    }
}
