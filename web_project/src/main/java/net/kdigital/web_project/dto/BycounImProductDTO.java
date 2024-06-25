package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.BycounImProductEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BycounImProductDTO {
	private Long seq3;
	private int dateYear;
	private String country;
	private int ranking;
	private String hscode;
	private String productName;
	private Long price;
	
	public static BycounImProductDTO toDTO(BycounImProductEntity bycounImProductEntity) {
		return BycounImProductDTO.builder()
				.seq3(bycounImProductEntity.getSeq3())
				.dateYear(bycounImProductEntity.getDateYear())
				.country(bycounImProductEntity.getCountry())
				.ranking(bycounImProductEntity.getRanking())
				.hscode(bycounImProductEntity.getHscode())
				.productName(bycounImProductEntity.getProductName())
				.price(bycounImProductEntity.getPrice())
				.build();
	}
}
