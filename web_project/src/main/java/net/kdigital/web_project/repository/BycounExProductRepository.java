package net.kdigital.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.BycounExProductEntity;

public interface BycounExProductRepository extends JpaRepository<BycounExProductEntity, Long> {

    // List<BycounExProductEntity> findAllByCountryOrderBySeq4Desc(String country);
    //
}
