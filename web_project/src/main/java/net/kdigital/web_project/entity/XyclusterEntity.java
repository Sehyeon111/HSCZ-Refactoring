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
import net.kdigital.web_project.dto.XyclusterDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="IMPORT_EXPORT_PRODUCT_TOP10")
public class XyclusterEntity {
	
	
	@Id
	@Column(name="seq1")
	private Long seq1;
	
	@Column(name="import_export", nullable=false)
	private String importexport;
	
	@Column(name="DATE_YEAR", nullable=false)
	private Long dateYear;
	
	@Column(name="hs_4digit", nullable=false)
	private String hs4digit;
	
	@Column(name="product_name", nullable=false)
	private String productName;
	
	@Column(name="price", nullable=false)
	private Long price;
	
	//DTO를 전달받아 Entity로 반환
	public static XyclusterEntity toEntity(XyclusterDTO xyclusterDTO) {
		return XyclusterEntity.builder()
				.seq1(xyclusterDTO.getSeq1())
				.importexport(xyclusterDTO.getImportexport())
				.dateYear(xyclusterDTO.getDateYear())
				.hs4digit(xyclusterDTO.getHs4digit())
				.productName(xyclusterDTO.getProductName())
				.price(xyclusterDTO.getPrice())
				.build();
	}//end entity
	
}//end class
