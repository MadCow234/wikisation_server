package com.madcow234.wikisation_server.data

import grails.gorm.services.Service

@Service(Post)
interface PostService {

    Post get(Serializable id)

    List<Post> list(Map args)

    Long count()

    void delete(Serializable id)

    Post save(Post post)
}