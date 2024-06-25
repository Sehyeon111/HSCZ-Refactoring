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
import net.kdigital.web_project.dto.BycounImExPriceDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name="bycoun_imex_price")
public class BycounImExPriceEntity {

	@Id
	@Column(name="seq6")
	private Long seq6;
	
	@Column(name="country")
	private String country;
	
	@Column(name="date_year")
	private int dateYear;
	
	@Column(name="date_month")
	private String dateMonth;
	
	@Column(name="export_price")
	private int exportPrice;
	
	@Column(name="import_price")
	private int importPrice;
	
	public static BycounImExPriceEntity toEntity(BycounImExPriceDTO bycounImExPriceDTO) {
		return BycounImExPriceEntity.builder()
				.seq6(bycounImExPriceDTO.getSeq6())
				.country(bycounImExPriceDTO.getCountry())
				.dateYear(bycounImExPriceDTO.getDateYear())
				.dateMonth(bycounImExPriceDTO.getDateMonth())
				.exportPrice(bycounImExPriceDTO.getExportPrice())
				.importPrice(bycounImExPriceDTO.getImportPrice())
				.build();

}
}