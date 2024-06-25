package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.BycounExProductEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BycounExProductDTO {

	private Long seq4;
	private int dateYear;
	private String country;
	private int ranking;
	private String hscode;
	private String productName;
	private Long price;
	
	public static BycounExProductDTO toDTO(BycounExProductEntity bycounExProductEntity) {
		return BycounExProductDTO.builder()
				.seq4(bycounExProductEntity.getSeq4())
				.dateYear(bycounExProductEntity.getDateYear())
				.country(bycounExProductEntity.getCountry())
				.ranking(bycounExProductEntity.getRanking())
				.hscode(bycounExProductEntity.getHscode())
				.productName(bycounExProductEntity.getProductName())
				.price(bycounExProductEntity.getPrice())
				.build();
	}
}
