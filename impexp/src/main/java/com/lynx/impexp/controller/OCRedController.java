package com.lynx.impexp.controller;


import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.javacpp.lept.PIX;
import static org.bytedeco.javacpp.lept.pixRead;
import static org.bytedeco.javacpp.tesseract.TessBaseAPI;

@RestController
public class OCRedController {


    @PostMapping("/oc3")
    public String handleFileUpload(@RequestParam("image") MultipartFile image) {

        TessBaseAPI tessBaseAPI= new TessBaseAPI();

        if (tessBaseAPI.Init("./tessdata/", "ENG") != 0) {
            System.err.println("Could not initialize tesseract.");
        }
        byte [] bytes = null;
        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BytePointer bytePointer = new BytePointer(bytes);
        PIX pixImage = lept.pixRead(bytePointer);
        tessBaseAPI.SetImage(pixImage);

        // Get OCR result
        BytePointer text = tessBaseAPI.GetUTF8Text();
        String string = text.getString();
        System.out.println("OCR output:\n" + string);

        // Destroy used object and release memory
        tessBaseAPI.End();
        text.deallocate();
        org.bytedeco.javacpp.lept.pixDestroy(pixImage);

        return string;
    }

    @PostMapping("/oc32")
    public void tesseract(@RequestParam("image") MultipartFile image) {
        ITesseract tessAPI = new Tesseract1();
        tessAPI.setDatapath("C:\\Users\\filip\\Desktop\\tessdata");
        tessAPI.setLanguage("eng");

        File file = new File(image.getName()+".jpg");

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

        try {
            String result =  tessAPI.doOCR(file);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
