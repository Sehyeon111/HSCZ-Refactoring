package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.SubheadingEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SubheadingDTO {
    
    private Long subheadSeq;
    private String hs4digit;
    private String hs6digit;
    private String hs10digit;
    private String hsAll;
    private String koDescription;
    private String engDescription;
    
    public static SubheadingDTO toDTO(SubheadingEntity subheadingEntity, String hs4digit) {
        return SubheadingDTO.builder()
                .subheadSeq(subheadingEntity.getSubheadSeq())
                .hs4digit(hs4digit)
                .hs6digit(subheadingEntity.getHs6digit())
                .hs10digit(subheadingEntity.getHs10digit())
                .hsAll(subheadingEntity.getHsAll())
                .koDescription(subheadingEntity.getKoDescription())
                .engDescription(subheadingEntity.getEngDescription())
                .build();
    }

}
