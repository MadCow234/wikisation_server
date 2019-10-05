package com.madcow234.wikisation_server

import com.madcow234.wikisation_server.auth.User
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class RegistrationController {

    RegistrationService registrationService

	static responseFormats = ['json', 'xml']

    static allowedMethods = [
            checkUsername: "GET",
            registerNewUser: "POST"
    ]

    def checkUsername(String username) {
        if (registrationService.isUsernameAvailable(username)) {
            render status: OK
        } else {
            render status: NOT_FOUND
        }
    }

    def checkEmail(String email) {
        if (registrationService.isEmailAvailable(email)) {
            render status: OK
        } else {
            render status: NOT_FOUND
        }
    }

    def registerNewUser(User user) {
        try {
            respond registrationService.registerNewUser(user), status: CREATED

        } catch (ValidationException e) {
            log.error("Could not register user! Reason: $e.errors")
            respond e.errors, status: UNPROCESSABLE_ENTITY
        }
    }
}
