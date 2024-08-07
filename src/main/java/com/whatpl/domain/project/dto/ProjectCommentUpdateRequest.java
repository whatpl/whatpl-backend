package com.whatpl.domain.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCommentUpdateRequest {

    @NotBlank(message = "댓글 내용을 입력해 주세요.")
    @Size(max = 300, message = "댓글은 최대 300자까지 입력 가능합니다.")
    private String content;
}
