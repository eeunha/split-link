package com.splitlink.controller;

import com.splitlink.common.jwt.JwtProvider;
import com.splitlink.common.resolver.AuthMemberArgumentResolver;
import com.splitlink.service.ExpenseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ExpenseController.class)
@Import(AuthMemberArgumentResolver.class)
public class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseService;

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("JWT 토큰이 헤더에 포함되면 @AuthMember를 통해 memberId를 성공적으로 추출한다")
    void getExpenseFormInit_auth_success() throws Exception {
        // given (가짜 토큰 및 토큰에서 나올 expectedMemberId 설정)
        String mockToken = "mock.jwt.token";
        Long expectedMemberId = 100L;

        // JwtProvider Mocking
        given(jwtProvider.validateToken(mockToken)).willReturn(true);
        given(jwtProvider.getMemberId(mockToken)).willReturn(expectedMemberId);

        // when & then
        mockMvc.perform(get("/api/rooms/test-slug/expenses/new")
                        .header("Authorization", "Bearer " + mockToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("memberId 추출 성공: 100"))
                .andDo(print());
    }
}