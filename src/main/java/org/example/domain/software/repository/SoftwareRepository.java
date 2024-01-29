package org.example.domain.software.repository;

import org.example.domain.software.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepository extends JpaRepository<Software,Long> {
}
