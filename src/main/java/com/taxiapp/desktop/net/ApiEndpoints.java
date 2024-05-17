package com.taxiapp.desktop.net;

public final class ApiEndpoints {
    private ApiEndpoints(){}

    public static final String ROOT_URL = "http://localhost:8080/api/v1";

    public static final String AUTH_URL = ROOT_URL + "/auth/authenticate";
    public static final String REGISTER_USER_URL = ROOT_URL +"/admin/register";

    public static final String ORDER_URL = ROOT_URL + "/order";
    public static final String ALL_ORDERS_URL = ORDER_URL + "/all";
    public static final String NEW_ORDER_URL = ORDER_URL + "/new";

    public static final String DRIVER_URL = ROOT_URL + "/driver";
    public static final String ALL_DRIVERS_URL = DRIVER_URL + "/all";
    public static final String DRIVER_FIN_STAT_URL = DRIVER_URL + "/financial-statement";

    public static final String USER_URL = ROOT_URL + "/user";
    public static final String ALL_USERS_URL = USER_URL + "/all";

    public static final String STATISTICS_RATIO_URL = ROOT_URL + "/statistics/ratio";
    public static final String STATISTICS_PERIOD_URL = ROOT_URL + "/statistics/period";

    public static final String GET_PRICING_POLICY = ROOT_URL + "/pricing/get";
    public static final String UPDATE_PRICING_POLICY = ROOT_URL + "/pricing/update";

    public static final String NEW_MESSAGE_URL = ROOT_URL + "/message/new";
    public static final String ALL_MESSAGES_URL = ROOT_URL + "/message/all";

    public static final String NEW_CAR_URL = ROOT_URL + "/car/new";
    public static final String ALL_CARS_URL = ROOT_URL + "/car/all";
}
