package net.kdigital.web_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.SubheadingDTO;
import net.kdigital.web_project.entity.HeadingEntity;
import net.kdigital.web_project.entity.SubheadingEntity;
import net.kdigital.web_project.repository.HeadingRepository;
import net.kdigital.web_project.repository.SubheadingRepository;

@Service
@RequiredArgsConstructor
public class SubheadingService {

    private final SubheadingRepository subheadingRepository;
    private final HeadingRepository headingRepository;

    public List<SubheadingDTO> selectDetail(String hs4digit) {

        // 전달받은 hscode 4자리로 headingEntity찾기
        HeadingEntity entity = headingRepository.findById(hs4digit).get();
        // 찾은 headingEntity로 해당하는 subheadingEntity 모두 찾기
        List<SubheadingEntity> subheadingEntityList = subheadingRepository.findAllByHeadingEntity(entity);

        // EntityList -> DTOList
        List<SubheadingDTO> subheadingDTOList = new ArrayList<>();
        subheadingEntityList.forEach((item) -> subheadingDTOList.add(SubheadingDTO.toDTO(item, hs4digit)));

        return subheadingDTOList;
    }

    public boolean checkOrigin(String hs4digit) {

        boolean result = false;

        // 원산지 표시대상에 포함되는 hs code 리스트 생성
        List<String> originCheckList = new ArrayList<>(List.of("3916", "3917", "3918", "3919", "3920", "3921", "3922",
                "3923", "3924", "3925", "3926", "4006", "4007", "4008", "4009", "4010", "4011", "4012", "4013", "4014",
                "4015", "4016", "4017", "8407", "8408", "8409", "8413", "8414", "8415", "8416", "8417", "8418", "8419",
                "8421", "8422", "8423", "8424", "8425", "8431", "8432", "8433", "8434", "8435", "8436", "8437", "8438",
                "8440", "8441", "8442", "8443", "8448", "8450", "8451", "8452", "8453", "8456", "8465", "8466", "8467",
                "8468", "8469", "8470", "8471", "8472", "8473", "8476", "8479", "8481", "8482", "8483", "8484", "8485",
                "8487", "8501", "8502", "8503", "8504", "8505", "8506", "8507", "8508", "8509", "8510", "8511", "8512",
                "8513", "8514", "8515", "8516", "8517", "8518", "8519", "8521", "8522", "8523", "8524", "8525", "8526",
                "8527", "8528", "8529", "8531", "8532", "8533", "8534", "8535", "8536", "8537", "8538", "8539", "8540",
                "8541", "8542", "8543", "8544", "8545", "8546", "8547", "8548", "8549"));

        for (int i = 0; i < originCheckList.size(); ++i) {
            if (originCheckList.get(i).equals(hs4digit)) {
                result = true;
                break;
            }
        }

        return result;
    }

}
