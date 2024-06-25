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
import net.kdigital.web_project.dto.BycounIeProductDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="bycoun_imex_product_top5")
public class BycounIeProductEntity {
	
	@Id
	@Column(name="seq8")
	private Long seq8;
	
	@Column(name="month_year")
	private Long monthYear;
	
	@Column(name="country")
	private String country;
	
	@Column(name="HSCODE")
	private String hscode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="exportrate")
	private Double exportrate;
	
	@Column(name="importrate")
	private Double importrate;
	
	public static BycounIeProductEntity toEntity(BycounIeProductDTO bycounIeProductDTO) {
		return BycounIeProductEntity.builder()
				.seq8(bycounIeProductDTO.getSeq8())
				.monthYear(bycounIeProductDTO.getMonthYear())
				.country(bycounIeProductDTO.getCountry())
				.hscode(bycounIeProductDTO.getHscode())
				.productName(bycounIeProductDTO.getProductName())
				.exportrate(bycounIeProductDTO.getExportrate())
				.importrate(bycounIeProductDTO.getImportrate())
				.build();
	}

}
