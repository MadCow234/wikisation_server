package com.madcow234.wikisation_server.data

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PostEditorController {

    PostEditorService postEditorService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond postEditorService.list(params), model:[postEditorCount: postEditorService.count()]
    }

    def show(Long id) {
        respond postEditorService.get(id)
    }

    def save(PostEditor postEditor) {
        if (postEditor == null) {
            render status: NOT_FOUND
            return
        }

        try {
            postEditorService.save(postEditor)
        } catch (ValidationException e) {
            respond postEditor.errors, view:'create'
            return
        }

        respond postEditor, [status: CREATED, view:"show"]
    }

    def update(PostEditor postEditor) {
        if (postEditor == null) {
            render status: NOT_FOUND
            return
        }

        try {
            postEditorService.save(postEditor)
        } catch (ValidationException e) {
            respond postEditor.errors, view:'edit'
            return
        }

        respond postEditor, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        postEditorService.delete(id)

        render status: NO_CONTENT
    }
}
