package com.hang.backend.exception;

public class ListNotFoundException extends RuntimeException {

    public ListNotFoundException(Long id) {
        // 에러 메세지 생성
        super("ID에 맞는 리스트가 없습니다. ID : " + id);
    }
}
