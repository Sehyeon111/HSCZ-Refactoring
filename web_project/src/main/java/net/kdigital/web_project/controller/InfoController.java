package net.kdigital.web_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	@GetMapping("/infoList")
	public String list() {
		
		return "info/infoList";
		
	}
	
	@GetMapping("/origin")
	public String origin() {
		
		return "info/origin";
		
	}
	
	@GetMapping("/country")
	public String country() {
		
		return "info/country";
		
	}
	
	@GetMapping("/import_utilization")
	public String import_utilization() {
		
		return "info/import_utilization";
		
	}
	
	@GetMapping("/tarrifrate")
	public String tarrifrate() {
		
		return "info/tarrifrate";
		
	}
	
	@GetMapping("/export_utilization")
	public String export_utilization() {
		
		return "info/export_utilization";
		
		
	}
	
	@GetMapping("/certificate")
	public String certificate() {
		
		return "info/certificate";
		
	}
}
