package com.madcow234.wikisation_server.data

import com.madcow234.wikisation_server.auth.User
import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.codehaus.groovy.util.HashCodeHelper

@ToString(cache=true, includeNames=true, includePackage=false)
class PostEditor implements Serializable {

    private static final long serialVersionUID = 1

    Post post
    User editor

    @Override
    boolean equals(other) {
        if (other instanceof PostEditor) {
            other.postId == post?.id && other.editorId == editor?.id
        }
    }

    @Override
    int hashCode() {
        int hashCode = HashCodeHelper.initHash()
        if (post) {
            hashCode = HashCodeHelper.updateHash(hashCode, post.id)
        }
        if (editor) {
            hashCode = HashCodeHelper.updateHash(hashCode, editor.id)
        }
        hashCode
    }

    static PostEditor get(long postId, long editorId) {
        criteriaFor(postId, editorId).get()
    }

    static boolean exists(long postId, long editorId) {
        criteriaFor(postId, editorId).count()
    }

    private static DetachedCriteria criteriaFor(long postId, long editorId) {
        PostEditor.where {
            post == Post.load(postId) &&
            editor == User.load(editorId)
        }
    }

    static PostEditor create(Post post, User editor, boolean flush = false) {
        def instance = new PostEditor(post: post, editor: editor)
        instance.save(flush: flush)
        instance
    }

    static boolean remove(Post p, User e) {
        if (p != null && e != null) {
            PostEditor.where { post == p && editor == e }.deleteAll()
        }
    }

    static int removeAll(Post p) {
        p == null ? 0 : PostEditor.where { post == p }.deleteAll() as int
    }

    static int removeAll(User e) {
        e == null ? 0 : PostEditor.where { editor == e }.deleteAll() as int
    }

    static constraints = {
        post nullable: false
        editor nullable: false, validator: { User editor, PostEditor postEditor ->
            if (postEditor.post?.id) {
                if (PostEditor.exists(postEditor.post.id, editor.id)) {
                    return ['postEditor.exists']
                }
            }
        }
    }

    static mapping = {
        id composite: ['post', 'editor']
        version false
    }
}
