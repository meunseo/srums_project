package org.kitri.services.sales.employee.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.kitri.services.common.login.session.SvcComLgnSsn;
import org.kitri.services.common.pageauth.SvcComPgcAcp;
import org.kitri.services.sales.employee.dto.SvcComEmpDepDto;
import org.kitri.services.sales.employee.dto.SvcComEmpDto;
import org.kitri.services.sales.employee.dto.SvcComEmpPosDto;
import org.kitri.services.sales.employee.dto.SvcComEmpRolDto;
import org.kitri.services.sales.employee.service.ISvcComEmpInqSvc;
import org.kitri.services.sales.employee.service.ISvcComEmpPRDSvc;
import org.kitri.services.sales.repo.dto.SvcComEmpLgnDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SvcComEmpInqCtl {

	@Autowired
	private ISvcComEmpPRDSvc iPrdSvc;

	@Autowired
	private ISvcComEmpInqSvc iInqSvc;

	@Autowired
	private SvcComPgcAcp auth;

	@Autowired
	private SvcComLgnSsn sessionManager;

	@Autowired
	private HttpSession session;

	@RequestMapping("/employee")
	public String employeeInquiry(@RequestParam(required = false) String employeeId,
			@RequestParam(required = false) String positionId, @RequestParam(required = false) String roleId,
			@RequestParam(required = false) String departmentId, Model model) {

		SvcComEmpLgnDto sessionEmployee = (SvcComEmpLgnDto) sessionManager.getValue(session, "user");

		if (sessionEmployee == null) {
			return "redirect:/employee/login";
		}

		if (!permissionCheck("ShqEmpEmiChk", sessionEmployee)) {
			return "includes/PermissionError";
		}

		List<SvcComEmpPosDto> posDtos = iPrdSvc.positionInquiry();
		List<SvcComEmpRolDto> rolDtos = iPrdSvc.roleInquiry();
		List<SvcComEmpDepDto> depDtos = iPrdSvc.departmentInquiry();

		model.addAttribute("positions", posDtos);
		model.addAttribute("roles", rolDtos);
		model.addAttribute("departments", depDtos);

		List<SvcComEmpDto> inqDtos = iInqSvc.employeeInquiryByFilters(employeeId, positionId, roleId, departmentId);

		model.addAttribute("employeeList", inqDtos);
		return "/sales/employee/employeelist";
	}

	private boolean permissionCheck(String serviceId, SvcComEmpLgnDto sessionEmployee) {
		SvcComEmpDto svcComEmpDto = iInqSvc.employeeInquiryByFilters(sessionEmployee.getEmployeeId(), null, null, null)
				.get(0);
		if (!auth.hasAuthority(svcComEmpDto, serviceId)) {
			// 권한없음
			return false;
		}
		return true;
	}
}
