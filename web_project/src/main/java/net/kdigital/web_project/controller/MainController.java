package net.kdigital.web_project.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.CustomerItemDTO;
import net.kdigital.web_project.service.CustomerItemService;
import net.kdigital.web_project.user.UserService;
import net.kdigital.web_project.user.domain.User;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

	private final UserService customerService;
	private final CustomerItemService customerItemService;

	@GetMapping({ "/", "" })
	public String index(
			Model model) {

		List<User> ccaList = customerService.selectTop3CCA();
		Map<User, CustomerItemDTO> ccaInfoMap = new HashMap<>();

		for (int i = 0; i < ccaList.size(); i++) {
			String userId = ccaList.get(i).getUserId();
			CustomerItemDTO itemList = customerItemService.findItem(userId);

			ccaInfoMap.put(ccaList.get(i), itemList);
		}

		log.info("====== ccaList: {}", ccaList);
		model.addAttribute("ccaList", ccaList);
		model.addAttribute("ccaInfoMap", ccaInfoMap);

		return "main";
	}

}
