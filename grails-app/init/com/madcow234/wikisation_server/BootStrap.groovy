package com.madcow234.wikisation_server

import com.madcow234.wikisation_server.auth.Role

class BootStrap {

    def init = { servletContext ->
        // Create User and Admin roles
        if (!Role.findByAuthority("ROLE_USER")) new Role(authority: "ROLE_USER").save(flush: true, failOnError: true)
        if (!Role.findByAuthority("ROLE_ADMIN")) new Role(authority: "ROLE_ADMIN").save(flush: true, failOnError: true)
    }

    def destroy = {
    }
}
