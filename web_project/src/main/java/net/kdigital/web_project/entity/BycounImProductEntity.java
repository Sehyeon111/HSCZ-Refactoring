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
import net.kdigital.web_project.dto.BycounImProductDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="BYCOUN_IMPORT_PRODUCT_TOP10")
public class BycounImProductEntity {
	@Id
	@Column(name="seq3")
	private Long seq3;
	
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
	
	public static BycounImProductEntity toEntity(BycounImProductDTO bycounImProductDTO) {
		return BycounImProductEntity.builder()
				.seq3(bycounImProductDTO.getSeq3())
				.dateYear(bycounImProductDTO.getDateYear())
				.country(bycounImProductDTO.getCountry())
				.ranking(bycounImProductDTO.getRanking())
				.hscode(bycounImProductDTO.getHscode())
				.productName(bycounImProductDTO.getProductName())
				.price(bycounImProductDTO.getPrice())
				.build();
	}
}
