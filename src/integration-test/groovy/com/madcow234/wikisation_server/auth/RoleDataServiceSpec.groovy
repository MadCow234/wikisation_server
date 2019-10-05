package com.madcow234.wikisation_server.auth

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RoleDataServiceSpec extends Specification {

    RoleDataService roleDataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Role(...).save(flush: true, failOnError: true)
        //new Role(...).save(flush: true, failOnError: true)
        //Role role = new Role(...).save(flush: true, failOnError: true)
        //new Role(...).save(flush: true, failOnError: true)
        //new Role(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //role.id
    }

    void "test get"() {
        setupData()

        expect:
        roleDataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Role> roleList = roleDataService.list(max: 2, offset: 2)

        then:
        roleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        roleDataService.count() == 5
    }

    void "test delete"() {
        Long roleId = setupData()

        expect:
        roleDataService.count() == 5

        when:
        roleDataService.delete(roleId)
        sessionFactory.currentSession.flush()

        then:
        roleDataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Role role = new Role()
        roleDataService.save(role)

        then:
        role.id != null
    }
}
