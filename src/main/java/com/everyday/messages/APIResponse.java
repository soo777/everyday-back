package com.everyday.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private boolean status; // 성공: true, 실패: false
    private String message; // 성공/실패 메시지
    private Object object;  // 추가 데이터
}

