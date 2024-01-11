package org.example.appproject_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class AssetController {
    private final AssetService assetService;
    //localhost:8080/assets
    @GetMapping("/assets") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<Asset> getAssets(){
        return assetService.getAssets();
    }
    //localhost:8080/assets/index
    @GetMapping("/assets/{index}")
    public Asset getAsset(@PathVariable Long index){
        return assetService.getAsset(index);
    }
    @PostMapping("/assets")
    public Asset saveAsset(@Valid @RequestBody Asset asset){
        return assetService.saveAsset(asset);
    }
    @PutMapping("/assets/{index}")
    public Asset updateAsset (@PathVariable Long index, @RequestBody Asset asset){
        asset.setIdx(index);
        return assetService.updateAsset(asset);
    }
    //localhost:8080/assets?index=index123
    @DeleteMapping("/assets")
    public void deleteAsset (@RequestParam("idx") Long idx){
        assetService.deleteAsset(idx);
//        return "Deleting the asset details for the assetCode " + assetCode;
    }
}
