package com.madcow234.wikisation_server.auth

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserRoleDataServiceSpec extends Specification {

    UserRoleDataService userRoleDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserRole(...).save(flush: true, failOnError: true)
        //new UserRole(...).save(flush: true, failOnError: true)
        //UserRole userRole = new UserRole(...).save(flush: true, failOnError: true)
        //new UserRole(...).save(flush: true, failOnError: true)
        //new UserRole(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userRole.id
    }

    void "test get"() {
        setupData()

        expect:
        userRoleDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserRole> userRoleList = userRoleDataService.list(max: 2, offset: 2)

        then:
        userRoleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userRoleDataService.count() == 5
    }

    void "test delete"() {
        Long userRoleId = setupData()

        expect:
        userRoleDataService.count() == 5

        when:
        userRoleDataService.delete(userRoleId)
        sessionFactory.currentSession.flush()

        then:
        userRoleDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserRole userRole = new UserRole()
        userRoleDataService.save(userRole)

        then:
        userRole.id != null
    }
}
