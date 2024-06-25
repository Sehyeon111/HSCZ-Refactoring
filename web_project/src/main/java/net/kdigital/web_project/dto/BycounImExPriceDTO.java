package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.BycounImExPriceEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BycounImExPriceDTO {
	private Long seq6;
	private String country;
	private int dateYear;
	private String dateMonth;
	private int exportPrice;
	private int importPrice;
	
	public static BycounImExPriceDTO toDTO (BycounImExPriceEntity bycounImExPriceEntity) {
		return BycounImExPriceDTO.builder()
				.seq6(bycounImExPriceEntity.getSeq6())
				.country(bycounImExPriceEntity.getCountry())
				.dateYear(bycounImExPriceEntity.getDateYear())
				.dateMonth(bycounImExPriceEntity.getDateMonth())
				.exportPrice(bycounImExPriceEntity.getExportPrice())
				.importPrice(bycounImExPriceEntity.getImportPrice())
				.build();
		
	}
}
