package org.example.appproject_be.repository;

import org.example.appproject_be.model.Hardware;
import org.example.appproject_be.model.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepository extends JpaRepository<Software,Long> {
}
