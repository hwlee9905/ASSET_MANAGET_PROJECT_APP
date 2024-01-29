package org.example.domain.asset.repository;

import org.example.domain.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset,Long> {
}
