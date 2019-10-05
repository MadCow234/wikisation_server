package com.madcow234.wikisation_server.auth

import grails.gorm.services.Service

@Service(Role)
interface RoleDataService {

    Role get(Serializable id)

    List<Role> list(Map args)

    Long count()

    void delete(Serializable id)

    Role save(Role role)

}