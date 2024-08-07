package com.whatpl.domain.project.domain;

import com.whatpl.global.common.model.BaseTimeEntity;
import com.whatpl.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "project_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public ProjectLike(Project project, Member member) {
        this.project = project;
        this.member = member;
    }
}
