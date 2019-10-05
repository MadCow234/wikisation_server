package com.madcow234.wikisation_server.auth

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserDataService userDataService
    SpringSecurityService springSecurityService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userDataService.list(params), model:[userCount: userDataService.count()]
    }

    def show(Long id) {
        respond userDataService.get(id)
    }

    def save(User user) {
        if (user == null) {
            render status: NOT_FOUND
            return
        }

        try {
            userDataService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        respond user, [status: CREATED, view:"show"]
    }

    def update(User user) {
        if (user == null) {
            render status: NOT_FOUND
            return
        }

        try {
            userDataService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        respond user, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        userDataService.delete(id)

        render status: NO_CONTENT
    }

    def updateCharacterPool() {
        User user = userDataService.find((springSecurityService.principal as GrailsUser).username)
        userDataService.updateCharacterPool(user.id, user.availableCharacters)
        userDataService.updateLastActivityDate(user.id, new Date())

        respond user, [status: OK, view:"show"]
    }
}
