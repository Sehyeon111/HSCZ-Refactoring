package net.kdigital.web_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.entity.CountryChartEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CountryChartDTO {
    private Long top5Seq;
    private String hs4digit;
    private String countryName;
    private int exportRanking;
    private int exportAmount;
    private int importRanking;
    private int importAmount;

    public static CountryChartDTO toDTO(CountryChartEntity countryChartEntity) {
        return CountryChartDTO.builder()
                .top5Seq(countryChartEntity.getTop5Seq())
                .hs4digit(countryChartEntity.getHs4digit())
                .countryName(countryChartEntity.getCountryName())
                .exportRanking(countryChartEntity.getExportRanking())
                .exportAmount(countryChartEntity.getExportAmount())
                .importRanking(countryChartEntity.getImportRanking())
                .importAmount(countryChartEntity.getImportAmount())
                .build();
    }
}
