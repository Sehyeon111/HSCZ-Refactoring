package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.BycounIeProductEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BycounIeProductDTO {
	private Long seq8;
	private Long monthYear;
	private String country;
	private String hscode;
	private String productName;
	private Double exportrate;
	private Double importrate;
	
	public static BycounIeProductDTO toDTO(BycounIeProductEntity bycounIeProductEntity) {
		return BycounIeProductDTO.builder()
				.seq8(bycounIeProductEntity.getSeq8())
				.monthYear(bycounIeProductEntity.getMonthYear())
				.country(bycounIeProductEntity.getCountry())
				.hscode(bycounIeProductEntity.getHscode())
				.productName(bycounIeProductEntity.getProductName())
				.exportrate(bycounIeProductEntity.getExportrate())
				.importrate(bycounIeProductEntity.getImportrate())
				.build();
	}//end dto
}//end class
