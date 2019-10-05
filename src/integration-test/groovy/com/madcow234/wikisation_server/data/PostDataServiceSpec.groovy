package com.madcow234.wikisation_server.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PostDataServiceSpec extends Specification {

    PostDataService postDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Post(...).save(flush: true, failOnError: true)
        //new Post(...).save(flush: true, failOnError: true)
        //Post message = new Post(...).save(flush: true, failOnError: true)
        //new Post(...).save(flush: true, failOnError: true)
        //new Post(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //message.id
    }

    void "test get"() {
        setupData()

        expect:
        postDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Post> postList = postDataService.list(max: 2, offset: 2)

        then:
        postList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        postDataService.count() == 5
    }

    void "test delete"() {
        Long postId = setupData()

        expect:
        postDataService.count() == 5

        when:
        postDataService.delete(postId)
        sessionFactory.currentSession.flush()

        then:
        postDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Post post = new Post()
        postDataService.save(post)

        then:
        post.id != null
    }
}
