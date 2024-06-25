package net.kdigital.web_project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.BycounExProductDTO;
import net.kdigital.web_project.dto.BycounIeProductDTO;
import net.kdigital.web_project.dto.BycounImExPriceDTO;
import net.kdigital.web_project.dto.BycounImMarketDTO;
import net.kdigital.web_project.dto.BycounImProductDTO;
import net.kdigital.web_project.dto.XyclusterDTO;
import net.kdigital.web_project.service.BycounExProductService;
import net.kdigital.web_project.service.BycounIeProductService;
import net.kdigital.web_project.service.BycounImExPriceService;
import net.kdigital.web_project.service.BycounImMarketService;
import net.kdigital.web_project.service.BycounImProductService;
import net.kdigital.web_project.service.XyclusterService;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/trade")
public class StatController {
	private final BycounImExPriceService imexService;
	private final BycounImMarketService marketService;
	private final XyclusterService xyclusterService;
	private final BycounIeProductService ieproService;
	private final BycounExProductService exproService;
	private final BycounImProductService improService;
	
	@GetMapping("/showStat")
	public String statShow() {
		return "tradeStat";
	}

	/**
	 * 국가별 수출입 금액
	 * @param country
	 * @param model
	 * @return
	 */
	@GetMapping("/stringChart")
	@ResponseBody
	public List<BycounImExPriceDTO> stringChart(
			@RequestParam(name="country", defaultValue="CN") String country,
			Model model
			) {
		log.info("stringChartr나라 여기는 컨트롤러 : {}", country);
		List<BycounImExPriceDTO> list = imexService.bycounImExPrice(country);
		log.info("=========== stringChart : {}", list.toString());
		return list;
	}//end stringChart
	
	/**
	 * 국가별 수입시장 점유율
	 * @param country
	 * @param model
	 * @return
	 */
	@GetMapping("/pieChart")
	@ResponseBody
	public List<BycounImMarketDTO> pieChart(
			@RequestParam(name="country", defaultValue="CN") String country,
			Model model
			) {
		log.info("============== pieChart나라 : {}", country);
		List<BycounImMarketDTO> list = marketService.bycounImMarketRanking(country);
		log.info("===========pieChart 데이터 : {}", list);
		return list;
	}//end barChart

	/**
	 * 한국 top10품목 
	 * @return
	 */
	@GetMapping({"/xyCluster"})
	@ResponseBody
	public List<XyclusterDTO> xyCluster(
			Model model
			){
		log.info("한국 top10품목 데이터 요청");
		
		List<XyclusterDTO> dtoList = xyclusterService.selectAll();
		//log.info("dtolist는 어떻게 와요? : {}", dtoList);
		
		//품목명 길어서 줄바꿈
		for (XyclusterDTO dto : dtoList) {
	        // product 문자열을 10자씩 나누어 줄바꿈을 추가
	        String product = dto.getProductName();
	        StringBuilder refinedProduct = new StringBuilder();
	        
	        for (int i = 0; i < product.length(); i += 22) {
	            if (i + 22 < product.length()) {
	                refinedProduct.append(product.substring(i, i + 22)).append("\n");
	            } else {
	                refinedProduct.append(product.substring(i));
	            }
	        }
	        
	        dto.setProductName(refinedProduct.toString().trim());
	    }
	    //log.info("새로바뀐 dto {}", dtoList.size());
	    return dtoList;
	}//end xyCluster
	
	/**
	 * 국가별 수출입품목 증감율
	 * @param country
	 * @param model
	 * @return
	 */
	@GetMapping("/StackCluster")
	@ResponseBody
	public List<BycounIeProductDTO> StackCluster(
			@RequestParam(name="country", defaultValue="CN") String country,
			Model model
			) {
		//log.info("파이차트 나라 : {}", country);
		List<BycounIeProductDTO> dtoList = ieproService.selectAll(country);
		//log.info("===========StackCluster : {}", dtoList.toString());
		
		//품목명 길어서 줄바꿈
		for (BycounIeProductDTO dto : dtoList) {
	        // product 문자열을 10자씩 나누어 줄바꿈을 추가
	        String product = dto.getProductName();
	        StringBuilder refinedProduct = new StringBuilder();
	        
	        for (int i = 0; i < product.length(); i += 22) {
	            if (i + 22 < product.length()) {
	                refinedProduct.append(product.substring(i, i + 22)).append("\n");
	            } else {
	                refinedProduct.append(product.substring(i));
	            }
	        }
	        
	        dto.setProductName(refinedProduct.toString().trim());
	    }
		return dtoList;
	}//end stringChart
	
	
	/**
	 * 국가별 10대 수출품목
	 * @param country
	 * @param model
	 * @return
	 */
	@GetMapping("/Exbarchart")
	@ResponseBody
	public List<BycounExProductDTO> Exbarchart(
			@RequestParam(name="country", defaultValue="CN") String country,
			Model model
			) {
		log.info("Exbarchart로 옴");
		List<BycounExProductDTO> dtoList = exproService.selectAll(country);
		
//		log.info("Exbarchart : {}", dtoList);
		
		//품목명 길어서 줄바꿈
		for (BycounExProductDTO dto : dtoList) {
	        // product 문자열을 10자씩 나누어 줄바꿈을 추가
	        String product = dto.getProductName();
	        StringBuilder refinedProduct = new StringBuilder();
	        
	        for (int i = 0; i < product.length(); i += 30) {
	            if (i + 30 < product.length()) {
	                refinedProduct.append(product.substring(i, i + 30)).append("\n");
	            } else {
	                refinedProduct.append(product.substring(i));
	            }
	        }
	        
	        dto.setProductName(refinedProduct.toString().trim());
	    }
		return dtoList;
	}//end Exbarchart
	
	/**
	 * 국가별 10대 수출품목
	 * @param country
	 * @param model
	 * @return
	 */
	@GetMapping("/Ixbarchart")
	@ResponseBody
	public List<BycounImProductDTO> Ixbarchart(
			@RequestParam(name="country", defaultValue="CN") String country,
			Model model
			) {
		log.info("Ixbarchart로 옴");
		List<BycounImProductDTO> dtoList = improService.selectAll(country);
//		log.info("Ixbarchart : {}", dtoList);
		
		//품목명 길어서 줄바꿈
		for (BycounImProductDTO dto : dtoList) {
	        // product 문자열을 10자씩 나누어 줄바꿈을 추가
	        String product = dto.getProductName();
	        StringBuilder refinedProduct = new StringBuilder();
	        
	        for (int i = 0; i < product.length(); i += 30) {
	            if (i + 30 < product.length()) {
	                refinedProduct.append(product.substring(i, i + 30)).append("\n");
	            } else {
	                refinedProduct.append(product.substring(i));
	            }
	        }
	        
	        dto.setProductName(refinedProduct.toString().trim());
	    }
		return dtoList;
	}//end Ixbarchart
	
}//end class

