package com.yanado.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanado.dao.UserDAO;
import com.yanado.dto.User;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userdao;

    @Autowired
    private BCryptPasswordEncoder encoder;

//    public int Login(User user) {
//        return userdao.login(user);
//    }

    // 회원 목록 조회 시 사용
//    public List<User> selectList() {
//        return userdao.selectList();
//    }

    public void createUser(User user) {

        // 암호화
        String pw = encoder.encode(user.getPassword());
        user.setPassword(pw);
        userdao.createUser(user);
    }

    public int updateUser(User user) {
        return userdao.updateUser(user);
    }

    public int deleteUser(String userId) {
        return userdao.deleteUser(userId);
    }

//    public int updateManager(User user) {
//        return userdao.updateManager(user);
//    }

    // 아이디로 개인정보 가져오기
    public User getUserByUserId(String userId) {
        return userdao.getUserByUserId(userId);
    }

    // 아이디로 알림 / 경매(완료, 진행중) / 주문내역 / 장바구니 리스트 가져오기
//		public List<T> getListByUserId(String userId) {
//
//		}

//    public int changeRankCount(String userId) {
//        return userdao.changeRankCount(userId);
//    }
}
