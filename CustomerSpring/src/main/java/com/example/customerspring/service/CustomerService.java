package com.example.customerspring.service;

import com.example.customerspring.model.Customer;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerService
 * author : Seok
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         Seok          최초 생성
 */

public interface CustomerService {
    // id로 조회하는 메소드
    Optional<Customer> findById(long id);

    // 모든 회원 조회하는 메소드 (결과: 여러건)
    List<Customer> findAll();

    // 회원 저장, 수정하는 메소드 (insert/update)
    Optional<Customer> save(Customer customer);

    // id로 회원 삭제하는 메소드
    boolean deleteById(Long id);

    // 모든 회원 삭제하는 메소드
    boolean deleteAll();
}