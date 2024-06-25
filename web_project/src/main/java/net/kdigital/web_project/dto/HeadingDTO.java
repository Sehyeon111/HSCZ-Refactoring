package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.HeadingEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class HeadingDTO {
    private String hs4digit;
    private String koDescription;
    private String engDescription;

    public static HeadingDTO toDTO(HeadingEntity headingEntity){
        return HeadingDTO.builder()
                .hs4digit(headingEntity.getHs4digit())
                .koDescription(headingEntity.getKoDescription())
                .engDescription(headingEntity.getEngDescription())
                .build();
    };
}
