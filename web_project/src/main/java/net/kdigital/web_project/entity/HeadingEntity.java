package net.kdigital.web_project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.kdigital.web_project.dto.HeadingDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "headings")
public class HeadingEntity {

    @Id
    @Column(name = "hs_4digit")
    private String hs4digit;

    @Column(name = "ko_description", nullable = false)
    private String koDescription;

    @Column(name = "eng_description", nullable = false)
    private String engDescription;

    // subheadings테이블과 관계 설정
    @OneToMany(
		mappedBy = "headingEntity", 
		cascade = CascadeType.REMOVE,
		orphanRemoval = true,
		fetch = FetchType.LAZY
		)
	
	@OrderBy("subhead_seq asc")
	private List<SubheadingEntity> subheadingEntity = new ArrayList<>();

    public static HeadingEntity toEntity(HeadingDTO headingDTO) {
        return HeadingEntity.builder()
                .hs4digit(headingDTO.getHs4digit())
                .koDescription(headingDTO.getKoDescription())
                .engDescription(headingDTO.getEngDescription())
                .build();
    };
}
