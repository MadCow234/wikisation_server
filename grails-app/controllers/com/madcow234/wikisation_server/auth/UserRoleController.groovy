package com.madcow234.wikisation_server.auth

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserRoleController {

    UserRoleDataService userRoleDataService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userRoleDataService.list(params), model:[userRoleCount: userRoleDataService.count()]
    }

    def show(Long id) {
        respond userRoleDataService.get(id)
    }

    def save(UserRole userRole) {
        if (userRole == null) {
            render status: NOT_FOUND
            return
        }

        try {
            userRoleDataService.save(userRole)
        } catch (ValidationException e) {
            respond userRole.errors, view:'create'
            return
        }

        respond userRole, [status: CREATED, view:"show"]
    }

    def update(UserRole userRole) {
        if (userRole == null) {
            render status: NOT_FOUND
            return
        }

        try {
            userRoleDataService.save(userRole)
        } catch (ValidationException e) {
            respond userRole.errors, view:'edit'
            return
        }

        respond userRole, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        userRoleDataService.delete(id)

        render status: NO_CONTENT
    }
}
