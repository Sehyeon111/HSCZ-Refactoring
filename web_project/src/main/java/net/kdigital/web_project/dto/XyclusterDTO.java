package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.XyclusterEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class XyclusterDTO {
	private Long seq1;
	private String importexport;
	private Long dateYear;
	private String hs4digit;
	private String productName;
	private Long price;
	
	// entity -> dto
	public static XyclusterDTO toDTO(XyclusterEntity xyclusterEntity) {
		return XyclusterDTO.builder()
				.seq1(xyclusterEntity.getSeq1())
				.importexport(xyclusterEntity.getImportexport())
				.dateYear(xyclusterEntity.getDateYear())
				.hs4digit(xyclusterEntity.getHs4digit())
				.productName(xyclusterEntity.getProductName())
				.price(xyclusterEntity.getPrice())
				.build();
	}//end toDTO
}//end class
