package com.madcow234.wikisation_server.auth

import grails.gorm.services.Service

@Service(User)
interface UserDataService {
    Integer countByUsername(String username)

    Integer countByEmail(String email)

    User get(Serializable id)

    User find(String username)

    User findByEmail(String email)

    List<User> list(Map args)

    Long count()

    void delete(Serializable id)

    User save(User user)

    User updateCharacterPool(Serializable id, int characterPool)

    User updateLastActivityDate(Serializable id, Date lastActivityDate)
}