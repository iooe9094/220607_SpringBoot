package com.example.customerspring.service;

import com.example.customerspring.dao.CustomerDao;
import com.example.customerspring.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : Seok
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         Seok          최초 생성
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    // 스프링에 customerDao 이미 생성되어 있음
    // 생성된 객체를 받아오는 어노테이션
    @Autowired
    private CustomerDao customerDao;

    // 나중에 에러 로그 추적을 위해 생성
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // id로 회원 조회 하는 메소드
    @Override
    public Optional<Customer> findById(long id) {
        return customerDao.findById(id);
    }

    // 모든 회원 조회 서비스
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    // 회원 생성 / 수정 서비스 (insert/update)
    @Override
    public Optional<Customer> save(Customer customer) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqId = 0;

        // 매개변수 cutomer 정보 출력
        logger.info("customer {}", customer);

        // customer.getId() 없으면 insert문(새로운 회원 생성),
        //                  있으면 update문 호출
        if(customer.getId() == null) {
            customerDao.insertCustomer(customer);
        } else {
            customerDao.updateCustomer(customer);
        }

        // insert문 후에는 customer의 id속성에 값이 저장(<selectkey>)
        seqId = customer.getId();
        logger.info("seqId {}", seqId);

        return customerDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = customerDao.deleteCustomer(id);

        return (queryResult >= 1 ) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = customerDao.deleteAll();

        return (queryResult >= 1) ? true : false;
    }
}