package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.BycounImExPriceEntity;

public interface BycounImExPriceRepository extends JpaRepository<BycounImExPriceEntity, Long> {
	List<BycounImExPriceEntity> findAllByCountryOrderBySeq6Desc(String country);
}