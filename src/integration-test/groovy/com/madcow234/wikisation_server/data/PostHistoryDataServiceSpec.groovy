package com.madcow234.wikisation_server.data

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PostHistoryDataServiceSpec extends Specification {

    PostHistoryDataService postHistoryDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PostHistory(...).save(flush: true, failOnError: true)
        //new PostHistory(...).save(flush: true, failOnError: true)
        //PostHistory postHistory = new PostHistory(...).save(flush: true, failOnError: true)
        //new PostHistory(...).save(flush: true, failOnError: true)
        //new PostHistory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //postHistory.id
    }

    void "test get"() {
        setupData()

        expect:
        postHistoryDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PostHistory> postHistoryList = postHistoryDataService.list(max: 2, offset: 2)

        then:
        postHistoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        postHistoryDataService.count() == 5
    }

    void "test delete"() {
        Long postHistoryId = setupData()

        expect:
        postHistoryDataService.count() == 5

        when:
        postHistoryDataService.delete(postHistoryId)
        sessionFactory.currentSession.flush()

        then:
        postHistoryDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PostHistory postHistory = new PostHistory()
        postHistoryDataService.save(postHistory)

        then:
        postHistory.id != null
    }
}
