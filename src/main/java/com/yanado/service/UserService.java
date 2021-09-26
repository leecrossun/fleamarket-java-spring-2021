package com.yanado.service;

import java.util.List;
import java.util.Random;

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

    public void createUser(User user) {

        // 암호화
        String pw = encoder.encode(user.getPassword());
        user.setPassword(pw);

        // 인증 키 생성
        String key = certifiedKey();
        user.setCertified(key);

        userdao.createUser(user);
    }

    public int updateUser(User user) {

        // 암호화
        String pw = encoder.encode(user.getPassword());
        user.setPassword(pw);

        // 인증 키 생성
        String key = certifiedKey();
        user.setCertified(key);

        return userdao.updateUser(user);
    }

    public int deleteUser(String userId) {
        return userdao.deleteUser(userId);
    }

    // 아이디로 개인정보 가져오기
    public User getUserByUserId(String userId) {
        return userdao.getUserByUserId(userId);
    }

    public User getUserByUserName(String name){
        return userdao.getUserByUserName(name);
    }

    // 이메일 인증 키 생성
    private String certifiedKey() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int num = 0;

        do {
            num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }

        } while (sb.length() < 10);
        return sb.toString();
    }

    // 인증 키 체크
    public User emailCheck(String name, String mailKey) {
        User user = userdao.getUserByUserName(name);
        String dbKey = user.getCertified();

        if (mailKey.equals(dbKey)){
            return user;
        } else {
            return null;
        }
    }

    // 인증 업데이트
    public void emailUpdate(User user) {
        user.setCertified("Y");
        userdao.updateUser(user);
    }
}
