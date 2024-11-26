package com.ohgiraffers.sessionsecurity.common;

import lombok.Getter;

// 값을 꺼내오기 위해서 Getter 만 추가
@Getter
public enum UserRole {

    /* comment.
    *   enum 이란?
    *   열거형 상수들의 집합을 의미한다.
    *   주로 사용되는 예시로는 고정되어있는 값들을 처리하기 위해 사용되며
    *   [ex] 시스템 권한이 단 2개 = 일반 사용자, 관리자 */

    // 상수 필드
    // ("") 는 상수에 value
    USER("USER"), ADMIN("ADMIN");

    private String role;

    UserRole(String role){
        this.role = role;
    }
}
