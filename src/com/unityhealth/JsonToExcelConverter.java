/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth;
import com.unityhealth.model.BiocIngredientsModel;
import com.unityhealth.model.IngredientsModel;
import com.unityhealth.model.ProductModel;
import com.unityhealth.parser.UHJParser;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Clinton
 */
public class JsonToExcelConverter {
    public static void main(String args[]) throws IOException{
        UHJParser jParser = new UHJParser();
         BiocIngredientsModel biocIngModel = new BiocIngredientsModel();
         BiocIngredientsModel biocIngModelReturned = null;
          XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Ingredients");
         int rowCount = 0;
         biocIngModelReturned =  (BiocIngredientsModel)jParser.parse("E:\\biocueticalsload\\ProductLoadBioc.json",biocIngModel);
         ProductModel [] products = null;
         products = biocIngModelReturned.getProducts();
         int i = 0;
         try{
             for (ProductModel product : products) {
            
           
             
            for (IngredientsModel ingredient : product.getIngredients()) {
                String equiVal = ingredient.getEquivalentValue() ;
                System.out.println(ingredient);
                System.out.println(equiVal);
               // System.out.println(ingredient.getQuantity());// + ++i);
//                    String [] qty = ingredient.getQuantity().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");  
//                    System.out.println(qty[0]);
//                    if(qty.length > 1)
//                    System.out.println(qty[1]);
//                   if(qty.length > 2)
//                    System.out.println(qty[2]);
                   
                   Matcher match = Pattern.compile("[0-9.]+|[a-z]+|[A-Z]+").matcher(ingredient.getQuantity());
                 // System.out.println("group count " + match.groupCount());
                  
    while (match.find()) {
       //  System.out.println(match.group(0)+ "group 0");
       //System.out.println(match.group());
    }
                if(equiVal !=null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") < 0 ){
                   
                  doSomthing(equiVal);// str.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
//                 Row row = sheet.createRow(++rowCount);
//             
//           Cell productNameCell = row.createCell(0);
//              
//                    productNameCell.setCellValue((String) product.getProductName());
//                 int columnCount = 0;
//                Cell ingNameCell = row.createCell(++columnCount);
//              
//                    ingNameCell.setCellValue((String) ingredient.getIngredientName());
//               Cell commonNamecell = row.createCell(++columnCount);
//               commonNamecell.setCellValue((String) ingredient.getCommonName());
//                Cell displayNamecell = row.createCell(++columnCount);
//               displayNamecell.setCellValue((String) ingredient.getDisplayName());
//               Cell scientificNamecell = row.createCell(++columnCount);
//               scientificNamecell.setCellValue((String) ingredient.getIngredientScientific());
//                Cell equivalentValueNamecell = row.createCell(++columnCount);
//                 equivalentValueNamecell.setCellValue((String) equiVal);
                }
                if(equiVal !=null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") > 0 ){
                     System.out.println("multiple....." + equiVal);
                    String[] multiEquival = equiVal.split("\\|\\|");
                    for(String singleEqui:multiEquival){
                        System.out.println("after split....." + singleEqui);
                                
                        doSomthing(singleEqui);
                    }
                
                    
                }
            }
        }
         }catch(Exception e){
             e.printStackTrace();
         }
        
        
//        try (FileOutputStream outputStream = new FileOutputStream("bioceuticals_ingredients" + "equivOnly" + ".xlsx")) {
//            workbook.write(outputStream);
//        }
    }
    
    private static void doSomthing(String equiVal){
        if (equiVal.indexOf(".") > 0 && equiVal.indexOf("|") > 0) {
                        if(equiVal.indexOf("to ")>0){
                           System.out.println(equiVal.substring(equiVal.indexOf("to ") + 3, equiVal.indexOf("|")).trim());
                        }else{
                            System.out.println(equiVal.substring(equiVal.indexOf(". ") + 2, equiVal.indexOf("|")).trim());
                        }
                        
                    }else if(equiVal.startsWith("standardized to")){
                          System.out.println(equiVal.substring(equiVal.indexOf("standardized to") + "standardized to".length(), equiVal.indexOf("|")).trim());
                    }
    }
}
