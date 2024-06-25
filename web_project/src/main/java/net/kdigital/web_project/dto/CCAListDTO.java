package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.CCAListEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CCAListDTO {
    private Long ccaNum;
    private String ccaName;
    private String companyName;
    private String phone;
    private String companyRegion;

    // // 생성자 (페이징을 위한 생성자, ccaList에서 사용할 내용으로 추림)
    // public CCAListDTO(Long ccaNum, String ccaName,String phone, String
    // companyName,String companyRegion) {
    // super();
    // this.ccaNum = ccaNum;
    // this.ccaName = ccaName;
    // this.companyName = companyName;
    // this.phone= phone;
    // this.companyRegion = companyRegion;
    //
    // }

    // Entity 받아서 --> DTO 반환
    public static CCAListDTO toDTO(CCAListEntity ccaListEntity) {
        return CCAListDTO.builder()
                .ccaNum(ccaListEntity.getCcaNum())
                .ccaName(ccaListEntity.getCcaName())
                .companyName(ccaListEntity.getCompanyName())
                .phone(ccaListEntity.getPhone())
                .companyRegion(ccaListEntity.getCompanyRegion())

                .build();
    }
}
