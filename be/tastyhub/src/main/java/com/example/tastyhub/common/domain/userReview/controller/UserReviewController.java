package com.example.tastyhub.common.domain.userReview.controller;

import static com.example.tastyhub.common.config.APIConfig.USERREVIEW_API;
import static com.example.tastyhub.common.utils.HttpResponseEntity.DELETE_SUCCESS;
import static com.example.tastyhub.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.tastyhub.common.utils.HttpResponseEntity.RESPONSE_OK;

import com.example.tastyhub.common.domain.userReview.dtos.UserReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tastyhub.common.domain.userReview.dtos.PagingUserReviewResponse;

import com.example.tastyhub.common.domain.userReview.service.UserReviewService;
import com.example.tastyhub.common.dto.StatusResponse;
import com.example.tastyhub.common.utils.Jwt.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
      @RequestBody UserReviewRequest userReviewRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    userReviewService.createUserReview(userId, userReviewRequest, userDetails.getUser());
    return RESPONSE_CREATED;
  }

  @GetMapping("/list/{userId}")
  public ResponseEntity<Page<PagingUserReviewResponse>> getUserReviews(@PathVariable Long userId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
    Page<PagingUserReviewResponse> pagingUserReviewResponseList = userReviewService.getUserReviews(
        userId, pageable);
    return ResponseEntity.ok().body(pagingUserReviewResponseList);
  }

  @PatchMapping("/modify/{userReviewId}")
  public ResponseEntity<StatusResponse> updateUserReview(@PathVariable Long userReviewId,
      @RequestBody UserReviewRequest userReviewUpdateRequest,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    userReviewService.updateUserReviewByUserReviewUpdateRequest(userReviewId,
        userReviewUpdateRequest,
        userDetails.getUser());
    return RESPONSE_OK;

  }

  @DeleteMapping("/delete/{userReviewId}")
  public ResponseEntity<StatusResponse> deleteUserReview(@PathVariable Long userReviewId,
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    userReviewService.deleteUserReview(userReviewId, userDetails.getUser());
    return DELETE_SUCCESS;

  }

}
