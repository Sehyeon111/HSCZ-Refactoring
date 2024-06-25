package net.kdigital.web_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.HeadingDTO;
import net.kdigital.web_project.service.HeadingService;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/heading")
public class HeadingController {
	
	private final HeadingService headingService;
	
	@PostMapping("/heading_des")
	@ResponseBody
	public HeadingDTO heading_des(@RequestParam(name="hs4digit", defaultValue = "") String hs4digit) {
		HeadingDTO headingDTO = headingService.selectOne(hs4digit);
		log.info("{}", headingDTO);
		
		return headingDTO;
	}
}
