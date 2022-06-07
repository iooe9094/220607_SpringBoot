package com.example.customerspring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.customerspring.model
 * fileName : Customer
 * author : Seok
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         Seok          최초 생성
 */

@Getter
@Setter
@ToString
public class Customer {
    // Long(객체), long(일반자료형) => 8byte 정수형
    private Long id; // 회원 아이디
    private String firstName; // 회원 이름
    private String lastName; // 회원 성
    private String email; // email
    private String phone; // 휴대폰
    private String deleteYn; // 삭제 여부
    private String insertTime; // 생성날짜
    private String updateTime; // 수정날짜
    private String deleteTime; // 삭제날짜

}