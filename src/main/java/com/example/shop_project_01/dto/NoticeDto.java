package com.example.shop_project_01.dto;

import com.example.shop_project_01.entity.Notice;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class NoticeDto {

    private Long noticeId;
    @NotEmpty(message = "공지사항 제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "공지사항 내용을 입력해주세요.")
    private String content;

    private LocalDate createdAt;
    private LocalDate updatedAt;


    public static NoticeDto fromNoticeEntity(Notice notice){
        return new NoticeDto(
                notice.getNoticeId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getCreatedAt(),
                notice.getUpdatedAt()
        );
    }

    public static Notice fromNoticeDto(NoticeDto dto){
        Notice notice = new Notice();
        notice.setNoticeId(dto.getNoticeId());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        notice.setCreatedAt(dto.getCreatedAt());
        notice.setUpdatedAt(dto.getUpdatedAt());
        return notice;
    }
}
