/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspose.slides.examples.Slides.Shapes;

import com.aspose.slides.examples.Utils;
import java.awt.Color;

import com.aspose.slides.FillType;
import com.aspose.slides.IShape;
import com.aspose.slides.ISlide;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.slides.ShapeType;
import com.aspose.slides.examples.Utils;

public class ImageAsEMF {
    
public static void main(String[] args) {

    String dataDir = Utils.getDataDir(ImageAsEMF.class);
     Workbook book = new Workbook(dataDir + "chart.xlsx");
     Worksheet sheet = book.getWorksheets().get(0); //or get_Item()
     com.aspose.cells.ImageOrPrintOptions options = new com.aspose.cells.ImageOrPrintOptions();
     options.HorizontalResolution = 200;
     options.VerticalResolution = 200;
     options.ImageFormat = ImageFormat.Emf;// <- check this

     //Save the workbook to stream
     SheetRender sr = new SheetRender(sheet, options);
     Presentation pres = new Presentation();
     pres.getSlides().removeAt(0);

     String EmfSheetName="";
     for (int j = 0; j < sr.PageCount; j++)
     {

         EmfSheetName=dataDir + "test" + sheet.Name + " Page" + (j + 1) + ".out.emf";
         sr.toImage(j, EmfSheetName);
      
         Path path = Paths.get(EmfSheetName);
         byte[] bytes = Files.readAllBytes(path);
         IPPImage emfImage = pres.getImages().addImage(bytes);
         ISlide slide= pres.getSlides().addEmptySlide(pres.getLayoutSlides().getByType(SlideLayoutType.Blank));
         IShape m = slide.getShapes().addPictureFrame(ShapeType.Rectangle, 0, 0, 
           (float)pres.getSlideSize().getSize().getWidth(), (float)pres.getSlideSize().getSize().getHeight(), emfImage);
    
     }
   
     }
  
}