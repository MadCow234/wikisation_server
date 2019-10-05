package com.madcow234.wikisation_server.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PostEditorServiceSpec extends Specification {

    PostEditorService postEditorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PostEditor(...).save(flush: true, failOnError: true)
        //new PostEditor(...).save(flush: true, failOnError: true)
        //PostEditor postEditor = new PostEditor(...).save(flush: true, failOnError: true)
        //new PostEditor(...).save(flush: true, failOnError: true)
        //new PostEditor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //postEditor.id
    }

    void "test get"() {
        setupData()

        expect:
        postEditorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PostEditor> postEditorList = postEditorService.list(max: 2, offset: 2)

        then:
        postEditorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        postEditorService.count() == 5
    }

    void "test delete"() {
        Long postEditorId = setupData()

        expect:
        postEditorService.count() == 5

        when:
        postEditorService.delete(postEditorId)
        sessionFactory.currentSession.flush()

        then:
        postEditorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PostEditor postEditor = new PostEditor()
        postEditorService.save(postEditor)

        then:
        postEditor.id != null
    }
}
