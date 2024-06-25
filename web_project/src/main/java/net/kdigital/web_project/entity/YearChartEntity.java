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
import net.kdigital.web_project.dto.YearChartDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "year_imex5")
public class YearChartEntity {

    @Id
    @Column(name = "year_seq")
    private Long yearSeq;

    @Column(name = "hs_4digit")
    private String hs4digit;

    @Column(name = "imex_year")
    private String imexYear;

    @Column(name = "export_amount")
    private Long exportAmount;

    @Column(name = "import_amount")
    private Long importAmount;

    public static YearChartEntity toEntity(YearChartDTO yearChartDTO) {
        return YearChartEntity.builder()
                .yearSeq(yearChartDTO.getYearSeq())
                .hs4digit(yearChartDTO.getHs4digit())
                .imexYear(yearChartDTO.getImexYear())
                .exportAmount(yearChartDTO.getExportAmount())
                .importAmount(yearChartDTO.getImportAmount())
                .build();
    }
}
