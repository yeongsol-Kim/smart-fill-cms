package com.smartf.comu.controller;

import com.smartf.comu.domain.Notice;
import com.smartf.comu.dto.NoticeDto;
import com.smartf.comu.service.NoticeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String superNoticePage(Model model, Authentication authentication) {
        String auth = authentication.getAuthorities().toString();
        System.out.println(authentication.getAuthorities());


        List<Notice> notices = noticeService.getNoticeList();
        model.addAttribute("notices", notices);
        if (auth.equals("[ROLE_SUPER]")) {
            return "/super/notice/noticeList";
        } else {
            return "/notice/noticeList";
        }
    }

    @GetMapping("/notice/new")
    @PreAuthorize("hasRole('SUPER')")
    public String superNoticeWritePage(Model model) {
        model.addAttribute("notice", Notice.builder().build());
        return "/super/notice/noticeForm";
    }

    @GetMapping("/notice/edit/{id}")
    @PreAuthorize("hasRole('SUPER')")
    public String superNoticeWritePage(Model model, @PathVariable Long id) {
        Notice notice = noticeService.getNotice(id);
        if (notice == null) {
            return "redirect:/notice";
        }
        model.addAttribute("notice", notice);
        return "/super/notice/noticeForm";
    }

    @PostMapping("/notice/edit/{id}")
    @PreAuthorize("hasRole('SUPER')")
    public String noticeUpdate(NoticeDto noticeDto, @PathVariable Long id) {
        Notice notice = Notice.builder()
                .id(id)
                .title(noticeDto.getTitle())
                .contents(noticeDto.getContents())
                .build();
        noticeService.insertNotice(notice);
        return "redirect:/notice";
    }

    @PostMapping("/notice/new")
    @PreAuthorize("hasRole('SUPER')")
    public String noticeInsert(NoticeDto noticeDto) {
        Notice notice = Notice.builder()
                .title(noticeDto.getTitle())
                .contents(noticeDto.getContents())
                .build();
        noticeService.insertNotice(notice);
        return "redirect:/notice";
    }

    @PostMapping("/noticeDelete")
    @PreAuthorize("hasRole('SUPER')")
    public String noticeDelete(@RequestParam(value = "id") Long id) {
        noticeService.deleteNotice(id);
        return "redirect:/notice";
    }
}
