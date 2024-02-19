package org.example.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.asset.dto.AssetDto;
import org.example.domain.asset.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController //@Controller + @ResponseBody
@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Slf4j
public class AssetController {
    private final AssetService assetService;
    //dto수정2
    @GetMapping("/assets/entire") //@RequestMapping(value = "/assets", method = RequestMethod.GET)
    public List<AssetDto> getAssets(){

        return assetService.getAssets();
    }
    //dto수정2
    @GetMapping("/asset")
    public AssetDto getAsset(@RequestParam("idx") Long index){
        return assetService.getAsset(index);
    }


}
