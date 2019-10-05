grails.plugin.springsecurity.interceptUrlMap = [
        [pattern: '/',               access: ['permitAll']],
        [pattern: '/checkUsername',  access: ['permitAll']],
        [pattern: '/checkEmail',     access: ['permitAll']],
        [pattern: '/register',       access: ['permitAll']],
        [pattern: '/error',          access: ['permitAll']],
        [pattern: '/index',          access: ['permitAll']],
        [pattern: '/index.gsp',      access: ['permitAll']],
        [pattern: '/shutdown',       access: ['permitAll']],
        [pattern: '/assets/**',      access: ['permitAll']],
        [pattern: '/**/js/**',       access: ['permitAll']],
        [pattern: '/**/css/**',      access: ['permitAll']],
        [pattern: '/**/images/**',   access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']],
        [pattern: '/login/**',       access: ['permitAll']],
        [pattern: '/logout/**',      access: ['permitAll']],
        [pattern: '/admin/**',       access: ['ROLE_ADMIN']],
        [pattern: '/**',             access: ['ROLE_USER', 'ROLE_ADMIN']]
]
grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/',               filters: 'none'],
        [pattern: '/assets/**',      filters: 'none'],
        [pattern: '/**/js/**',       filters: 'none'],
        [pattern: '/**/css/**',      filters: 'none'],
        [pattern: '/**/images/**',   filters: 'none'],
        [pattern: '/checkUsername',  filters: 'none'],
        [pattern: '/checkEmail',     filters: 'none'],
        [pattern: '/register',       filters: 'none'],
        [pattern: '/**',             filters: 'JOINED_FILTERS,' +
                                              '-anonymousAuthenticationFilter,' +
                                              '-exceptionTranslationFilter,' +
                                              '-authenticationProcessingFilter,' +
                                              '-securityContextPersistenceFilter,' +
                                              '-rememberMeAuthenticationFilter']
]