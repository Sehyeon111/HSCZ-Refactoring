package net.kdigital.web_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.HeadingEntity;
import net.kdigital.web_project.entity.SubheadingEntity;

public interface SubheadingRepository extends JpaRepository<SubheadingEntity, Long>{

    List<SubheadingEntity> findAllByHeadingEntity(HeadingEntity entity);
    
}
