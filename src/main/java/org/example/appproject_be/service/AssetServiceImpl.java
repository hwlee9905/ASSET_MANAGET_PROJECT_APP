package org.example.appproject_be.service;

import org.example.appproject_be.model.Asset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService{
    private static List<Asset> list = new ArrayList<>();
    static {
        Asset as = new Asset();
        as.setAssetName("asset123");
        as.setAssetType("N");
        as.setSn("NAWE-1234");
        as.setDept("전략운영팀");
        as.setManufacturer("SAMSUNG");
        as.setIndex(1L);
        list.add(as);

        Asset as1 = new Asset();
        as.setAssetName("asset223");
        as.setAssetType("N");
        as.setSn("QWEV-1234");
        as.setDept("전략채널팀");
        as.setManufacturer("SAMSUNG");
        as.setIndex(1L);
        list.add(as1);
    }
    @Override
    public List<Asset> getAssets() {
        return list;
    }
}
