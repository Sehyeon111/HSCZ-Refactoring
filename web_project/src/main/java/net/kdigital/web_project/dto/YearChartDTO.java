package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.YearChartEntity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class YearChartDTO {
    private Long yearSeq;
    private String hs4digit;
    private String imexYear;
    private Long exportAmount;
    private Long importAmount;

    public static YearChartDTO toDTO(YearChartEntity yearChartEntity) {
        return YearChartDTO.builder()
                .yearSeq(yearChartEntity.getYearSeq())
                .hs4digit(yearChartEntity.getHs4digit())
                .imexYear(yearChartEntity.getImexYear())
                .exportAmount(yearChartEntity.getExportAmount())
                .importAmount(yearChartEntity.getImportAmount())
                .build();
    }
}
