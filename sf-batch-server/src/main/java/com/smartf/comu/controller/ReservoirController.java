package com.smartf.comu.controller;

import com.smartf.comu.domain.Reservoir;
import com.smartf.comu.dto.BranchDto;
import com.smartf.comu.dto.FillLogDto;
import com.smartf.comu.dto.ReservoirDto;
import com.smartf.comu.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservoirController {

    private final ReservoirService reservoirService;
    private final BranchService branchService;
    private final PumpService pumpService;
    private final CompanyAdminService companyAdminService;
    private final FillLogService fillLogService;


    // 재고 페이지
    @GetMapping("/stock")
    @PreAuthorize("hasAnyRole('BRANCH', 'ADMIN')")
    public String stockPage(Model model, Authentication authentication) {
        if (authentication.getAuthorities().toString().equals("[ROLE_BRANCH]")) {
            List<FillLogDto> fillLogList = fillLogService.getMyBranchLogs();
            List<Reservoir> reservoirs = reservoirService.getMyBranchReservoirs();
//            List<Pump> pumps = pumpService.getMyPumps();
            model.addAttribute("fillLogs", fillLogList);
//            model.addAttribute("pumps", pumps);
            model.addAttribute("reservoirs", reservoirs);
            return "stock/stockPage";
        } else if(authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            List<BranchDto> branches = companyAdminService.getMyBranches();
            model.addAttribute("branches", branches);
            return "reservoirs/reservoirListSelect";
        }

        return "redirect:/";
    }


    // 저장조 등록 페이지
    @GetMapping("/reservoirs/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createReservoirForm(Model model) {
        List<BranchDto> branches = companyAdminService.getMyBranches();
        model.addAttribute("branches", branches);
        return "reservoirs/reservoirCreateSelectForm";
    }

    // 저장조 등록 처리
    @PostMapping("/reservoirs/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String createReservoir(ReservoirDto reservoirDto) {

        reservoirService.addReservoir(reservoirDto);
        return "redirect:/reservoirs";
    }

    // 입고 처리
    @PostMapping("/reservoirs/put")
    @PreAuthorize("hasAnyRole('BRANCH', 'ADMIN')")
    public String reservoirPut(HttpServletRequest request, ReservoirDto reservoirDto) {
//        reservoirDto.getId()
//        branchService.isMyBranch(reservoirDto.getAmount())

        reservoirService.putReservoir(reservoirDto);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/reservoirDelete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String reservoirDelete(@PathVariable Long id) throws Exception {
        reservoirService.deleteReservoir(id);
        return "redirect:/members";
    }

    @ResponseBody
    @GetMapping("/reservoirs/branch/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reservoir> getReservoirByBranch(@PathVariable Long id) {
        return reservoirService.getMyBranchReservoirs(id);
    }
}
