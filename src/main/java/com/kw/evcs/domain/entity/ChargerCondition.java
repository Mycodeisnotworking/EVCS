package com.kw.evcs.domain.entity;

public enum ChargerCondition {
    ERROR, // 1: 통신이상
    WAITING, // 2: 충전대기
    USING, // 3: 충전중
    STOP, // 4: 운영중지
    MAINTENANCE, // 5: 점검중
    UNCHECKED; // 6: 상태미확인

    public static ChargerCondition getCondition(int statusCode) {
        switch (statusCode) {
            case 1:
                return ERROR;
            case 2:
                return WAITING;
            case 3:
                return USING;
            case 4:
                return STOP;
            case 5:
                return MAINTENANCE;
            case 6:
            default:
                return UNCHECKED;
        }
    }
}
