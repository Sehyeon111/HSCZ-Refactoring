package net.kdigital.web_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kdigital.web_project.entity.BoardEntity;
import net.kdigital.web_project.repository.CCARepository;

@SpringBootTest
class WebProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	// 테스트 코드 - 메소드별로 실행, 생성자 주입방식으로 생성안됨- @Autowired사용
	@Autowired // bin을 주입받기 위한 하나의 annotation
	private CCARepository repository;

	@Test 
	void testInsertBoard() {
		for(int i=0; i<100; ++i) {
			BoardEntity b = new BoardEntity();

			b.setConsultTitle("수입신고는 어떻게 하고 어떤 서류를 제출해야 하나요?");
			b.setConsultWriter("suyeon123");
			b.setConsultContent("◎수입신고는 P/L 신고를 원칙으로 하고 있으므로, 첨부서류 없이 전자적 방법으로 수입신고서를 작성하여 관세청 전자통관시스템 유니패스*를 통해 전송하면 됩니다.* https://unipass.customs.go.kr\r\n" + //
								"\r\n" +
								"◎다만, 서류제출대상으로 선별된 수입신고 건에 대해서는 송품장, 가격신고서, 선하증권, 포장명세서 등 「수입통관 사무처리에 관한 고시」 제15조제1항 각 호의 서류를 스캔 등의 방법으로 전자 이미지화하거나 무역서류의 전자제출 방법을 이용하여 통관시스템에 전송하면 됩니다.\r\n" + //
								"\r\n" +
								"◎그러나 일시수입통관증서(A.T.A Carnet)에 의한 일시수입물품, 사전세액심사 대상물품 등 「수입통관 사무처리에 관한 고시」 제15조제2항 각 호의 어느 하나에 해당하는 경우에는 종이서류를 제출해야 합니다.");
			b.setProductCategory("전체");

			repository.save(b);
		}
	}
}
