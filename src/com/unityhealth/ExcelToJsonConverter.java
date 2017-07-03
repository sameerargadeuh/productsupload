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
import com.unityhealth.model.ProductImageModel;
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
            inp = new FileInputStream("E:\\sanofi\\sanofinaturesown.xlsx");
        
        Workbook workbook = WorkbookFactory.create(inp);

// Get the first Sheet.
for(int i =0; i< workbook.getNumberOfSheets(); i++ )       {
  
    Sheet sheet = workbook.getSheetAt(i);
    if( i == 0){
        sheetReader(sheet ,"product");
    }else if( i == 1){
        sheetReader(sheet ,"ingredients");
    }else if( i == 2){
        sheetReader(sheet ,"images");
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
           
           List<ProductImageModel> imageModellList = new ArrayList<ProductImageModel>();
          for ( Iterator<Row> rowsIT = sheet.rowIterator(); rowsIT.hasNext(); )
    {
        Row row = rowsIT.next();
        Model jsonModel = null;
        if(jsonModelType.equals("product")){
              jsonModel = new ProductModel();
              ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
        }
        else if(jsonModelType.equals("ingredients")){
             jsonModel = new IngredientsModel();
        } else if(jsonModelType.equals("images")){
             jsonModel = new ProductImageModel();
        }
     //    System.out.println(row.getRowNum()+1);
//        JSONObject jRow = new JSONObject();

        // Iterate through the cells.
  //      JSONArray cells = new JSONArray();
        for ( Iterator<Cell> cellsIT = row.cellIterator(); cellsIT.hasNext(); )
        {//System.out.println("com.unityhealth.ExcelToJsonConverter.sheetReader()---->>>>>"+ row.getRowNum());
            Cell cell = cellsIT.next();
           cell.setCellType(Cell.CELL_TYPE_STRING);
           // cell.getStringCellValue();
           switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                   // System.out.println(cell.getRichStringCellValue().getString());
                     if( jsonModel instanceof ProductModel){ 
                     switch (cell.getColumnIndex()) {
                         case 0: 
                             // product id
                                 //System.out.println(row.getRowNum()+1);
                              //  ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
                              ((ProductModel) jsonModel).setProductID(String.valueOf(cell.getRichStringCellValue().getString()));
                            
                             break;
                             case 1: 
                           //optionsize
                                 ((ProductModel) jsonModel).setOptionsSize(cell.getRichStringCellValue().getString());
                                 
                            
                             break;
                             case 2: 
                            //austl
                                 ((ProductModel) jsonModel).setProductAustl(cell.getRichStringCellValue().getString());
                                 
                            
                             break;
                             case 3: 
                             // dosage
                                // ((ProductModel) jsonModel).setProductBrand(cell.getRichStringCellValue().getString());
                                  ((ProductModel) jsonModel).setProductDosage(cell.getRichStringCellValue().getString());
                             
                             break;
                             case 4: 
                             // name
                                ((ProductModel) jsonModel).setProductName(cell.getRichStringCellValue().getString());
                                 
                             
                             break;
                             case 5: 
                             //note
                                // ((ProductModel) jsonModel).setProductWarning(cell.getRichStringCellValue().getString());
                                 ((ProductModel) jsonModel).setProductNote(cell.getRichStringCellValue().getString());
                                
                            
                             break;
                             case 6: 
                            // indication
                                ((ProductModel) jsonModel).setProductIndications(cell.getRichStringCellValue().getString());
                                
                            
                             break;
                             case 7: 
                            //warning
                                 ((ProductModel) jsonModel).setProductWarning(cell.getRichStringCellValue().getString());
                                
                             
                             break;
                     }
                     }
                     if( jsonModel instanceof IngredientsModel){ 
                     switch (cell.getColumnIndex()) {
                         case 0: 
                             // product id
                                 //System.out.println(row.getRowNum()+1);
                              //  ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
                             
                               //  ((IngredientsModel)jsonModel).setEquivalentValue(cell.getRichStringCellValue().getString());
                             
                             break;
                             case 1: // name
                             
                                 //((IngredientsModel)jsonModel).setEquivalentValue(cell.getRichStringCellValue().getString());
                                    ((IngredientsModel)jsonModel).setIngredientName(cell.getRichStringCellValue().getString());
                             break;
                             case 2: // product id
                             
                                 ((IngredientsModel)jsonModel).setProductID(cell.getRichStringCellValue().getString());
                             
                             break;
                             case 3: // sceintific
                            
                                 //((IngredientsModel)jsonModel).setQuantity(cell.getRichStringCellValue().getString());
                                 ((IngredientsModel)jsonModel).setIngredientScientific(cell.getRichStringCellValue().getString());
                             
                             break;
                             case 4: // order number
                            
                                // ((IngredientsModel)jsonModel).setProductID(cell.getRichStringCellValue().getString());
                            ((IngredientsModel)jsonModel).setOrderNumber(cell.getRichStringCellValue().getString());
                            
                             break;
                             case 5: //
                            
                                 ((IngredientsModel)jsonModel).setQuantity(cell.getRichStringCellValue().getString());
                             
                             break;
                             case 6: // unit
                            ((IngredientsModel)jsonModel).setQuantity(((IngredientsModel)jsonModel).getQuantity() + cell.getRichStringCellValue().getString());
                                 //System.out.println("what will it print?? " + cell.getRichStringCellValue().getString());
                                 
                            
                           
                             break;
                              
                              case 7: // equivalent ing
                                  if(!cell.getRichStringCellValue().getString().trim().equals("")){
                            ((IngredientsModel)jsonModel).setEquivalentValue("equiv. " + cell.getRichStringCellValue().getString());
                                  }
                                 //System.out.println("what will it print for equival ?? " + cell.getRichStringCellValue().getString());
                                 
                            
                           
                             break;
                              case 8: // equivalent quantity
                                  if(((IngredientsModel) jsonModel).getEquivalentValue() != null && !((IngredientsModel) jsonModel).getEquivalentValue().equals("") ){
                                      ((IngredientsModel)jsonModel).setEquivalentValue(((IngredientsModel) jsonModel).getEquivalentValue() + "|" + cell.getRichStringCellValue().getString());
                                  }
                            
                                 //System.out.println("what will it print?? " + cell.getRichStringCellValue().getString());
                                 
                            
                           
                             break;
                              case 9: // equivalent qty
                                  if(((IngredientsModel) jsonModel).getEquivalentValue() != null && !((IngredientsModel) jsonModel).getEquivalentValue().equals("") ){
                            ((IngredientsModel)jsonModel).setEquivalentValue(((IngredientsModel) jsonModel).getEquivalentValue() + cell.getRichStringCellValue().getString());
                                  } //System.out.println("what will it print?? " + cell.getRichStringCellValue().getString());
                                 
                            
                           
                             break;
                             
                     }
           }
                      if( jsonModel instanceof ProductImageModel){ 
                     switch (cell.getColumnIndex()) {
                         case 0: //url
                             ((ProductImageModel)jsonModel).setCDNImageUrl(cell.getRichStringCellValue().getString());
                             break;
                             case 1: //product id 
                                 ((ProductImageModel)jsonModel).setProductID(cell.getRichStringCellValue().getString());
                             break;
                     }
                      }
                    break;
                    
              case Cell.CELL_TYPE_NUMERIC:
                   if( jsonModel instanceof ProductModel){ 
                     switch (cell.getColumnIndex()) {
                         case 0: 
                             // product id
                                 //System.out.println(row.getRowNum()+1);
                              //  ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
                         //     ((ProductModel) jsonModel).setProductID(String.valueOf(Integer.parseInt(String.valueOf(cell.getNumericCellValue()))));
                            
                             break;
                    
                    }
                   }
                   if( jsonModel instanceof IngredientsModel){ 
                     switch (cell.getColumnIndex()) {
                         case 2: 
                             // product id
                                 //System.out.println(row.getRowNum()+1);
                              //  ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
                        //      ((IngredientsModel) jsonModel).setProductID(String.valueOf((cell.getNumericCellValue())));
                            
                             break;
                    
                    }
                   } 
                   if( jsonModel instanceof ProductImageModel){ 
                     switch (cell.getColumnIndex()) {
                         case 0: 
                             // product id
                                 //System.out.println(row.getRowNum()+1);
                              //  ((ProductModel) jsonModel).setProductID(String.valueOf(row.getRowNum()+1));
                            //  ((ProductImageModel) jsonModel).setProductID(String.valueOf(Integer.parseInt(String.valueOf(cell.getNumericCellValue()))));
                            
                             break;
                    
                    }
                   } break;
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
              }else if( jsonModel instanceof IngredientsModel){ 
                  iModelList.add((IngredientsModel)jsonModel);
              } 
              else if( jsonModel instanceof ProductImageModel){ 
                  imageModellList.add((ProductImageModel)jsonModel);
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
           
            jsonInString = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(imageModellList);
            System.out.println("JsonInString " +jsonInString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
       

       

        try {
            if(jsonModelType.equals("product")){
               mapper.writeValue(new File("E:\\sanofi\\sanofinaturesownproducts113.json"), productsList);
               
        }
            else if(jsonModelType.equals("ingredients")){
             mapper.writeValue(new File("E:\\sanofi\\CenovisOwnIngredientsnouse113.json"), iModelList);
        }else if(jsonModelType.equals("images")){
             mapper.writeValue(new File("E:\\sanofi\\CenovisOwnImages113nouse.json"), imageModellList);
        }
          
            
        } catch (IOException ex) {
            Logger.getLogger(ExcelToJsonConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    void populateProductModel(){
        
    }
    
}
