package com.example.tastyhub.common.domain.userReview.controller;

import static com.example.tastyhub.common.config.APIConfig.USERREVIEW_API;
import static com.example.tastyhub.common.utils.HttpResponseEntity.RESPONSE_OK;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tastyhub.common.domain.recipeReview.dtos.PagingRecipeReviewResponse;
import com.example.tastyhub.common.domain.userReview.dtos.PagingUserReviewResponse;
import com.example.tastyhub.common.domain.userReview.dtos.UserReviewCreateRequest;
import com.example.tastyhub.common.domain.userReview.service.UserReviewService;
import com.example.tastyhub.common.dto.StatusResponse;
import com.example.tastyhub.common.utils.Jwt.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(USERREVIEW_API)
@RequiredArgsConstructor
public class UserReviewController {
    private final UserReviewService userReviewService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<StatusResponse> createUserReview(@PathVariable Long userId, 
        @RequestBody UserReviewCreateRequest userReviewcCreateRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userReviewService.createUserReview(userId, userReviewcCreateRequest, userDetails.getUser());
        return RESPONSE_OK;
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<PagingUserReviewResponse>> getUserReviews(@PathVariable Long userId, 
    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<PagingUserReviewResponse> pagingUserReviewResponseList = userReviewService.getUserReviews(userId);
        return ResponseEntity.ok().body(pagingUserReviewResponseList);
    }
    
}
