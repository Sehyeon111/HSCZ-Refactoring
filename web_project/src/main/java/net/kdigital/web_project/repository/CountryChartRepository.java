package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.kdigital.web_project.entity.CountryChartEntity;

public interface CountryChartRepository extends JpaRepository<CountryChartEntity , Long>{

    @Query("SELECT c FROM CountryChartEntity c WHERE c.hs4digit = :hs4digit ORDER BY c.exportRanking ASC FETCH FIRST 5 ROWS ONLY")
    List<CountryChartEntity> findTop5ExportByHs4digit(@Param("hs4digit") String hs4digit);

    @Query("SELECT c FROM CountryChartEntity c WHERE c.hs4digit = :hs4digit ORDER BY c.importRanking ASC FETCH FIRST 5 ROWS ONLY")
    List<CountryChartEntity> findTop5ImportByHs4digit(@Param("hs4digit") String hs4digit);

}
