package net.kdigital.web_project.controller;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kdigital.web_project.dto.NewsDTO;

@Controller
public class NewsController {

	@GetMapping("/news")
	public String news(Model model) {

		URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.naver.com")
				.path("/v1/search/news.json")
				.queryParam("query", "무역")
				.queryParam("query", "무역규제")
				.queryParam("query", "무역수지")
				.queryParam("query", "무역거래")
				.queryParam("query", "무역협정")
				.queryParam("query", "수출입")
				.queryParam("display", 10)
				.queryParam("start", 1)
				.queryParam("sort", "date")
				.encode(Charset.forName("UTF-8"))
				.build()
				.toUri();

		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-Naver-Client-Id", "g0AqMX9OmgWDK3sg56fF")
				.header("X-Naver-Client-Secret", "ETSdVZr8aE")
				.build();

		ResponseEntity<String> result = restTemplate.exchange(req, String.class);

		// API 호출 결과를 DTO로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		List<NewsDTO> dtoList;
		try {
			JsonNode rootNode = objectMapper.readTree(result.getBody());
			JsonNode itemsNode = rootNode.path("items");
			dtoList = objectMapper.convertValue(itemsNode, new TypeReference<List<NewsDTO>>() {
			});
		} catch (JsonProcessingException e) {

			return "";
		}

		model.addAttribute("list", dtoList);
		return "/news";
	}

}
