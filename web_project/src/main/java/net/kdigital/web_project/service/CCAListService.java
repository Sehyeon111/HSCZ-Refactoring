package net.kdigital.web_project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.kdigital.web_project.dto.CCAListDTO;
import net.kdigital.web_project.entity.CCAListEntity;
import net.kdigital.web_project.repository.CCAListRepository;

@Service
@RequiredArgsConstructor
public class CCAListService {
    @Value("${user.board.pageLimit}")
    int pageLimit;
    
    private final CCAListRepository ccaListRepository;

    public Page<CCAListDTO> findAllCCABySearch(Pageable pageable, String searchItem, String searchWord) {
        int page = pageable.getPageNumber() - 1; 

        Page<CCAListEntity> entityList = null;

        entityList = ccaListRepository.findAllCCAByRegion(
                searchItem,
                searchWord,
                PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "cca_num")));

        Page<CCAListDTO> dtoList = entityList.map(cca -> 
            new CCAListDTO(
                    cca.getCcaNum(),
                    cca.getCcaName(),
                    cca.getCompanyName(),
                    cca.getPhone(),
                    cca.getCompanyRegion()
                )
        );
        return dtoList;
    }
}

