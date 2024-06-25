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
import net.kdigital.web_project.dto.CountryChartDTO;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name = "country_top5")
public class CountryChartEntity {
    
    @Id
    @Column(name = "top5_seq")
    private Long top5Seq;

    @Column(name = "hs_4digit", nullable = false)
    private String hs4digit;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "export_ranking", nullable = false)
    private int exportRanking;

    @Column(name = "export_amount", nullable = false)
    private int exportAmount;

    @Column(name = "import_ranking", nullable = false)
    private int importRanking;

    @Column(name = "import_amount", nullable = false)
    private int importAmount;

    public static CountryChartEntity toDTO(CountryChartDTO countryChartDTO) {
        return CountryChartEntity.builder()
                .top5Seq(countryChartDTO.getTop5Seq())
                .hs4digit(countryChartDTO.getHs4digit())
                .countryName(countryChartDTO.getCountryName())
                .exportRanking(countryChartDTO.getExportRanking())
                .exportAmount(countryChartDTO.getExportAmount())
                .importRanking(countryChartDTO.getImportRanking())
                .importAmount(countryChartDTO.getImportAmount())
                .build();
    }
}
