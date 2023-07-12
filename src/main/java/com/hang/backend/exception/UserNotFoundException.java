package com.hang.backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        // 에러 메세지 생성
        super("ID에 맞는 유저가 없습니다. ID : " + id);
    }
}
