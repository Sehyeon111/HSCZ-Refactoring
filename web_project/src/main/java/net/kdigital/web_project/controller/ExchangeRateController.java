package net.kdigital.web_project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kdigital.web_project.api.OpenApiManager;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateController {
	
	private final OpenApiManager openApiManager;
	
	@PostMapping("/exchangeRate")
    public ResponseEntity<List<Map<String, String>>> exchangeRate(@RequestParam(name="today") String today,
                                                                  @RequestParam(name="imexTp") String imexTp) {
  try {
            List<Map<String, String>> exchangeRateList = openApiManager.exchangeRateOpenApi(today, imexTp);
            log.info("Fetched exchange rates: {}", exchangeRateList);
            return ResponseEntity.ok(exchangeRateList);
        } catch (Exception e) {
            log.error("Error fetching exchange rates: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
	
	@GetMapping("/exchangeRate")
	public String exchangeRate() {
		return "exchangeRate";
	}

}
