package com.madcow234.wikisation_server.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PostHistoryController {

    PostHistoryService postHistoryService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond postHistoryService.list(params), model:[postHistoryCount: postHistoryService.count()]
    }

    def show(Long id) {
        respond postHistoryService.get(id)
    }

    def save(PostHistory postHistory) {
        if (postHistory == null) {
            render status: NOT_FOUND
            return
        }

        try {
            postHistoryService.save(postHistory)
        } catch (ValidationException e) {
            respond postHistory.errors, view:'create'
            return
        }

        respond postHistory, [status: CREATED, view:"show"]
    }

    def update(PostHistory postHistory) {
        if (postHistory == null) {
            render status: NOT_FOUND
            return
        }

        try {
            postHistoryService.save(postHistory)
        } catch (ValidationException e) {
            respond postHistory.errors, view:'edit'
            return
        }

        respond postHistory, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        postHistoryService.delete(id)

        render status: NO_CONTENT
    }
}
