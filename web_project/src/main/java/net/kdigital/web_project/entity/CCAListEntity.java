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
import net.kdigital.web_project.dto.CCAListDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="ccalist") 
public class CCAListEntity {
    
    @Id
    @Column(name = "cca_num")
    private Long ccaNum;
    
    @Column(name="cca_name", nullable=false)
    private String ccaName;
    
    @Column(name="company_name", nullable=false)
    private String companyName;
    
    @Column(name="phone", nullable=false)
    private String phone;
    
    @Column(name="company_region", nullable=false)
    private String companyRegion;
    
  
    
    
    public static CCAListEntity toEntity(CCAListDTO ccaListDTO) {
        return CCAListEntity.builder()
                .ccaNum(ccaListDTO.getCcaNum())
                .ccaName(ccaListDTO.getCcaName())
                .companyName(ccaListDTO.getCompanyName())
                .phone(ccaListDTO.getPhone())
                .companyRegion(ccaListDTO.getCompanyRegion())
                .build();
    }
}
