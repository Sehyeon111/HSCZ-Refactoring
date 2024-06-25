package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.BycounImMarketEntity;

public interface BycounImMarketRepository extends JpaRepository<BycounImMarketEntity, Long> {
	List<BycounImMarketEntity> findAllByCountryOrderBySeq5Desc(String country);
}
