package com.madcow234.wikisation_server.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PostController {

    PostDataService postDataService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond postDataService.list(params), model:[postCount: postDataService.count()]
    }

    def show(Long id) {
        respond postDataService.get(id)
    }

    def save(Post post) {
        if (post == null) {
            render status: NOT_FOUND
            return
        }

        try {
            postDataService.save(post)
        } catch (ValidationException e) {
            respond post.errors, view:'create'
            return
        }

        respond post, [status: CREATED, view:"show"]
    }

    def update(Post post) {
        if (post == null) {
            render status: NOT_FOUND
            return
        }

        try {
            postDataService.save(post)
        } catch (ValidationException e) {
            respond post.errors, view:'edit'
            return
        }

        respond post, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        postDataService.delete(id)

        render status: NO_CONTENT
    }
}
