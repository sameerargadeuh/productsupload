/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.serviceimpl;

import com.unityhealth.dao.IProductBrandDao;
import com.unityhealth.dao.IProductDao;
import com.unityhealth.dao.IProductImagesDao;
import com.unityhealth.dao.IProductNewIDDao;
import com.unityhealth.dao.IProductWarningAssocDao;
import com.unityhealth.dao.IProductWarningCodesDao;
import com.unityhealth.daoimpl.ProductBrandDaoImpl;
import com.unityhealth.daoimpl.ProductDaoImpl;
import com.unityhealth.daoimpl.ProductImagesDaoImpl;
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
    }

    @Override
    public boolean saveProduct(ProductModel[] productJsonArray) {
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
                    product.setIBrandID(15);
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

//                if (productModel.getOptionSize() != null && productModel.getOptionSize().indexOf(" ") > 0) {
//                    product.setVSize(productModel.getOptionSize().substring(0, productModel.getOptionSize().indexOf(" ")));
//                    product.setVQtyUnit(productModel.getOptionSize().substring(productModel.getOptionSize().indexOf(" ") + 1, productModel.getOptionSize().length() - 1));
//                }
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
                            //saveImage(productImageModel.getCDNImageUrl());
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
        return result.get(0).getIID();
    }

    private List<Tblproductwarningcodes> setProdWarnings(Map<String, String> prodWarningsMap) {
        List<Tblproductwarningcodes> results = new ArrayList<Tblproductwarningcodes>();
        List<String> warningList = new ArrayList<String>();
        Map<String, List<String>> params = new HashMap<String, List<String>>();
        List<String> missingWarningList = null;
        for (String prodId : prodWarningsMap.keySet()) {
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
//tobe moved to util 

    private List<String> prepareWarningList(String toRemoveHtml) {
        List<String> warningListFromModel = new ArrayList<String>();

        if (toRemoveHtml != null) {
            Elements elements = Jsoup.parse(toRemoveHtml).body().select("*");
            for (Element element : elements) {
                if (!element.ownText().trim().equals("")) {
                    warningListFromModel.add(element.ownText());
                }
            }
        }

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

    private List<String> prepareExistingWarnings(List<Tblproductwarningcodes> existingWarningsDesc) {
        List<String> existingWarnigs = new ArrayList<String>();
        for (Tblproductwarningcodes warnigcodes : existingWarningsDesc) {
            existingWarnigs.add(warnigcodes.getVDescription());
        }
        return existingWarnigs;
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
    public boolean saveProduct(BiocIngredientsModel productJson) {
        // productJson.getProducts();
        return saveProduct(productJson.getProducts());
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private  void saveImage(String imageUrl) throws IOException {
	URL url = new URL(imageUrl);
	String fileName = url.getFile();
	String destName = "E:\\biocueticalsload\\productImages" + fileName.substring(fileName.lastIndexOf("/"));
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
}
