/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unityhealth.model.IngredientsModel;
import com.unityhealth.model.Model;
import com.unityhealth.model.ProductModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 *
 * @author Sameer
 */
public class ExcelToJsonConverter {
    
    
    public static void main(String arg[]){
        
          

   

   // logger.info("uploadExcel method");
   
        
        FileInputStream inp;
        try {
            inp = new FileInputStream("E:\\BioMedica\\SampleProductInputFormat_03022017.xlsx");
        
        Workbook workbook = WorkbookFactory.create(inp);

// Get the first Sheet.
for(int i =0; i< workbook.getNumberOfSheets(); i++ )       {
  
    Sheet sheet = workbook.getSheetAt(i);
    if( i == 0){
        sheetReader(sheet ,"product");
    }else{
        sheetReader(sheet ,"ingredients");
    }
    
   // productsList.add(product);
    System.out.println("------------------------------------------------------------------------------- sheet number ------------------------------------------------------------------------------------------------" + i+1);
}



 } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void  sheetReader(Sheet sheet,String jsonModelType){
         ObjectMapper mapper = new ObjectMapper();
          List<ProductModel> productsList = new ArrayList<ProductModel>();
           List<IngredientsModel> iModelList = new ArrayList<IngredientsModel>();
          for ( Iterator<Row> rowsIT = sheet.rowIterator(); rowsIT.hasNext(); )
    {
        Row row = rowsIT.next();
        Model jsonModel;
        if(jsonModelType.equals("product")){
              jsonModel = new ProductModel();
              ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
        }
        else{
             jsonModel = new IngredientsModel();
        }
     //    System.out.println(row.getRowNum()+1);
//        JSONObject jRow = new JSONObject();

        // Iterate through the cells.
  //      JSONArray cells = new JSONArray();
        for ( Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); )
        {//System.out.println("com.unityhealth.ExcelToJsonConverter.sheetReader()---->>>>>"+ row.getRowNum());
            Cell cell = cellsIT.next();
           
           // cell.getStringCellValue();
           switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                   // System.out.println(cell.getRichStringCellValue().getString());
                     
                     switch (cell.getColumnIndex()) {
                         case 0: 
                             if( jsonModel instanceof ProductModel){
                                 //System.out.println(row.getRowNum()+1);
                              //  ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
                             }else{
                                 ((IngredientsModel)jsonModel).setEquivalentValue(cell.getRichStringCellValue().getString());
                             }
                             break;
                             case 1: 
                             if( jsonModel instanceof ProductModel){
                                 ((ProductModel) jsonModel).setOptionsSize(cell.getRichStringCellValue().getString());
                                 
                             }else{
                                 //((IngredientsModel)jsonModel).setEquivalentValue(cell.getRichStringCellValue().getString());
                             }
                             break;
                             case 2: 
                             if( jsonModel instanceof ProductModel){
                                 ((ProductModel) jsonModel).setProductAustl(cell.getRichStringCellValue().getString());
                                 
                             }else{
                                 ((IngredientsModel)jsonModel).setIngredientName(cell.getRichStringCellValue().getString());
                             }
                             break;
                             case 3: 
                             if( jsonModel instanceof ProductModel){
                                 ((ProductModel) jsonModel).setProductBrand(cell.getRichStringCellValue().getString());
                                 
                             }else{
                                 //((IngredientsModel)jsonModel).setQuantity(cell.getRichStringCellValue().getString());
                             }
                             break;
                             case 4: 
                             if( jsonModel instanceof ProductModel){
                                 ((ProductModel) jsonModel).setProductDosage(cell.getRichStringCellValue().getString());
                                 
                             }else{
                                // ((IngredientsModel)jsonModel).setProductID(cell.getRichStringCellValue().getString());
                            
                             }
                             break;
                             case 5: 
                             if( jsonModel instanceof ProductModel){
                                // ((ProductModel) jsonModel).setProductWarning(cell.getRichStringCellValue().getString());
                                
                             }else{
                                 ((IngredientsModel)jsonModel).setQuantity(cell.getRichStringCellValue().getString());
                             }
                             break;
                             case 6: 
                             if( jsonModel instanceof ProductModel){
                                ((ProductModel) jsonModel).setProductName(cell.getRichStringCellValue().getString());
                                
                             } else{
                                 //System.out.println("what will it print?? " + cell.getRichStringCellValue().getString());
                                 ((IngredientsModel)jsonModel).setProductID(cell.getRichStringCellValue().getString());
                            
                             }
                             break;
                             case 7: 
                             if( jsonModel instanceof ProductModel){
                                 ((ProductModel) jsonModel).setProductWarning(cell.getRichStringCellValue().getString());
                                
                             }
                             break;
                     }
                   
                    break;
                    
               case Cell.CELL_TYPE_NUMERIC:
                    switch (cell.getColumnIndex()) {
                         case 6: //System.out.println("what will it print?? " + cell.getNumericCellValue());
                         String productID = String.valueOf(cell.getNumericCellValue());
                         //System.out.println(cell.getNumericCellValue());
                                ((IngredientsModel)jsonModel).setProductID(productID.substring(0, productID.indexOf(".")));
                                    break;
                    }
//                    if (DateUtil.isCellDateFormatted(cell)) {
//                        System.out.println(cell.getDateCellValue());
//                        System.out.println("date");
//                    } else {
//                        System.out.println(cell.getNumericCellValue());
//                    }
//                    break;
//                case Cell.CELL_TYPE_BOOLEAN:
//                    System.out.println(cell.getBooleanCellValue());
//                    System.out.println("boolean");
//                    break;
//                case Cell.CELL_TYPE_FORMULA:
//                    System.out.println(cell.getCellFormula());
//                    System.out.println("cellformula");
//                    break;
//                default:
//                    System.out.println();
            }
     //       cells.put( cell.getStringCellValue() );
        }
             
              if( jsonModel instanceof ProductModel){
                  productsList.add((ProductModel) jsonModel);
              }else{
                  iModelList.add((IngredientsModel)jsonModel);
              }
              
   //     jRow.put( "cell", cells );
    //    rows.put( jRow );
    }

//    System.out.println("productsList: "+products);
//    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//      Date date = new Date();
//      String location = dateFormat.format(date);
//      System.out.println("productsList final: "+products);
//
//
//
        // Convert object to JSON string
        String jsonInString;
        try {
            jsonInString = mapper.writeValueAsString(productsList); //iModelList
             
              // Convert object to JSON string and pretty print
        jsonInString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(productsList);
        System.out.println("JsonInString " +jsonInString);
          jsonInString = mapper.writeValueAsString(iModelList); //iModelList
          
              // Convert object to JSON string and pretty print
        jsonInString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(iModelList);
           System.out.println("JsonInString " +jsonInString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
       

       

        try {
            if(jsonModelType.equals("product")){
               mapper.writeValue(new File("E:\\BioMedica\\products.json"), productsList);
               
        }
        else{
             mapper.writeValue(new File("E:\\BioMedica\\ingredients.json"), iModelList);
        }
          
            
        } catch (IOException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    void populateProductModel(){
        
    }
    
}
