package com.madcow234.wikisation_server

class UrlMappings {

    static mappings = {
        get "/checkUsername(.$format)?"(controller: "registration", action: "checkUsername")
        get "/checkEmail(.$format)?"(controller: "registration", action: "checkEmail")
        post "/register(.$format)?"(controller: "registration", action: "registerNewUser")

        group "/admin", {
            get "/users(.$format)?"(controller: "user", action: "index")
            put "/users/updateCharacterPool(.$format)?"(controller: "user", action: "updateCharacterPool")
            get "/roles(.$format)?"(controller: "role", action: "index")
        }

        get "/post(.$format)?"(controller: "post", action: "index")
        post "/post(.$format)?"(controller: "post", action: "save")
        put "/post/$id(.$format)?"(controller: "post", action: "update")

        get "/postHistory(.$format)?"(controller: "postHistory", action: "index")

        get "/postEditor(.$format)?"(controller: "postEditor", action: "index")

        get "/userRole(.$format)?"(controller: "userRole", action: "index")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
