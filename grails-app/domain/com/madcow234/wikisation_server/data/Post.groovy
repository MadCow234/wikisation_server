package com.madcow234.wikisation_server.data

import com.madcow234.wikisation_server.auth.User
import com.madcow234.wikisation_server.auth.UserService
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.userdetails.GrailsUser

class Post {

    transient SpringSecurityService springSecurityService
    transient UserService userService

    static hasMany = [postHistory: PostHistory]

    String message
    Integer charactersUsed
    User lastEditor

    Date dateCreated = new Date()
    Date lastUpdated = new Date()

    Set<User> getEditors() {
        (PostEditor.findAllByPost(this) as List<PostEditor>)*.editor as Set<User>
    }

    static constraints = {
        message nullable: true, blank: true
        charactersUsed nullable: true
        lastEditor nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
    }

    def beforeInsert() {
        // TODO if user has enough characters

        // Save the number of characters used in the Post
        charactersUsed = message.length()

        // Retrieve the User that submitted the Post
        User user = userService.find((springSecurityService.principal as GrailsUser).username)

        // Remove characters from User's character pool
        userService.updateCharacterPool(user.id, user.availableCharacters - charactersUsed)
        userService.updateLastActivityDate(user.id, new Date())

        // Set the User as the last editor of the Post
        lastEditor = user
    }

    def afterInsert() {
        // Add the last editor to the list of editors for this Post
        PostEditor.create(this, this.lastEditor)
    }

    def beforeUpdate() {
        // TODO if user has enough characters

        // Get the User that edited this Post
        User currentUser = userService.find((springSecurityService.principal as GrailsUser).username)

        // Retrieve the previous information of the Post
        Post originalPost = this
        String originalMessage = this.getPersistentValue("message") as String
        Integer originalCharactersUsed = this.getPersistentValue("charactersUsed") as Integer
        User originalLastEditor = this.getPersistentValue("lastEditor") as User

        // Create a new PostHistory using the previous Post information and current User
        PostHistory.withNewSession {
            new PostHistory(
                    post: originalPost,
                    message: originalMessage,
                    charactersUsed: originalCharactersUsed,
                    editor: originalLastEditor
            ).save()
        }

        // TODO Calculate character diff on the two messages instead of just message length diff
        // Calculate and update how many characters were used when editing the Post
        Integer characterDelta = Math.abs(originalMessage.length() - message.length())
        charactersUsed = characterDelta

        // Calculate the User's new character pool after editing this Post
        Integer newUserCharPool = currentUser.availableCharacters - characterDelta

        // Update the character pool of the User that edited the Post
        User.withNewSession {
            userService.updateCharacterPool(currentUser.id, newUserCharPool)
            userService.updateLastActivityDate(currentUser.id, new Date())
        }

        // Set this User as the last editor of the Post
        lastEditor = currentUser

        PostEditor.withNewSession {
            // Add the last editor to the list of editors for this Post
            PostEditor.create(originalPost, currentUser, true)
        }
    }
}
