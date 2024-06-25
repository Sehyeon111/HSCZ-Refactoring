package net.kdigital.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.BycounImProductEntity;

public interface BycounImproductRepository extends JpaRepository<BycounImProductEntity, Long> {

    // List<BycounImProductEntity> findAllByCountryOrderBySeq4Desc(String country);
}
