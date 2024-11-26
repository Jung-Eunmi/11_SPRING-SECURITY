package com.ohgiraffers.sessionsecurity.user.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SignupDTO {

    // signup.html 의 내부 name 과 같게 필드명 작성
    private String userId;
    private String userName;
    private String userPass;
    private String role;
}
