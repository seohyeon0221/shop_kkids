package com.example.shop_project_01.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
//공지사항 엔티티
public class Notice {

    //공지사항 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    private String title;

    private String content;

    @Column(name = "created_at",updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column(name = "updated_at",insertable = false)
    @LastModifiedDate
    private LocalDate updatedAt;
}
