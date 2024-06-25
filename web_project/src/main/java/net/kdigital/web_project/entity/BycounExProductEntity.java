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
import net.kdigital.web_project.dto.BycounExProductDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="bycoun_export_product_top10")
public class BycounExProductEntity {
	
	@Id
	@Column(name="seq4")
	private Long seq4;
	
	@Column(name="date_year")
	private int dateYear;
	
	@Column(name="country")
	private String country;
	
	@Column(name="ranking")
	private int ranking;
	
	@Column(name="hscode")
	private String hscode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="price")
	private Long price;
	
	public static BycounExProductEntity toEntity(BycounExProductDTO bycounExProductDTO) {
		return BycounExProductEntity.builder()
				.seq4(bycounExProductDTO.getSeq4())
				.dateYear(bycounExProductDTO.getDateYear())
				.country(bycounExProductDTO.getCountry())
				.ranking(bycounExProductDTO.getRanking())
				.hscode(bycounExProductDTO.getHscode())
				.productName(bycounExProductDTO.getProductName())
				.price(bycounExProductDTO.getPrice())
				.build();
	}
}
