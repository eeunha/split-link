package com.splitlink.controller;

import com.splitlink.common.annotation.AuthMember;
import com.splitlink.common.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 지출(Expense) 관련 API 요청을 처리하는 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/api/rooms/{slug}/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    /**
     * 지출 입력 폼 초기화 정보 조회
     */
    @GetMapping("/new")
    public ResponseEntity<ApiResponse<String>> getExpenseFormInit(
            @PathVariable String slug,
            @AuthMember Long memberId) {
        log.info(">>>> [JWT Auth Success] slug: {}, memberId: {}", slug, memberId);

        // 아직 서비스 구현 전이므로 임시 성공 응답 반환
        return ResponseEntity.ok(ApiResponse.success("memberId 추출 성공: " + memberId));
    }
}
