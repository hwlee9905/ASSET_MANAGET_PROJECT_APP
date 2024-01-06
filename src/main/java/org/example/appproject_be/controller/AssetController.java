package org.example.appproject_be.controller;

import org.example.appproject_be.model.Asset;
import org.example.appproject_be.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController //@Controller + @ResponseBody
public class AssetController {
    @Autowired
    private AssetService assetService;
    //localhost:8080/assets
    @GetMapping("/assets") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<Asset> getAssets(){
        return assetService.getAssets();
    }
    //localhost:8080/assets/index
    @GetMapping("/assets/{index}")
    public String getAsset(@PathVariable("index") String index){
        return "Fetching the asset details for the index " + index;
    }
    @PostMapping("/assets")
    public Asset saveAsset(@RequestBody Asset asset){

        return assetService.saveAsset(asset);
    }
    @PutMapping("/assets/{index}")
    public Asset updateAsset (@PathVariable Long index, @RequestBody Asset asset){
        System.out.println("updating the asset data for the index " + index);
        return asset;
    }
    //localhost:8080/assets?index=index123
    @DeleteMapping("/assets")
    public String deleteAsset (@RequestParam("assetCode") String assetCode){
        return "Deleting the asset details for the assetCode " + assetCode;
    }
}
