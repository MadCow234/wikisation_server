package com.madcow234.wikisation_server.data

import com.madcow234.wikisation_server.auth.User

class PostHistory {

    static belongsTo = [post: Post]

    String message
    Integer charactersUsed
    User editor

    Date dateCreated = new Date()

    static constraints = {
        message nullable: false
        charactersUsed nullable: false
        editor nullable: false
        dateCreated nullable: false
    }
}
