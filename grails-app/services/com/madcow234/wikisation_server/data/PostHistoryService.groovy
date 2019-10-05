package com.madcow234.wikisation_server.data

import grails.gorm.services.Service

@Service(PostHistory)
interface PostHistoryService {

    PostHistory get(Serializable id)

    List<PostHistory> list(Map args)

    Long count()

    void delete(Serializable id)

    PostHistory save(PostHistory postHistory)

}