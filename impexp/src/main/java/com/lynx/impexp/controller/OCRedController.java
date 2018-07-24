package com.lynx.impexp.controller;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class OCRedController {

    @PostMapping("/oc3")
    public String tesseract(@RequestParam("image") MultipartFile image) {

        ITesseract tessAPI = new Tesseract1();

        File tessDataFolder = LoadLibs.extractTessResources("tessdata");

        tessAPI.setDatapath(tessDataFolder.getAbsolutePath());

        File file = new File(image.getOriginalFilename());

        byte [] bytes = null;
        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.writeByteArrayToFile(file, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            result =  tessAPI.doOCR(file);
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        return result;
    }
}
