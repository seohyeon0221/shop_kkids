package com.example.shop_project_01.service;

import com.example.shop_project_01.dto.NoticeDto;
import com.example.shop_project_01.entity.Notice;
import com.example.shop_project_01.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;

    public List<NoticeDto> showAllNotice() {
        return noticeRepository.findAll().stream().map(x -> NoticeDto.fromNoticeEntity(x)).toList();
    }

    public NoticeDto noticeViewFindById(Long noticeId) {
        return noticeRepository.findById(noticeId).map(x -> NoticeDto.fromNoticeEntity(x)).orElse(null);
    }

    public Page<Notice> pagingList(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }
}
