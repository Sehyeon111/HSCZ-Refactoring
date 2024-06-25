package net.kdigital.web_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.dto.SubheadingDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "subheadings")
public class SubheadingEntity {
    
    @Id
    @Column(name = "subhead_seq")
    private Long subheadSeq;

    @Column(name = "hs_6digit")
    private String hs6digit;

    @Column(name = "hs_10digit")
    private String hs10digit;

    @Column(name = "hs_all")
    private String hsAll;

    @Column(name = "ko_description")
    private String koDescription;

    @Column(name = "eng_description")
    private String engDescription;

    /* headings테이블과 관계 설정 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hs_4digit") 
    private HeadingEntity headingEntity;

    public static SubheadingEntity toEntity(SubheadingDTO subheadingDTO, HeadingEntity headingEntity) {
        return SubheadingEntity.builder()
                .subheadSeq(subheadingDTO.getSubheadSeq())
                .hs6digit(subheadingDTO.getHs6digit())
                .hs10digit(subheadingDTO.getHs10digit())
                .hsAll(subheadingDTO.getHsAll())
                .koDescription(subheadingDTO.getKoDescription())
                .engDescription(subheadingDTO.getEngDescription())
                .headingEntity(headingEntity)
                .build();
    }
}
