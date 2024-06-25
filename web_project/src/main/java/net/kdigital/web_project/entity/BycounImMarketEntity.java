package net.kdigital.web_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.dto.BycounImMarketDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="bycoun_import_market_top10")
public class BycounImMarketEntity {
	
	@Id
	@Column(name="seq5")
	private Long seq5;
	
	@Column(name="country")
	private String country;
	
	@Column(name="date_year")
	private int dateYear;
	
	@Column(name="ranking")
	private int ranking;
	
	@Column(name="import_market")
	private String importMarket;
	
	@Column(name="price")
	private Long price;
	
	@Column(name="percentile")
	private String percentile;
	
	
	public static BycounImMarketEntity toEntity(BycounImMarketDTO bycounImMarketDTO) {
		return BycounImMarketEntity.builder()
				.seq5(bycounImMarketDTO.getSeq5())
				.country(bycounImMarketDTO.getCountry())
				.dateYear(bycounImMarketDTO.getDateYear())
				.ranking(bycounImMarketDTO.getRanking())
				.importMarket(bycounImMarketDTO.getImportMarket())
				.price(bycounImMarketDTO.getPrice())
				.percentile(bycounImMarketDTO.getPercentile())
				.build();
	}
}
