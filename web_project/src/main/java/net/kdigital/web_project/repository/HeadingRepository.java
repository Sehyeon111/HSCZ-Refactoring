package net.kdigital.web_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.kdigital.web_project.entity.HeadingEntity;

public interface HeadingRepository extends JpaRepository<HeadingEntity, String>{
    
}
