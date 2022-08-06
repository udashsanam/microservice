package com.assessment.user.util;

public class ApiConstant {

    public static final String BASE_API = "/api/user";

    public static final String FIND_ALL = "/findAll";

    public static final String FIND_BY_ID = "/findById/{id}";

    public static final String DELETE_BY_ID = "/deleteById/{id}";

    public static final String ACCOUNT = BASE_API + "/account";

    public static final String ROLE = BASE_API + "/role";

    public static final String PERMISSION = BASE_API + "/permission";

    public static final String FIND_ALL_AUTHORITIES_BY_ID = "findAllAuthoritiesById/{id}";

    public static final String FIND_USER_ENTITY_WITH_ID = "findUserEntityById/{id}";
}
