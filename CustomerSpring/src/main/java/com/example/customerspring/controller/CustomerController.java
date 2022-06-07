package com.example.customerspring.controller;

import com.example.customerspring.model.Customer;
import com.example.customerspring.service.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustomerController
 * author : Seok
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         Seok          최초 생성
 */

@CrossOrigin(origins = "http://localhost:8080")
@RestController // 통신 json 형태로 주고받고 싶을 때 사용
@RequestMapping("/api") // http://localhost:8000/api
public class CustomerController {

    // logger 찍기를 위한 객체 생성
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 스프링 객체 하나 받아오기
    @Autowired
    private CustomerServiceImpl customerService; // 객체 정의 (null => 스프링객체)

    // 모든 회원 조회 메뉴
    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomers() {

        // 모든 회원 조회 서비스 호출
        List<Customer> customers = customerService.findAll();
        try {
            // Vue 성공 메세지 + 객체를 전송
            return new ResponseEntity<Object>(customers, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue에 에러메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // 회원 생성 메뉴
    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {

        // save 리턴값 Optional<Customer> => save().get() 객체를 꺼냄
        Customer savedCustomer = customerService.save(customer).get();
        try {
            // Vue에 객체 + 성공메세지 전송
            return new ResponseEntity<Object>(savedCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            // Vue에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id로 회원 조회 메뉴
    @GetMapping("/customers/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("id") Long id) {
        // id로 회원 조회 하는 서비스를 호출 ( Optional 없애기 : get() )
        Customer customer = customerService.findById(id).get();
        try {
            if(customer != null) {
                // 성공 시 Vue에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(customer, HttpStatus.OK);
            } else {
                // 데이터가 없을 경우 Not Found 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error((ex.getMessage()), ex);
            // Vue에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}