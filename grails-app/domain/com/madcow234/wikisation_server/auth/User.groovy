package com.madcow234.wikisation_server.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String email
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    // Use getAvailableCharacters() for a real-time character pool count
    int characterPool

    Date lastActivityDate

    static constraints = {
        username nullable: false, blank: false, unique: true
        email nullable: false, blank: false, email: true
        password nullable: false, blank: false, password: true
    }

    static mapping = {
	    password column: '`password`'
    }

    /**
     * Retrieves the user's roles.
     *
     * @return the user's roles
     */
    Set<Role> getAuthorities() {
        return (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    /**
     * Calculates the user's real-time character pool count based on their characterPool and lastActivityDate.
     *
     * @return the real-time character pool count available to the user
     */
    int getAvailableCharacters() {
        // TODO create a real newCharacterDelta model
        // Adds a new character for every second that has passed since the user's last activity date
        int newCharacterDelta = Math.floor((new Date().getTime() - lastActivityDate.getTime()) / 1000) as int
        return characterPool + newCharacterDelta
    }
}
