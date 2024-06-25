package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.BycounImMarketEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BycounImMarketDTO {
	private Long seq5;
	private String country;
	private int dateYear;
	private int ranking;
	private String importMarket;
	private Long price;
	private String percentile;
	public static BycounImMarketDTO toDTO(BycounImMarketEntity bycounImMarketEntity) {
		return BycounImMarketDTO.builder()
				.seq5(bycounImMarketEntity.getSeq5())
				.country(bycounImMarketEntity.getCountry())
				.dateYear(bycounImMarketEntity.getDateYear())
				.ranking(bycounImMarketEntity.getRanking())
				.importMarket(bycounImMarketEntity.getImportMarket())
				.price(bycounImMarketEntity.getPrice())
				.percentile(bycounImMarketEntity.getPercentile())
				.build();
	}
}
