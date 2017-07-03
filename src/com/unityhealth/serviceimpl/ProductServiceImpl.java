/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.serviceimpl;

import com.unityhealth.dao.IProductBrandDao;
import com.unityhealth.dao.IProductDao;
import com.unityhealth.dao.IProductImagesDao;
import com.unityhealth.dao.IProductIndicationsAssocDao;
import com.unityhealth.dao.IProductIndicationsDao;
import com.unityhealth.dao.IProductNewIDDao;
import com.unityhealth.dao.IProductWarningAssocDao;
import com.unityhealth.dao.IProductWarningCodesDao;
import com.unityhealth.daoimpl.ProductBrandDaoImpl;
import com.unityhealth.daoimpl.ProductDaoImpl;
import com.unityhealth.daoimpl.ProductImagesDaoImpl;
import com.unityhealth.daoimpl.ProductIndicationsAssocDaoImpl;
import com.unityhealth.daoimpl.ProductIndicationsDaoImpl;
import com.unityhealth.daoimpl.ProductNewIDDaoImpl;
import com.unityhealth.daoimpl.ProductWarningAssocDaoImpl;
import com.unityhealth.daoimpl.ProductWarningCodesDaoImpl;
import com.unityhealth.model.BiocIngredientsModel;
import com.unityhealth.model.ProductImageModel;
import com.unityhealth.model.ProductItemsModel;
import com.unityhealth.model.ProductModel;
import com.unityhealth.model.Tblproduct;
import com.unityhealth.model.Tblproductbrand;
import com.unityhealth.model.Tblproductimages;
import com.unityhealth.model.Tblproductindicationassoc;
import com.unityhealth.model.Tblproductindications;
import com.unityhealth.model.Tblproductnewid;
import com.unityhealth.model.Tblproductwarningassoc;
import com.unityhealth.model.Tblproductwarningcodes;
import com.unityhealth.service.IProductIngredientsService;
import com.unityhealth.service.IProductService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Clinton
 */
public class ProductServiceImpl implements IProductService {

    IProductDao productDao;
    IProductBrandDao productBrandDao;
    IProductWarningCodesDao pwCodesDao;
    IProductWarningAssocDao pwaDao;
    IProductIndicationsAssocDao pwIndiAssocDao;
    IProductIndicationsDao pwIndiDao;
    IProductNewIDDao nIdDao;
    List<Tblproduct> maxIdLst;
    IProductIngredientsService productIngredientsService;
    IProductImagesDao productImagesDao;
    private String hashString = "#";

    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
        productBrandDao = new ProductBrandDaoImpl();
        pwCodesDao = new ProductWarningCodesDaoImpl();
        pwaDao = new ProductWarningAssocDaoImpl();
        nIdDao = new ProductNewIDDaoImpl();
        productIngredientsService = new ProductIngredientsServiceImpl();
        productImagesDao = new ProductImagesDaoImpl();
        pwIndiDao = new ProductIndicationsDaoImpl();
        pwIndiAssocDao = new ProductIndicationsAssocDaoImpl();
    }

    @Override
    public boolean saveProduct(ProductModel[] productJsonArray,Integer brandID) {
        Map<String, String> prodWarningsMap = new LinkedHashMap<String, String>();
         Map<String, String> prodIndicationsMap = new LinkedHashMap<String, String>();
        System.out.println("Total Number of Products to be inserted " + productJsonArray.length);
        try {
            Tblproduct product;
            
            for (ProductModel productModel : productJsonArray) {
                if(productModel.getItems()!=null){
                for (ProductItemsModel item : productModel.getItems()) {
                   // productDao.beginTransaction();
                    //System.out.println(productModel.toString());
                    saveIndividualProduct(productModel,item,prodWarningsMap,brandID,null);
                   // productDao.commitTransaction();
                  
                }
                }else{
                   // productDao.beginTransaction();
                    saveIndividualProduct(productModel,null,prodWarningsMap,brandID,prodIndicationsMap);
                   // productDao.commitTransaction();
                }
//                  System.out.println(productModel.getProductBrand());
                if( brandID == 15 ){
                    System.out.println("bioceuticals");
                     productIngredientsService.saveProductIngredients(productModel.getIngredients());
                }
                
            }

            System.out.println("Finished inserting products");
            System.out.println("Started inserting Warnings");
            setProdWarnings(prodWarningsMap);
            if(brandID != 15){
                setProdIndications(prodIndicationsMap);
            }
            updateNewProductId();
            System.out.println("Finished inserting Warnings");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

      //backup plan
    public boolean saveProduct2(ProductModel[] productJsonArray) {
        Map<String, String> prodWarningsMap = new LinkedHashMap<String, String>();
        System.out.println("Total Number of Products to be inserted " + productJsonArray.length);
        try {
            Tblproduct product;

            for (ProductModel productModel : productJsonArray) {
                for (ProductItemsModel item : productModel.getItems()) {
                    productDao.beginTransaction();
                    product = new Tblproduct();
                    StringBuilder searchSB = new StringBuilder();
                    // product.setIBrandID(getBrandID(productModel.getProductBrand()));
                    if(productModel.getProductBrand().equalsIgnoreCase("BC"))
                         product.setIBrandID(15);
                    else{
                         int bid = getBrandID(productModel.getProductBrand());
                         if (bid ==0)
                             bid = 88;
                          product.setIBrandID(bid);
                    }
                       
                       
                    if (productModel.getProductName() != null && !productModel.getProductName().trim().equals("")) {
                        searchSB.append(productModel.getProductName());
                        searchSB.append(hashString);
                    }
                    product.setVName(productModel.getProductName());
                    product.setIAUSTL(productModel.getProductAustl());
                    searchSB.append(productModel.getProductAustl());
                    searchSB.append(hashString);
                    if (productModel.getProductNote() != null) {
                        product.setVDescription(Jsoup.parse(productModel.getProductNote()).text());
                        searchSB.append(Jsoup.parse(productModel.getProductNote()).text());
                    }

                    searchSB.append(hashString);


                    if (item.getSize() != null && item.getSize().indexOf(" ") > 0) {
                        product.setVSize(item.getSize().substring(0, item.getSize().indexOf(" ")));
                        product.setVQtyUnit(item.getSize().substring(item.getSize().indexOf(" ") + 1, item.getSize().length() - 1));
                    }
                    if (productModel.getProductDosage() != null) {
                        product.setVDosage(Jsoup.parse(productModel.getProductDosage()).text());
                    }
                    productImagesDao.beginTransaction();
                     Tblproductimages productImages = new Tblproductimages();
                    for( ProductImageModel productImageModel:item.getImages()){
                        if(productImageModel.getImageDimension().equals("190x250")){
                           
                            productImages.setIBrandID(15);
                            productImages.setVFilename(productImageModel.getImageFile().substring(productImageModel.getImageFile().lastIndexOf("/")+1));
                            productImagesDao.create(productImages);
                            //productImageModel.getCDNImageUrl();
                            saveImage(productImageModel.getCDNImageUrl());
                        }
                    }
                    productImagesDao.commitTransaction();
                    product.setVImageID(productImages.getIID());
                    product.setvProductId(productModel.getProductID());
                    product.setvPartNo(item.getPartNo());
                    product.setVSearchText(searchSB.toString());
                    productDao.create(product);
                    prodWarningsMap.put(productModel.getProductID(), productModel.getProductWarning());
                    productDao.commitTransaction();
                  
                }
                System.out.println(productModel.getProductBrand());
                if(productModel.getProductBrand().equalsIgnoreCase("BC"))
                 productIngredientsService.saveProductIngredients(productModel.getIngredients());
            }

            System.out.println("Finished inserting products");
            System.out.println("Started inserting Warnings");
            setProdWarnings(prodWarningsMap);
            updateNewProductId();
            System.out.println("Finished inserting Warnings");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private int getBrandID(String brandName) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("vName", brandName);

        List<Tblproductbrand> result = productBrandDao.readByNameDQuery("Tblproductbrand.findByVName", params);
        if(!result.isEmpty()){
            return result.get(0).getIID();
        }
        return 0;
    }

    private List<Tblproductwarningcodes> setProdWarnings(Map<String, String> prodWarningsMap) {
        List<Tblproductwarningcodes> results = new ArrayList<Tblproductwarningcodes>();
        List<String> warningList = new ArrayList<String>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> missingWarningList = null;
        for (String prodId : prodWarningsMap.keySet()) {
            System.out.println("product warning for productId  " + prodId);
            warningList = prepareWarningList(prodWarningsMap.get(prodId));
            params.put("vDescription", warningList);
            results = pwCodesDao.readByNameDQueryList("Tblproductwarningcodes.findByVDescription", params);
            missingWarningList = findMissingWarnings(warningList, results);
            if (missingWarningList != null) {
                createMissingWarnings(missingWarningList);
                results = pwCodesDao.readByNameDQueryList("Tblproductwarningcodes.findByVDescription", params);
            }
            createProductWarningAssociation(prodId, results);
            //System.out.println("com.unityhealth.serviceimpl.ProductServiceImpl.getProdWarnings()" + missingWarningList);
        }

        return results;
    }
    private List<Tblproductindications> setProdIndications(Map<String, String> prodIndicationsMap) {
        List<Tblproductindications> results = new ArrayList<Tblproductindications>();
        List<String> indicationsList = new ArrayList<String>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> missingIndicationsList = null;
        for (String prodId : prodIndicationsMap.keySet()) {
            System.out.println("product Indications for productId  " + prodId);
            indicationsList = prepareWarningList(prodIndicationsMap.get(prodId));
            params.put("vDesc", indicationsList);
            results = pwIndiDao.readByNameDQueryList("Tblproductindications.findByVDesc", params);
            missingIndicationsList = findMissingIndications(indicationsList, results);
            if (missingIndicationsList != null) {
                createMissingIndications(missingIndicationsList);
                results = pwIndiDao.readByNameDQueryList("Tblproductindications.findByVDesc", params);
            }
            createProductIndicationsAssociation(prodId, results);
            //System.out.println("com.unityhealth.serviceimpl.ProductServiceImpl.getProdWarnings()" + missingWarningList);
        }

        return results;
    }
//tobe moved to util 

    private List<String> prepareWarningList(String toRemoveHtml) {
        List<String> warningListFromModel = new ArrayList<String>();

        if (toRemoveHtml != null && !toRemoveHtml.contains("|") ) {
            Elements elements = Jsoup.parse(toRemoveHtml).body().select("*");
            for (Element element : elements) {
                if (!element.ownText().trim().equals("")) {
                    warningListFromModel.add(element.ownText());
                }
            }
        }
        if (toRemoveHtml != null && toRemoveHtml.contains("|") ) {
           String[] warningsArray = toRemoveHtml.split("\\|");
           for(String warningPart:warningsArray){
               warningListFromModel.add(warningPart);
               System.out.println(warningPart);
           }
        }
//        if (toRemoveHtml != null && toRemoveHtml.contains(".") ) {
//           String[] warningsArray = toRemoveHtml.split("\\.");
//           for(String warningPart:warningsArray){
//               warningListFromModel.add(warningPart);
//           }
//        }

        return warningListFromModel;
    }

    private List<String> findMissingWarnings(List<String> expectedWarnings, List<Tblproductwarningcodes> existingWarningsDesc) {
        List<String> existingWarnigs = prepareExistingWarnings(existingWarningsDesc);
        if (existingWarnigs.containsAll(expectedWarnings)) {
            return null;
        }
        if (existingWarnigs.size() <= expectedWarnings.size()) {
            expectedWarnings.removeAll(existingWarnigs);
            return expectedWarnings;
        }

        return null;
    }
    private List<String> findMissingIndications(List<String> expectedIndications, List<Tblproductindications> existingIndicationsDesc) {
        List<String> existingIndications = prepareExistingIndications(existingIndicationsDesc);
        if (existingIndications.containsAll(expectedIndications)) {
            return null;
        }
        if (existingIndications.size() <= expectedIndications.size()) {
            expectedIndications.removeAll(existingIndications);
            return expectedIndications;
        }

        return null;
    }

    private List<String> prepareExistingWarnings(List<Tblproductwarningcodes> existingWarningsDesc) {
        List<String> existingWarnigs = new ArrayList<String>();
        for (Tblproductwarningcodes warnigcodes : existingWarningsDesc) {
            existingWarnigs.add(warnigcodes.getVDescription());
        }
        return existingWarnigs;
    }
     private List<String> prepareExistingIndications(List<Tblproductindications> existingIndicationDesc) {
        List<String> existingIndications = new ArrayList<String>();
        for (Tblproductindications indications : existingIndicationDesc) {
            existingIndications.add(indications.getVDesc());
        }
        return existingIndications;
    }

    private void createMissingWarnings(List<String> missingWarningList) {
        if (missingWarningList != null) {
            pwCodesDao.beginTransaction();
            for (String warn : missingWarningList) {
                Tblproductwarningcodes missingWarning = new Tblproductwarningcodes();
                missingWarning.setVDescription(warn);
                missingWarning.setBCustom(Boolean.FALSE);
                pwCodesDao.create(missingWarning);
            }
            pwCodesDao.commitTransaction();
        }
    }
     private void createMissingIndications(List<String> missingIndicationsList) {
         try{
        if (missingIndicationsList != null) {
            pwIndiDao.beginTransaction();
            for (String indication : missingIndicationsList) {
                Tblproductindications missingIndications = new Tblproductindications();
                missingIndications.setVDesc(indication);
                //missingWarning.setBCustom(Boolean.FALSE);
                pwIndiDao.create(missingIndications);
            }
            pwIndiDao.commitTransaction();
        }}catch(Exception e){
            
            System.out.println("Error persisting indication ");
            e.printStackTrace();
        }
        
    }

    private void createProductWarningAssociation(String prodID, List<Tblproductwarningcodes> results) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("vProductId", prodID);
        List<Tblproductwarningassoc> assocs = null;
        List<Tblproduct> products = productDao.readByNameDQuery("Tblproduct.findByVProductId", params);

        for (Tblproductwarningcodes warning : results) {
            try {
                pwaDao.beginTransaction();
                for (Tblproduct product : products) {
                    Map<String, Integer> assocParams = new TreeMap<String, Integer>();
                    assocParams.put("iProductID", product.getIID());
                    assocParams.put("iWarningCodeID", warning.getIID());
                    assocs = pwaDao.readByNameDQueryInt("Tblproductwarningassoc.findByIProductIDIWarningCodeID", assocParams);
                    if (assocs.isEmpty()) {
                        Tblproductwarningassoc productWarningAssociation = new Tblproductwarningassoc();
                        productWarningAssociation.setIProductID(product.getIID());
                        productWarningAssociation.setIWarningCodeID(warning.getIID());
                        pwaDao.create(productWarningAssociation);
                    }

                }
                pwaDao.commitTransaction();
            } catch (Exception e) {
                System.out.println(warning.getVDescription());
                e.printStackTrace();
            }
        }

    }
    
    private void createProductIndicationsAssociation(String prodID, List<Tblproductindications> indicationsResults) {
        Map<String, String> params = new TreeMap<String, String>();
        params.put("vProductId", prodID);
        List<Tblproductindicationassoc> assocs = null;
        List<Tblproduct> products = productDao.readByNameDQuery("Tblproduct.findByVProductId", params);

        for (Tblproductindications indications : indicationsResults) {
            try {
                pwIndiAssocDao.beginTransaction();
                for (Tblproduct product : products) {
                    Map<String, Integer> assocParams = new TreeMap<String, Integer>();
                    assocParams.put("iProductID", product.getIID());
                    assocParams.put("iIndicationID", indications.getIID());
                    assocs = pwIndiAssocDao.readByNameDQueryInt("Tblproductindicationassoc.findByIProductIDIIndicationID", assocParams);
                    if (assocs.isEmpty()) {
                        Tblproductindicationassoc productIndicationAssociation = new Tblproductindicationassoc();
                        productIndicationAssociation.setIProductID(product.getIID());
                        productIndicationAssociation.setIIndicationID(indications.getIID());
                        pwIndiAssocDao.create(productIndicationAssociation);
                    }

                }
                pwIndiAssocDao.commitTransaction();
            } catch (Exception e) {
                System.out.println(indications.getVDesc());
                e.printStackTrace();
            }
        }

    }

    private void updateNewProductId() {
        maxIdLst = productDao.readByNameDQueryNoParam("Tblproduct.findMaxIID");
        Map<String, Integer> params = new TreeMap<String, Integer>();
        Iterator it = maxIdLst.iterator();
        nIdDao.beginTransaction();
        while (it.hasNext()) {
            Object objMaxId = it.next();

            if (objMaxId != null) {
                Integer maxId = (Integer) objMaxId;
                params.put("iNewID", maxId);
                Tblproductnewid nwid = new Tblproductnewid();
                nIdDao.updateByNamedQueryInt("Tblproductnewid.updateNewID", params);
            }

        }
        nIdDao.commitTransaction();
    }

    @Override
    public void releasePC() {
        productDao.releasePersistenceContext();
        productBrandDao.releasePersistenceContext();
        pwCodesDao.releasePersistenceContext();
        pwaDao.releasePersistenceContext();
        productDao = null;
        productBrandDao = null;
        pwCodesDao = null;
        pwaDao = null;
    }

    @Override
    public boolean saveProduct(BiocIngredientsModel productJson,Integer brandID) {
        // productJson.getProducts();
        return saveProduct(productJson.getProducts(),brandID);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private  void saveImage(String imageUrl) throws IOException {
	URL url = new URL(imageUrl);
	String fileName = url.getFile();
	String destName = "E:\\biocueticalsload\\productImages\\11-04-17" + fileName.substring(fileName.lastIndexOf("/"));
	System.out.println(destName);
 
	InputStream is = url.openStream();
	OutputStream os = new FileOutputStream(destName);
 
	byte[] b = new byte[2048];
	int length;
 
	while ((length = is.read(b)) != -1) {
		os.write(b, 0, length);
	}
 
	is.close();
	os.close();
}
    private boolean saveIndividualProduct(ProductModel productModel,ProductItemsModel item,Map<String, String> prodWarningsMap,Integer brandID,Map<String, String> prodIndicationsMap){
          try {
            Tblproduct product;

        productDao.beginTransaction();
                    product = new Tblproduct();
                    StringBuilder searchSB = new StringBuilder();
                    // product.setIBrandID(getBrandID(productModel.getProductBrand()));
                    System.out.println("com.unityhealth.serviceimpl.ProductServiceImpl.getProdWarnings()" + productModel.getProductBrand());
                  
                          product.setIBrandID(brandID);
                        
                    if (productModel.getProductName() != null && !productModel.getProductName().trim().equals("")) {
                        searchSB.append(productModel.getProductName());
                        searchSB.append(hashString);
                    }
                    product.setVName(productModel.getProductName());
                    product.setIAUSTL(productModel.getProductAustl());
                    product.setVURL(productModel.getProductURL());
                    searchSB.append(productModel.getProductAustl());
                    searchSB.append(hashString);
                    if (productModel.getProductNote() != null) {
                        product.setVDescription(Jsoup.parse(productModel.getProductNote()).text());
                        searchSB.append(Jsoup.parse(productModel.getProductNote()).text());
                    }

                    searchSB.append(hashString);

                    if(item!=null){
                         if (item.getSize() != null && item.getSize().indexOf(" ") > 0) {
                        product.setVSize(item.getSize().substring(0, item.getSize().indexOf(" ")));
                        product.setVQtyUnit(item.getSize().substring(item.getSize().indexOf(" ") + 1, item.getSize().length()));
                    }
                         
                          
                          productImagesDao.beginTransaction();
                     Tblproductimages productImages = new Tblproductimages();
                    for( ProductImageModel productImageModel:item.getImages()){
                        if(productImageModel.getImageDimension().equals("190x250")){
                           
                            productImages.setIBrandID(brandID);
                            productImages.setVFilename(productImageModel.getImageFile().substring(productImageModel.getImageFile().lastIndexOf("/")+1));
                            productImagesDao.create(productImages);
                            //productImageModel.getCDNImageUrl();
                            saveImage(productImageModel.getCDNImageUrl());
                            
                        }
                    }
                    productImagesDao.commitTransaction();
                     product.setVImageID(productImages.getIID());
                              product.setvPartNo(item.getPartNo());
                    }
                    if (productModel.getOptionSize() != null && productModel.getOptionSize().indexOf(" ") > 0) {
                        product.setVSize(productModel.getOptionSize().substring(0, productModel.getOptionSize().indexOf(" ")));
                        product.setVQtyUnit(productModel.getOptionSize().substring(productModel.getOptionSize().indexOf(" ") + 1, productModel.getOptionSize().length()));
                    }
                   
                    if (productModel.getProductDosage() != null) {
                        product.setVDosage(Jsoup.parse(productModel.getProductDosage()).text());
                    }
                   
                   
                    product.setvProductId(productModel.getProductID());
                   
                    product.setVSearchText(searchSB.toString());
                    productDao.create(product);
                    prodWarningsMap.put(productModel.getProductID(), productModel.getProductWarning());
                    prodIndicationsMap.put(productModel.getProductID(), productModel.getProductIndications());
                    System.out.println("Product getting Saved" + productModel.getProductName() + " ---- " + productModel.getProductID());
                    productDao.commitTransaction();
                  
                }catch(Exception e){
                    e.printStackTrace();
                    return false;
                }
//                if(brandID == 15)
//                 productIngredientsService.saveProductIngredients(productModel.getIngredients());
     return true;
    }
    @Override
    public boolean updatewarnings(BiocIngredientsModel productJson,Integer brandID){
        ProductModel[] productJsonArray = productJson.getProducts();
         Map<String, String> prodWarningsMap = new LinkedHashMap<String, String>();
           for (ProductModel productModel : productJsonArray) {
               
               
                  
                    prodWarningsMap.put(productModel.getProductID(), productModel.getProductWarning());
                
                  
               
    }
            setProdWarnings(prodWarningsMap);
            return true;
    }
    @Override
    public boolean updateIndications(ProductModel[] productJsonArray,Integer brandID){
       // ProductModel[] productJsonArray = productJson.getProducts();
         Map<String, String> prodIndicationsMap = new LinkedHashMap<String, String>();
           for (ProductModel productModel : productJsonArray) {
               
               
                  
                    prodIndicationsMap.put(productModel.getProductID(), productModel.getProductIndications());
                
                  
               
    }
            setProdIndications(prodIndicationsMap);
            return true;
    }
    @Override
    public boolean updatewarnings(ProductModel[] productJsonArray,Integer brandID){
       // ProductModel[] productJsonArray = productJson.getProducts();
          Map<String, String> prodWarningsMap = new LinkedHashMap<String, String>();
           for (ProductModel productModel : productJsonArray) {
               
               
                  
                    prodWarningsMap.put(productModel.getProductID(), productModel.getProductWarning());
                
                  
               
    }
            setProdWarnings(prodWarningsMap);
            return true;
    }
}
