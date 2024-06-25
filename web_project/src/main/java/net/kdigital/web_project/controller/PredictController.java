package net.kdigital.web_project.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.PredictDTO;
import net.kdigital.web_project.service.PredictService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PredictController {
	
	private final PredictService service;
	
	@PostMapping("/predict")
	public String predict(@ModelAttribute PredictDTO predictDTO, Model model) {
		
		Map<String, String> result = service.predictRest(predictDTO);
		
		log.info("{}", result);
		
		String combinedResult = String.join("/", result.values());
		
		String[] resultAry = combinedResult.split("/");
		
		model.addAttribute("result", resultAry);
		
		return "predict/top3";
	}
	
	
}
