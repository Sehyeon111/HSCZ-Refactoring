package net.kdigital.web_project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.AnswerDTO;
import net.kdigital.web_project.dto.BoardDTO;
import net.kdigital.web_project.dto.CCAListDTO;
import net.kdigital.web_project.service.CCAListService;
import net.kdigital.web_project.service.CCAService;
import net.kdigital.web_project.service.ReplyService;
import net.kdigital.web_project.user.UserService;
import net.kdigital.web_project.userItem.domain.UserItem;
import net.kdigital.web_project.userItem.service.UserItemService;
import net.kdigital.web_project.util.PageNavigator;

@Slf4j
@Controller
@RequestMapping("/cca")
public class CCAController {
    private final CCAService ccaService;
    private final ReplyService replyService;
    private final int pageLimit; // 한 페이지에 보여줄 글의 개수
    private final CCAListService ccaListService;
    private final UserService customerService;
    private final UserItemService customerItemService;

    public CCAController(CCAService ccaService, ReplyService replyService,
            @Value("${user.board.pageLimit}") int pageLimit, CCAListService ccaListService,
            UserService customerService, UserItemService customerItemService) {
        this.ccaService = ccaService;
        this.replyService = replyService;
        this.pageLimit = pageLimit;
        this.ccaListService = ccaListService;
        this.customerService = customerService;
        this.customerItemService = customerItemService;

    }

    /**
     * 상담 목록 불러오기
     * 
     * @param pageable
     * @param searchItem
     * @param searchWord
     * @param model
     * @return
     */
    @GetMapping("/boardList")
    public String boardList(
            @PageableDefault(page = 1) Pageable pageable,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
            Model model) {

        Page<BoardDTO> dtoList;

        dtoList = ccaService.findAllConsultsbySearch(pageable, searchWord, searchItem);

        int totalPages = dtoList.getTotalPages();
        int page = pageable.getPageNumber();

        PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);

        model.addAttribute("consultList", dtoList);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("navi", navi);

        return "/cca/boardList";
    }

    /**
     * 글쓰기 화면 요청하기
     * 
     * @param hsCode
     * @param model
     * @return
     */
    @GetMapping("/ccaWrite")
    public String ccaWrite(@RequestParam(name = "hsCode", defaultValue = "") String hsCode, Model model) {
        log.info("글쓰기 화면 요청");

        model.addAttribute("hsCode", hsCode);

        return "/cca/ccaWrite";
    }

    /**
     * 새 상담글 등록하기
     * 
     * @param boardDTO
     * @return
     */
    @PostMapping("/ccaWrite")
    public String ccaWrite(@ModelAttribute BoardDTO boardDTO) {
        log.info("+++++++++++{}", boardDTO);

        Long consultNum = ccaService.insertConsult(boardDTO);

        return "redirect:/cca/detail?consultNum=" + consultNum;
    }

    /**
     * 상담글 상세 화면 요청하기
     * 
     * @param consultNum
     * @param searchBy
     * @param searchItem
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String ccaDetail(
            @RequestParam(name = "consultNum") Long consultNum,
            @RequestParam(name = "searchBy", defaultValue = "") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            HttpServletRequest request,
            Model model) {

        BoardDTO boardDTO = ccaService.selectOneConsult(consultNum);
        List<AnswerDTO> replyList = replyService.selectAllReplys(consultNum); // 예시일 뿐, 해당 메서드가 실제로 존재한다고 가정

        Map<AnswerDTO, UserItem> dataMap = new HashMap<>();
        for (AnswerDTO temp : replyList) {
            UserItem customerItemDTO = customerItemService.findItem(temp.getReplyWriter());
            dataMap.put(temp, customerItemDTO);
        }

        model.addAttribute("consult", boardDTO);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("searchBy", searchBy);
        model.addAttribute("dataMap", dataMap);
        model.addAttribute("replyList", replyList); // 답변 DTO도 Model에 추가

        return "cca/detail";
    }

    /**
     * 상담글 삭제 처리하기
     * 
     * @param consultNum
     * @param searchBy
     * @param searchItem
     * @param rttr
     * @return
     */
    @GetMapping("/delete")
    public String boardDelete(
            @RequestParam(name = "consultNum") Long consultNum,
            @RequestParam(name = "searchBy") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            RedirectAttributes rttr) {

        ccaService.deleteOneConsult(consultNum);

        rttr.addAttribute("searchItem", searchItem);
        rttr.addAttribute("searchBy", searchBy);
        return "redirect:/cca/boardList";
    }

    /**
     * 상담글 수정화면 요청하기
     * 
     * @param consultNum
     * @param searchBy
     * @param searchItem
     * @param model
     * @return
     */
    @GetMapping("/update")
    public String boardUpdate(
            @RequestParam(name = "consultNum") Long consultNum,
            @RequestParam(name = "searchBy", defaultValue = "") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            Model model) {

        BoardDTO boardDTO = ccaService.selectOneConsult(consultNum);
        model.addAttribute("consult", boardDTO);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("searchBy", searchBy);

        return "cca/update";
    }

    /**
     * 상담글 수정 처리
     * 
     * @param boardDTO
     * @param searchBy
     * @param searchItem
     * @param rttr
     * @return
     */
    @PostMapping("/update")
    public String boardUpdate(
            @ModelAttribute BoardDTO boardDTO,
            @RequestParam(name = "searchBy", defaultValue = "") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            RedirectAttributes rttr) {

        ccaService.updateOneConsult(boardDTO);
        rttr.addAttribute("consultNum", boardDTO.getConsultNum());
        rttr.addAttribute("searchItem", searchItem);
        rttr.addAttribute("searchBy", searchBy);
        return "redirect:/cca/detail";
    }

    /**
     * 답변 작성 화면 요청
     * 
     * @param model
     * @param consultNum
     * @return
     */
    @GetMapping("/replyWrite")
    public String replyWritePage(Model model, @RequestParam("consultNum") Long consultNum) {

        model.addAttribute("consultNum", consultNum);
        return "cca/replyWrite"; // replyWrite.html로 이동
    }

    /**
     * 답변 작성 처리
     * 
     * @param answerDTO
     * @param consultNum
     * @param searchBy
     * @param searchItem
     * @return
     */
    @PostMapping("/replyWrite")
    public String replyWrite(
            @ModelAttribute AnswerDTO answerDTO, @RequestParam("consultNum") Long consultNum,
            @RequestParam("searchBy") String searchBy, @RequestParam("searchItem") String searchItem) {

        System.out.println(consultNum);
        System.out.println(searchBy);
        answerDTO.setConsultNum(consultNum);

        replyService.insertAnswer(answerDTO);

        // searchBy와 searchItem을 포함하여 상세 페이지로 리다이렉트
        return "redirect:/cca/detail?consultNum=" + consultNum + "&searchBy=" + searchBy + "&searchItem=" + searchItem;
    }

    /**
     * 답변 수정화면 요청
     * 
     * @param consultNum
     * @param replyNum
     * @param searchBy
     * @param searchItem
     * @param model
     * @return
     */
    @GetMapping("/replyUpdate")
    public String replyUpdate(
            @RequestParam(name = "consultNum") Long consultNum,
            @RequestParam(name = "replyNum") Long replyNum,
            @RequestParam(name = "searchBy") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            Model model) {
        System.out.println("업데이트 확인");
        System.out.println(consultNum);
        System.out.println(replyNum);
        AnswerDTO answerDTO = replyService.selectOneAnswer(replyNum, consultNum);
        BoardDTO boardDTO = ccaService.selectOneConsult(consultNum);
        System.out.println(answerDTO.toString());
        model.addAttribute("reply", answerDTO);
        model.addAttribute("consult", boardDTO);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("searchBy", searchBy);
        return "cca/replyUpdate";
    }

    /**
     * 답변 수정처리
     * 
     * @param answerDTO
     * @param replyNum
     * @param consultNum
     * @param searchBy
     * @param searchItem
     * @param rttr
     * @return
     */
    @PostMapping("/replyUpdate")
    public String replyUpdate(
            @ModelAttribute AnswerDTO answerDTO,
            @RequestParam(name = "replyNum") Long replyNum,
            @RequestParam(name = "consultNum") Long consultNum,
            @RequestParam(name = "searchBy") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            RedirectAttributes rttr) {

        AnswerDTO updatedAnswer = replyService.updateOneAnswer(replyNum, answerDTO);
        System.out.println("답변컨트롤러 옴");
        System.out.println(replyNum);
        System.out.println(consultNum);

        System.out.println(updatedAnswer.toString());
        rttr.addAttribute("replyNum", answerDTO.getReplyNum());
        rttr.addAttribute("searchItem", searchItem);
        rttr.addAttribute("searchBy", searchBy);
        return "redirect:/cca/detail?consultNum=" + consultNum + "&searchBy=" + searchBy + "&searchItem=" + searchItem;
    }

    /**
     * 답변 삭제 처리
     * 
     * @param replyNum
     * @param consultNum
     * @param searchBy
     * @param searchItem
     * @param rttr
     * @return
     */
    @GetMapping("/replyDelete")
    public String replyDelete(
            @RequestParam(name = "replyNum") Long replyNum,
            @RequestParam(name = "consultNum") Long consultNum,
            @RequestParam(name = "searchBy") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            RedirectAttributes rttr) {

        replyService.deleteOneAnswer(replyNum, consultNum);
        System.out.println("답변 삭제됨");
        rttr.addAttribute("searchItem", searchItem);
        rttr.addAttribute("searchBy", searchBy);
        return "redirect:/cca/detail?consultNum=" + consultNum + "&searchBy=" + searchBy + "&searchItem=" + searchItem;
    }

    /**
     * 답변 불러오기
     * 
     * @param replyNum
     * @return
     */
    @GetMapping("/replyAll")
    @ResponseBody
    public List<AnswerDTO> replyAll(
            @RequestParam(name = "replyNum") Long replyNum) {

        log.info("{}", replyNum);
        List<AnswerDTO> replyList = replyService.selectAllReplys(replyNum);
        log.info("===> {}", replyList);

        return replyList;
    }

    /**
     * 관세사 목록
     * 
     * @param pageable
     * @param searchBy
     * @param searchItem
     * @param model
     * @return
     */
    @GetMapping("/ccaList")
    public String ccaList(
            @PageableDefault(page = 1) Pageable pageable,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem,
            @RequestParam(name = "searchWord", defaultValue = "") String searchWord,
            Model model) {
        Page<CCAListDTO> dtoList;

        dtoList = ccaListService.findAllCCABySearch(pageable, searchItem, searchWord);

        int totalPages = (int) dtoList.getTotalPages();
        int page = pageable.getPageNumber();
        PageNavigator navi = new PageNavigator(pageLimit, page, totalPages);
        log.info("관세사 리스트 : {}", dtoList);
        model.addAttribute("ccaList", dtoList);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("navi", navi);
        model.addAttribute("searchWord", searchWord);
        return "cca/ccaList";
    }

    /**
     * 답변 채택 처리
     * 
     * @param replyNum
     * @param consultNum
     * @param replyWriter
     * @param searchBy
     * @param searchItem
     * @return
     */
    @PostMapping("/increaseLike")
    public String increaseLike(
            @RequestParam("replyNum") Long replyNum,
            @RequestParam("consultNum") Long consultNum,
            @RequestParam("replyWriter") String replyWriter,
            @RequestParam(name = "searchBy", defaultValue = "") String searchBy,
            @RequestParam(name = "searchItem", defaultValue = "") String searchItem) {
        // 로그인한 유저 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // UserDetails userDetails = (UserDetails) principal;
        String username = ((UserDetails) principal).getUsername();

        log.info("===================increaseLike 도착");
        // 사용자가 답변에 추천을 했는지 확인
        boolean likeCheck = customerService.checkIsAlreadyLiked(replyNum, username);

        if (likeCheck) {
            // customerLike 엔티티에 추가
            customerService.insertCustomerLike(replyNum, username);
            log.info("============ customerLikeEntity에 추가됨");

            // reply_cca 엔티티의 like_count 증가
            replyService.increaseLikeCount(replyNum);
            log.info("============= likeCount 증가");

            // customer 엔티티의 like_total 증가
            customerService.increaseTotalLike(replyWriter);
            log.info("============= likeTotal 증가");

            return "redirect:/cca/detail?consultNum=" + consultNum;
        }

        return "redirect:/cca/detail?consultNum=" + consultNum;
    }

}
