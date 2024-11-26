package com.ohgiraffers.sessionsecurity.user.model.dto;

import com.ohgiraffers.sessionsecurity.common.UserRole;
import lombok.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginUserDTO {

    /* comment.
    *   로그인 시 사용 할 DTO 클래스는
    *   화면상에서 입력 받는 ID, PASSWORD 뿐만 아니라
    *   로그인에 성공 했을 시 필요한 정보들을 추가적으로 담는다. */
    private int userCode;
    private String userId;
    private String userName;
    private String password;
    // 변하지 않는 값(admin, user)을 묶어서 관리하기 위해 UserRole 을 enum 만듦
    private UserRole userRole;

    // 다중 권한이 아닐 때는 없어도 되는 메소드 (일반회원만 있을 경우)
    public List<String> getRole() {

        if(this.userRole.getRole().length() > 0){
            // 회원의 권한이 여러 개 ex) 일반회원, 관리자 일 시 두 권한을 담기 위한 리스트
            return Arrays.asList(this.userRole.getRole().split(","));
        }
        
        return new ArrayList<>();
    }
}
