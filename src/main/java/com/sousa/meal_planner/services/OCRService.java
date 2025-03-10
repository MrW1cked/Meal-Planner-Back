package com.sousa.meal_planner.services;

import lombok.RequiredArgsConstructor;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class OCRService {
    public String extractTextFromImage(String imagePath) {
        File image = new File(imagePath);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        try {
            return tesseract.doOCR(image);
        } catch (TesseractException e) {
            return "Erro ao processar OCR: " + e.getMessage();
        }
    }
}
