package net.kdigital.web_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.board.domain.Board;
import net.kdigital.web_project.board.infrastructure.BoardEntity;
import net.kdigital.web_project.board.service.BoardService;
import net.kdigital.web_project.dto.AnswerDTO;
import net.kdigital.web_project.service.ReplyService;
import net.kdigital.web_project.user.UserService;
import net.kdigital.web_project.user.domain.User;
import net.kdigital.web_project.userItem.domain.UserItem;
import net.kdigital.web_project.userItem.service.UserItemService;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/my")
public class MypageController {

	public final UserService userService;
	public final BoardService boardService;
	public final ReplyService replyService;
	public final UserItemService userItemService;
	public int boardCount;
	public int replyCount;

	@GetMapping("/userpage")
	public String userpage(Model model) {
		// 로그인한 유저가 쓴 글 갯수 counting
		boardCount = 0;
		// 로그인한 유저 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User customerDTO = userService.findById(username);

		// 유저 아이템 정보 가져오기
		UserItem customerItemDTO = userItemService.findItem(username);

		// 로그인한 유저가 작성한 글 리스트로 가져오기
		List<Board> boardList = boardService.findAllConsultsbyuserId(username);

		// 상담글과 해당 글에 해당하는 댓글 갯수 map에 저장
		Map<Board, Integer> dataMap = new HashMap<>();

		for (BoardEntity temp : boardEntityList) {
			// 작성한 글에 해당하는 댓글 가져오기
			List<AnswerDTO> replyDTOList = replyService.selectAllReplys(temp.getConsultNum());

			dataMap.put(Board.toDTO(temp), replyDTOList.size());

			boardCount += 1;
		}

		log.info("{}", dataMap);

		model.addAttribute("customerDTO", customerDTO);
		model.addAttribute("customerItemDTO", customerItemDTO);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("boardCount", boardCount);
		return "/user/userPage";
	}

	@GetMapping("/ccapage")
	public String ccapage(Model model) {
		replyCount = 0;
		// 로그인한 유저 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User customerDTO = userService.findById(username);

		// 유저 아이템 정보 가져오기
		UserItem customerItemDTO = userItemService.findItem(username);

		// 유저가 쓴 댓글 정보 가져오기
		List<AnswerDTO> replyDTOList = replyService.selectAllReplysByUsername(username);

		// 댓글과 그 댓글에 해당하는 상담글 정보 map에 저장
		Map<AnswerDTO, Board> dataMap = new HashMap<>();

		for (AnswerDTO answerDTO : replyDTOList) {

			// 댓글에 해당하는 글정보 가져오기
			Board boardDTO = ccaService.findById(answerDTO.getConsultNum());

			dataMap.put(answerDTO, boardDTO);

			replyCount += 1;

		}

		log.info("{}", dataMap);

		model.addAttribute("customerDTO", customerDTO);
		model.addAttribute("customerItemDTO", customerItemDTO);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("replyCount", replyCount);
		return "/user/ccaPage";
	}

	@GetMapping("/updateUserPage")
	public String updateUserPage(Model model) {
		boardCount = 0;
		// 로그인한 유저 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User customerDTO = userService.findById(username);

		// 유저 아이템 정보 가져오기
		UserItem customerItemDTO = userItemService.findItem(username);

		// 로그인한 유저가 작성한 글 가져오기
		List<Boardy> boardyList = userService.findAllConsultsbyuserId(username);

		log.info("{}", boardEntityList);

		Map<Board, Integer> dataMap = new HashMap<>();

		for (BoardEntity temp : boardEntityList) {
			// 작성한 글에 해당하는 댓글 가져오기
			List<AnswerDTO> replyDTOList = replyService.selectAllReplys(temp.getConsultNum());

			log.info("{}", replyDTOList.size());

			dataMap.put(Board.toDTO(temp), replyDTOList.size());
			boardCount += 1;
		}

		log.info("{}", dataMap);

		model.addAttribute("customerDTO", customerDTO);
		model.addAttribute("customerItemDTO", customerItemDTO);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("boardCount", boardCount);

		return "/user/updateUserPage";
	}

	@GetMapping("/updateCCAPage")
	public String updateCCAPage(Model model) {
		replyCount = 0;
		// 로그인한 유저 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User customerDTO = userService.findById(username);

		// 유저 아이템 정보 가져오기
		UserItem customerItemDTO = userItemService.findItem(username);

		// 유저가 쓴 댓글 정보 가져오기
		List<AnswerDTO> replyDTOList = replyService.selectAllReplysByUsername(username);

		Map<AnswerDTO, Board> dataMap = new HashMap<>();

		for (AnswerDTO answerDTO : replyDTOList) {

			// 댓글에 해당하는 글정보 가져오기
			Board boardDTO = ccaService.findById(answerDTO.getConsultNum());

			dataMap.put(answerDTO, boardDTO);

			replyCount += 1;

		}

		log.info("{}", dataMap);

		model.addAttribute("customerDTO", customerDTO);
		model.addAttribute("customerItemDTO", customerItemDTO);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("replyCount", replyCount);

		return "/user/updateCcaPage";
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute User updateUser, @ModelAttribute UserItem customerItemDTO) {

		// 로그인한 유저 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// 유저 아이템 업데이트
		UserItem updatedCustomerItemDTO = userItemService.updateItem(username, customerItemDTO);

		// 유저 정보 업데이트
		User updatedCustomerDTO = userService.updateUser(username, updateUser);

		return "redirect:/my/userpage";
	}

	@PostMapping("/updateCCA")
	public String updateCCA(@ModelAttribute User customerDTO, @ModelAttribute UserItem customerItemDTO) {
		// 로그인한 유저 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// 유저 정보 업데이트
		UserItem updatedCustomerItemDTO = userItemService.updateItem(username, customerItemDTO);

		// 유저 아이템 업데이트
		User updatedCustomerDTO = userService.updateUser(username, customerDTO);

		log.info("{}", updatedCustomerDTO);
		log.info("{}", updatedCustomerItemDTO);

		return "redirect:/my/ccapage";
	}

}
