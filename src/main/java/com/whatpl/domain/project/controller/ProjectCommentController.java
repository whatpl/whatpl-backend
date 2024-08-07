package com.whatpl.domain.project.controller;

import com.whatpl.global.security.domain.MemberPrincipal;
import com.whatpl.domain.project.dto.ProjectCommentCreateRequest;
import com.whatpl.domain.project.dto.ProjectCommentListResponse;
import com.whatpl.domain.project.dto.ProjectCommentUpdateRequest;
import com.whatpl.domain.project.service.ProjectCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectCommentController {

    private final ProjectCommentService projectCommentService;

    @GetMapping("/projects/{projectId}/comments")
    public ResponseEntity<ProjectCommentListResponse> readList(@PathVariable Long projectId) {
        ProjectCommentListResponse projectCommentListResponse = projectCommentService.readProjectCommentList(projectId);
        return ResponseEntity.ok(projectCommentListResponse);
    }

    @PostMapping("/projects/{projectId}/comments")
    public ResponseEntity<Void> write(@PathVariable Long projectId,
                                      @AuthenticationPrincipal MemberPrincipal principal,
                                      @Valid @RequestBody ProjectCommentCreateRequest request) {
        projectCommentService.createProjectComment(request, projectId, principal.getId());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasPermission(#commentId, 'PROJECT_COMMENT', 'UPDATE')")
    @PatchMapping("/projects/{projectId}/comments/{commentId}")
    public ResponseEntity<Void> modify(@PathVariable Long projectId,
                                       @PathVariable Long commentId,
                                       @Valid @RequestBody ProjectCommentUpdateRequest request) {
        projectCommentService.updateProjectComment(request, projectId, commentId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasPermission(#commentId, 'PROJECT_COMMENT', 'DELETE')")
    @DeleteMapping("/projects/{projectId}/comments/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long projectId,
                                       @PathVariable Long commentId) {
        projectCommentService.deleteProjectComment(projectId, commentId);
        return ResponseEntity.noContent().build();
    }
}
