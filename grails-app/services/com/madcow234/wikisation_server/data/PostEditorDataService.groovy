package com.madcow234.wikisation_server.data

import grails.gorm.services.Service

@Service(PostEditor)
interface PostEditorDataService {

    PostEditor get(Serializable id)

    List<PostEditor> list(Map args)

    Long count()

    void delete(Serializable id)

    PostEditor save(PostEditor postEditor)

}