package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appproject_be.dto.AssetDto;
import org.example.appproject_be.model.Asset;
import org.example.appproject_be.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller
@RestController //@Controller + @ResponseBody
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class AssetController {
    private final AssetService assetService;
    //localhost:8080/assets
    @GetMapping("/assets") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<AssetDto> getAssets(){

        return assetService.getAssets();
    }
    //localhost:8080/assets/index
    @GetMapping("/asset")
    public AssetDto getAsset(@RequestParam("idx") Long index){
        return assetService.getAsset(index);
    }
    @PostMapping("/assets")
    public Asset saveAsset(@Valid @RequestBody Asset asset){
        return assetService.saveAsset(asset);
    }
    @PutMapping("/assets/{index}")
    public Asset updateAsset (@PathVariable Long index, @RequestBody Asset asset){
        asset.setAssetidx(index);
        return assetService.updateAsset(asset);
    }

}
