package net.kdigital.web_project.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.dto.PredictDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class PredictService {

	@Value("${hscode.predict.server}")
	String url;

	private final RestTemplate restTemplate;

	public Map<String, String> predictRest(PredictDTO predictDTO) {
		Map<String, String> error = new HashMap<>();
		Map<String, String> result = new HashMap<>();
		log.info("=============={}", predictDTO.getDescription());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			ResponseEntity<Map> response = restTemplate.postForEntity(url, predictDTO, Map.class);
			result = response.getBody();
			// log.info("{}", result);

		} catch (Exception e) {
			error.put("statusCode", "450");
			error.put("body", "오류남");

			return error;
		}

		return result;

	}

}
