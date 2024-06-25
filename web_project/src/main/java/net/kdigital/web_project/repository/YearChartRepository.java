package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.YearChartEntity;

public interface YearChartRepository extends JpaRepository<YearChartEntity, Long>{

    List<YearChartEntity> findAllByHs4digit(String hs4digit);
    
}
