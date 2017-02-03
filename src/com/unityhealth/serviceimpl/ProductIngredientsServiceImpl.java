/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth.serviceimpl;

import com.unityhealth.dao.IProductDao;
import com.unityhealth.dao.IProductIngredientsAssocDao;
import com.unityhealth.dao.IProductIngredientsDao;
import com.unityhealth.daoimpl.ProductDaoImpl;
import com.unityhealth.daoimpl.ProductIngredientsAssocDaoImpl;
import com.unityhealth.daoimpl.ProductIngredientsDaoImpl;
import com.unityhealth.model.IngredientsModel;
import com.unityhealth.model.Tblproduct;
import com.unityhealth.model.Tblproductingredientassoc;
import com.unityhealth.model.Tblproductingredientassoctemp;
import com.unityhealth.model.Tblproductingredients;
import com.unityhealth.service.IProductIngredientsService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sameer
 */
public class ProductIngredientsServiceImpl implements IProductIngredientsService {

    IProductIngredientsDao piDao;
    IProductDao productDao;
    IProductIngredientsAssocDao piAssocDao;
    List<Tblproductingredientassoc> qtyList; //=  piAssocDao.readByNameDQueryNoParam("Tblproductingredientassoc.findDistinctVQtyUnit");
    List<String> qtyStrList;

    public ProductIngredientsServiceImpl() {
        piDao = new ProductIngredientsDaoImpl();
        productDao = new ProductDaoImpl();
        piAssocDao = new ProductIngredientsAssocDaoImpl();
        qtyList = piAssocDao.readByNameDQueryNoParam("Tblproductingredientassoc.findDistinctVQtyUnit");
        qtyStrList = new ArrayList<String>();
        //if (qtyList == null) {
        qtyStrList = new ArrayList();
        qtyStrList.add("%");
        qtyStrList.add("% w/w");
        qtyStrList.add("billion");
        qtyStrList.add("g");
        qtyStrList.add("IU");
        qtyStrList.add("mcg");
        qtyStrList.add("mg");
        qtyStrList.add("million");
        qtyStrList.add("ng");
        qtyStrList.add("ml");
        qtyStrList.add("%");
        qtyStrList.add("% w/v");
        qtyStrList.add("% v/v");
        //  }
    }

    @Override
    public boolean saveProductIngredients(IngredientsModel[] ingredientsJsonArray) {
        System.out.println("Total Number of Ingredients to be inserted " + ingredientsJsonArray.length);
        Map<String, List<String>> prodIngredientsLstMap = prepareIngredientsList(ingredientsJsonArray);
        Map<String, List<String>> params = new TreeMap<String, List<String>>();
        Map<String, String> prodQueryMap = new TreeMap<String, String>();
        List<Tblproductingredients> result = null;
        List<String> missingIngredients = null;
        List<Tblproduct> productsLst = null;
        String tempQty = null;
        // List<Tblproductingredientassoctemp> assocTempLst = new ArrayList();
        StringBuilder searchSB = null;
        int missingIngs = 0;
        try {

            for (String prodId : prodIngredientsLstMap.keySet()) {
                params.put("vNameLst", prodIngredientsLstMap.get(prodId));
                result = piDao.readByNameDQueryList("Tblproductingredients.findByVNameLst", params);
                List expectedWarningsList = new ArrayList<String>(prodIngredientsLstMap.get(prodId));
                missingIngredients = findMissingIngredients(expectedWarningsList, result);

                if (missingIngredients != null) {
                    missingIngs += missingIngredients.size();
                    try {
                        piDao.beginTransaction();
                        for (String missingIngredient : missingIngredients) {
                            Tblproductingredients productingredient = new Tblproductingredients();
                            productingredient.setBCustom(Boolean.FALSE);
                            IngredientsModel iModelRaw = getIngredientModelFromIgredientName(missingIngredient, ingredientsJsonArray, false);
                            if (iModelRaw != null) {
                                productingredient.setvIngredientID(iModelRaw.getIngredientID());
                                System.out.println("iModelRaw.getCommonName() " + iModelRaw.getCommonName());
                                productingredient.setBCustom(Boolean.FALSE);
                                productingredient.setVCommonName(iModelRaw.getCommonName());
                                productingredient.setvDisplayName(iModelRaw.getDisplayName());
                            } else {
                                iModelRaw = getIngredientModelFromIgredientName(missingIngredient, ingredientsJsonArray, true);
                                if (iModelRaw != null) {
                                    // productingredient.setvIngredientID(iModelRaw.getIngredientID());
                                    // productingredient.setVCommonName(iModelRaw.getCommonName());
                                    // productingredient.setvDisplayName(iModelRaw.getDisplayName());
                                    // productingredient.setBCustom(Boolean.FALSE);
                                    System.out.println("iModelRaw.getCommonName() " + iModelRaw.getEquivalentValue());
                                }
                            }

                            productingredient.setVName(missingIngredient);
                            System.out.println("missing ingredient about to insert " + missingIngredient);
                            piDao.create(productingredient);
                        }
                        piDao.commitTransaction();
                    } catch (Exception e) {
                        //System.out.println("missing ingredient failed to insert " + missingIngredient);
                        e.printStackTrace();
                    }
                    result = piDao.readByNameDQueryList("Tblproductingredients.findByVNameLst", params);
                }
                prodQueryMap.put("vProductId", prodId);
                productsLst = productDao.readByNameDQuery("Tblproduct.findByVProductId", prodQueryMap);
                System.out.println("the product List " + productsLst);
                //for main ingredients
                try {
                    productDao.beginTransaction();
                    piAssocDao.beginTransaction();
                    for (Tblproduct product : productsLst) {
                        for (Tblproductingredients ingredient : result) {
                            Tblproductingredientassoc productIngAssoc = new Tblproductingredientassoc();
                            searchSB = new StringBuilder();
                            //Tblproductingredientassoctemp productIngAssocTemp = new Tblproductingredientassoctemp();
                            productIngAssoc.setIIngredientID(ingredient.getIID());
                            //productIngAssocTemp.setIIngredientID(ingredient.getIID());
                            productIngAssoc.setIProductID(product.getIID());
                            //productIngAssocTemp.setIProductID(product.getIID());
                            searchSB.append(product.getVSearchText());
                            searchSB.append(ingredient.getVName());
                            searchSB.append("#");
                            IngredientsModel iModelMainIng = getIngredientModelFromIgredientName(ingredient.getVName(), ingredientsJsonArray, false);
                            if (iModelMainIng != null) {
                                //tempQty = addPipeToQty(iModelMainIng.getQuantity());
                                tempQty = iModelMainIng.getQuantity();
                                Matcher match = Pattern.compile("[0-9.]+|[a-z]+|[A-Z]+").matcher(iModelMainIng.getQuantity());
                                // System.out.println("group count " + match.groupCount());
                                int i = 0;
                                String unit = "";
                                while (match.find()) {
                                    //  System.out.println(match.group(0)+ "group 0");
                                    if (i == 0) {
                                        productIngAssoc.setVQty(match.group());
                                        i = 1;
                                    } else {
                                        unit = match.group();
                                                break;
                                    }

                                    //System.out.println(match.group());
                                }
                                productIngAssoc.setVQtyUnit(unit);

                            } else {
                                IngredientsModel iModelEquiv = getIngredientModelFromIgredientName(ingredient.getVName(), ingredientsJsonArray, true);
                                if (iModelEquiv != null) {
                                    String equiVal = iModelEquiv.getEquivalentValue();

                                    if (equiVal != null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") < 0) {

                                        tempQty = equiVal.substring(equiVal.indexOf("|") + 1, equiVal.length());
                                        Matcher match = Pattern.compile("[0-9.]+|[a-z]+|[A-Z]+").matcher(tempQty);
                                        int i = 0;
                                        String unit = "";
                                        while (match.find()) {

                                            if (i == 0) {
                                                productIngAssoc.setVQty(match.group());
                                                i = 1;
                                            } else {
                                                unit = match.group();
                                                break;
                                            }

                                        }
                                        productIngAssoc.setVQtyUnit(unit);

                                    }
                                    if (equiVal != null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") > 0) {
                                        //System.out.println("multiple....." + equiVal);
                                        String[] multiEquival = equiVal.split("\\|\\|");
                                        for (String singleEqui : multiEquival) {
                                            // System.out.println("after split....." + singleEqui);
                                            tempQty = equiVal.substring(singleEqui.indexOf("|") + 1, singleEqui.length());
                                            if (tempQty != null && tempQty.contains(ingredient.getVName())) {
                                                Matcher match = Pattern.compile("[0-9.]+|[a-z]+|[A-Z]+").matcher(tempQty);
                                                int i = 0;
                                                String unit = "";
                                                while (match.find()) {

                                                    if (i == 0) {
                                                        productIngAssoc.setVQty(match.group());
                                                        i = 1;
                                                    } else {
                                                        unit = match.group();
                                                        break;
                                                    }

                                                }
                                                productIngAssoc.setVQtyUnit(unit);
                                                break;
                                            }

                                        }

                                    }
                                    // tempQty = equiVal.substring(equiVal.indexOf("|") + 1, equiVal.length());
                                }
                            }

                            //for main ing
                            /*if (tempQty != null) {
                            String[] tempQtyArr = tempQty.split("\\|");
                            productIngAssoc.setVQty(tempQtyArr[0]);
                            productIngAssoc.setVQtyUnit(tempQtyArr[1]);
                            
                        }*/
                            tempQty = null;

                            System.out.println("assoc getting persisted" + productIngAssoc.getIIngredientID());
                            piAssocDao.create(productIngAssoc);
                        }
                        product.setVSearchText(searchSB.toString());
                    }
                    piAssocDao.commitTransaction();
                    productDao.commitTransaction();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            setParentIdForAssoc(ingredientsJsonArray);
            System.out.println("Finished with ingredients too " + missingIngs);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
        return true;

    }

    private Map<String, List<String>> prepareIngredientsList(IngredientsModel[] ingredientsJsonArray) {

        Map<String, List<String>> prodIngredientsLstMap = new TreeMap<String, List<String>>();
        List<String> ingList = null;

        for (IngredientsModel iModel : ingredientsJsonArray) {
            try {
                if (prodIngredientsLstMap.get(iModel.getProductID()) == null) {
                    ingList = new ArrayList<String>();
                } else {

                    ingList = prodIngredientsLstMap.get(iModel.getProductID());
                }
                if (!iModel.getEquivalentValue().equals("")) {
                    String equiVal = iModel.getEquivalentValue();
                    if (equiVal != null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") < 0) {

                        String singleEquival = getEquivalentIngredientName(equiVal);
                        if (singleEquival != null) {
                            ingList.add(singleEquival);
                        }
                    }
                    if (equiVal != null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") > 0) {
                        //System.out.println("multiple....." + equiVal);
                        String[] multiEquival = equiVal.split("\\|\\|");
                        for (String singleEqui : multiEquival) {
                            // System.out.println("after split....." + singleEqui);

                            String equi = getEquivalentIngredientName(singleEqui);
                            if (equi != null) {
                                ingList.add(equi);
                            }
                        }

                    }
                    /*String equiVal = iModel.getEquivalentValue();
                    if (equiVal.indexOf(".") > 0 && equiVal.indexOf("|") > 0) {
                        if(equiVal.indexOf("to ")>0){
                            ingList.add(equiVal.substring(equiVal.indexOf("to ") + 3, equiVal.indexOf("|")).trim());
                        }else{
                            ingList.add(equiVal.substring(equiVal.indexOf(".") + 1, equiVal.indexOf("|")).trim());
                        }
                        
                    }else if(equiVal.startsWith("standardized to")){
                         ingList.add(equiVal.substring(equiVal.indexOf("standardized to") + "standardized to".length(), equiVal.indexOf("|")).trim());
                    }*/
                }
                if (!iModel.getQuantity().equals("")) {
                    if (!iModel.getIngredientName().trim().equals("")) {
                        if (iModel.getIngredientName().trim().startsWith("as ")) {
                            ingList.add(iModel.getIngredientName().substring(iModel.getIngredientName().indexOf("as ") + 3, iModel.getIngredientName().length()));
                        } else {
                            ingList.add(iModel.getIngredientName());
                        }

                    } else if (!iModel.getIngredientScientific().trim().equals("")) {
                        if (iModel.getIngredientScientific().trim().startsWith("as ")) {
                            ingList.add(iModel.getIngredientScientific().substring(iModel.getIngredientScientific().indexOf("as ") + 3, iModel.getIngredientScientific().length()));
                        } else {
                            ingList.add(iModel.getIngredientScientific());
                        }
                    } else if (iModel.getCommonName() != null && !iModel.getCommonName().trim().equals("")) {
                        if (iModel.getCommonName().trim().startsWith("as ")) {
                            ingList.add(iModel.getCommonName().substring(iModel.getCommonName().indexOf("as ") + 3, iModel.getCommonName().length()));
                        } else {
                            ingList.add(iModel.getCommonName());
                        }

                    }
                }
                prodIngredientsLstMap.put(iModel.getProductID(), ingList);
            } catch (Exception e) {
                System.out.println("The Ingredient had somthing missing " + iModel.getIngredientName() + "   <<<<<<   " + iModel.getEquivalentValue());
                e.printStackTrace();
            }
        }
        return prodIngredientsLstMap;
    }

    private List<String> prepareExistingIngredients(List<Tblproductingredients> existingIngredientsLst) {
        List<String> existingIngredients = new ArrayList<String>();
        for (Tblproductingredients ingredients : existingIngredientsLst) {
            existingIngredients.add(ingredients.getVName());
        }
        return existingIngredients;
    }

    private List<String> findMissingIngredients(List<String> expectedIngredients, List<Tblproductingredients> existingIngredientsLst) {
        List<String> existingIngredients = prepareExistingIngredients(existingIngredientsLst);
        Set<String> existingIngredientsSet = new TreeSet<String>((String.CASE_INSENSITIVE_ORDER));
        Set<String> expectedIngredientsSet = new TreeSet<String>((String.CASE_INSENSITIVE_ORDER));
        existingIngredientsSet.addAll(existingIngredients);
        expectedIngredientsSet.addAll(expectedIngredients);
        if (existingIngredientsSet.containsAll(expectedIngredientsSet)) {
            return null;
        }
        if (existingIngredientsSet.size() <= expectedIngredientsSet.size()) {
            expectedIngredientsSet.removeAll(existingIngredientsSet);
            return new ArrayList(expectedIngredientsSet);
        }

        return null;
    }

    private IngredientsModel getIngredientModelFromIgredientName(String ingredientName, IngredientsModel[] ingredientsJsonArray, boolean euivalance) {
        try {
            for (IngredientsModel iModel : ingredientsJsonArray) {
                if (!iModel.getIngredientName().trim().equals("")) {
                    if (iModel.getIngredientName().trim().startsWith("as ")) {
                        if (iModel.getIngredientName().substring(iModel.getIngredientName().indexOf("as ") + 3, iModel.getIngredientName().length()).equalsIgnoreCase(ingredientName)) {
                            return iModel;
                        }
                    } else if (iModel.getIngredientName().trim().equalsIgnoreCase(ingredientName) && !euivalance) {
                        return iModel;
                    }

                } else if (!iModel.getIngredientScientific().trim().equals("")) {
                    if (iModel.getIngredientScientific().trim().startsWith("as ")) {
                        if (iModel.getIngredientScientific().substring(iModel.getIngredientScientific().indexOf("as ") + 3, iModel.getIngredientScientific().length()).equalsIgnoreCase(ingredientName)) {
                            return iModel;
                        }
                    } else if (iModel.getIngredientScientific().trim().equalsIgnoreCase(ingredientName) && !euivalance) {
                        return iModel;
                    }
                } else if (iModel.getCommonName() != null && !iModel.getCommonName().trim().equals("")) {
                    if (iModel.getCommonName().trim().startsWith("as ")) {
                        if (iModel.getCommonName().substring(iModel.getCommonName().indexOf("as ") + 3, iModel.getCommonName().length()).equalsIgnoreCase(ingredientName)) {
                            return iModel;
                        }
                    } else if (iModel.getCommonName().trim().equalsIgnoreCase(ingredientName) && !euivalance) {
                        return iModel;
                    }

                }

                if (!iModel.getEquivalentValue().equals("") && euivalance) {
                    /* String equiVal = iModel.getEquivalentValue();
                    if (equiVal.substring(equiVal.indexOf(".") + 1, equiVal.indexOf("|")).trim().equalsIgnoreCase(ingredientName)) {
                        return iModel;
                    }*/
                    String equiVal = iModel.getEquivalentValue();
                    if (equiVal != null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") < 0) {

                        String singleEquival = getEquivalentIngredientName(equiVal);
                        if (singleEquival != null && singleEquival.equalsIgnoreCase(ingredientName)) {
                            return iModel;
                        }
                    }
                    if (equiVal != null && !equiVal.trim().equalsIgnoreCase("") && equiVal.indexOf("||") > 0) {
                        //System.out.println("multiple....." + equiVal);
                        String[] multiEquival = equiVal.split("\\|\\|");
                        for (String singleEqui : multiEquival) {
                            // System.out.println("after split....." + singleEqui);

                            String equi = getEquivalentIngredientName(singleEqui);
                            if (equi != null && equi.equalsIgnoreCase(ingredientName)) {
                                return iModel;
                            }
                        }

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String addPipeToQty(String qty) {
        try {
//            for (Tblproductingredientassoc ingAssoc : qtyList) {
//                if (!ingAssoc.getVQtyUnit().equals("") && qty.indexOf(ingAssoc.getVQtyUnit()) > 0) {
//                    if (ingAssoc.getVQtyUnit().equals("g") && !qty.contains("mg") && !qty.contains("mcg") && !qty.contains("ng")) {
//                        StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(ingAssoc.getVQtyUnit()) - 1));
//                        stringBuilder.append("|");
//                        stringBuilder.append(ingAssoc.getVQtyUnit());
//                        return stringBuilder.toString();
//                    } else {
//                        StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(ingAssoc.getVQtyUnit()) - 1));
//                        stringBuilder.append("|");
//                        stringBuilder.append(ingAssoc.getVQtyUnit());
//                        return stringBuilder.toString();
//                    }
//                }
//                //mg,ng,mcg
//            }
            if (qtyList.size() > 0) {
                Iterator it = qtyList.iterator();
                while (it.hasNext()) {
                    Object objQty = it.next();
                    if (objQty != null) {

                        String strQty = objQty.toString();
                        // System.out.println(qty.indexOf(strQty) + " " + strQty + "legnth of qty " + qty.length() + "Qty itself " + qty);
                        if (strQty != null && !strQty.equals("") && ((qty.indexOf(strQty) > 0 && !strQty.equals("g")))) {
                            StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(strQty)));
                            stringBuilder.append("|");
                            stringBuilder.append(strQty);
                            return stringBuilder.toString();
                        } else if (strQty != null && !strQty.equals("") && (qty.indexOf(strQty) == qty.length() - 1 && strQty.equals("g"))) {
                            StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(strQty)));
                            stringBuilder.append("|");
                            stringBuilder.append(strQty);
                            return stringBuilder.toString();
                        }
                    }

//            for (int i = 0; i < qtyList.size(); i++) {
//                if (qtyList.get(i).toString().equals("") && qty.indexOf(qtyList.get(i).toString()) > 0) {
//                    StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(qtyList.get(i).toString()) - 1));
//                    stringBuilder.append("|");
//                    stringBuilder.append(qtyList.get(i).toString());
//                    return stringBuilder.toString();
//                } else {
//                    StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(qtyList.get(i).toString()) - 1));
//                    stringBuilder.append("|");
//                    stringBuilder.append(qtyList.get(i).toString());
//                    return stringBuilder.toString();
//                }
//            }
                }

            } else {
                for (String strQty : qtyStrList) {

                    if (strQty != null && !strQty.equals("") && ((qty.indexOf(strQty) > 0 && !strQty.equals("g")))) {
                        StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(strQty)));
                        stringBuilder.append("|");
                        stringBuilder.append(strQty);
                        return stringBuilder.toString();
                    } else if (strQty != null && !strQty.equals("") && ((qty.indexOf(strQty) > 0 && qty.indexOf(strQty) == qty.length() - 1) && (!qty.contains("mg") && !qty.contains("mcg") && !qty.contains("ng")))) {//if (strQty != null && !strQty.equals("") && (qty.indexOf(strQty) == qty.length() - 1 && strQty.equals("g"))) {
                        StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(strQty)));
                        stringBuilder.append("|");
                        stringBuilder.append(strQty);
                        return stringBuilder.toString();
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void setParentIdForAssoc(IngredientsModel[] ingredientsJsonArray) {
        Map<String, String> prodIngQueryMap = new TreeMap<String, String>();
        Map<String, Integer> prodIngAssocMap = new TreeMap<String, Integer>();
        Map<String, String> prodQueryMap = new TreeMap<String, String>();
        List<Tblproductingredients> result = null;
        List<Tblproductingredients> equivResult = null;
        List<Tblproductingredientassoc> assocResultMain = null;
        List<Tblproductingredientassoc> assocResultEquiv = null;
        List<Tblproduct> prodResult = null;
        int i = 0;
        piAssocDao.beginTransaction();
        for (IngredientsModel iModel : ingredientsJsonArray) {

            try {
                if (!iModel.getEquivalentValue().equals("")) {
                    String equiVal = iModel.getEquivalentValue();
                    String equivIng = equiVal.substring(equiVal.indexOf(".") + 1, equiVal.indexOf("|")).trim();
//                    if (!iModel.getIngredientName().trim().equals("")) {
//                        prodIngQueryMap.put("vName", iModel.getIngredientName());
//                    } else {
//                        prodIngQueryMap.put("vName", iModel.getIngredientScientific());
//                    }
                    if (!iModel.getIngredientName().trim().equals("")) {
                        if (iModel.getIngredientName().trim().startsWith("as ")) {
                            String ingName = iModel.getIngredientName().substring(iModel.getIngredientName().indexOf("as ") + 3, iModel.getIngredientName().length());
                            System.out.println("was as removed?" + ingName);
                            prodIngQueryMap.put("vName", ingName);
                        } else {
                            prodIngQueryMap.put("vName", iModel.getIngredientName());
                        }

                    } else if (!iModel.getIngredientScientific().trim().equals("")) {

                        if (iModel.getIngredientScientific().trim().startsWith("as ")) {
                            String scFCName = iModel.getIngredientScientific().substring(iModel.getIngredientScientific().indexOf("as ") + 3, iModel.getIngredientScientific().length());
                            System.out.println("was as removed?" + scFCName);
                            prodIngQueryMap.put("vName", scFCName);
                        } else {
                            prodIngQueryMap.put("vName", iModel.getIngredientScientific());
                        }
                    }
                    prodQueryMap.put("vProductId", iModel.getProductID());
                    prodResult = productDao.readByNameDQuery("Tblproduct.findByVProductId", prodQueryMap);
                    result = piDao.readByNameDQuery("Tblproductingredients.findByVName", prodIngQueryMap); //get main ing id from
                    for (Tblproduct prod : prodResult) {
                        for (Tblproductingredients mainIng : result) {
                            prodIngAssocMap.put("iIngredientID", mainIng.getIID());
                            prodIngAssocMap.put("iProductID", prod.getIID());
                            assocResultMain = piAssocDao.readByNameDQueryInt("Tblproductingredientassoc.findByIIngredientIDIproductID", prodIngAssocMap);
                            if (assocResultMain.size() > 0) {
                                prodIngQueryMap = new TreeMap<String, String>();
                                prodIngQueryMap.put("vName", equivIng);
                                equivResult = piDao.readByNameDQuery("Tblproductingredients.findByVName", prodIngQueryMap);
                                //break;
                                for (Tblproductingredients equiIng : equivResult) {
                                    prodIngAssocMap.put("iIngredientID", equiIng.getIID());
                                    prodIngAssocMap.put("iProductID", prod.getIID());
                                    assocResultEquiv = piAssocDao.readByNameDQueryInt("Tblproductingredientassoc.findByIIngredientIDIproductID", prodIngAssocMap);
                                    if (assocResultEquiv.size() > 0) {
                                        //       break;
                                        if (assocResultMain != null && !assocResultMain.isEmpty() && assocResultEquiv != null && assocResultEquiv.size() > 0 && assocResultEquiv.get(0).getIParentID() == null) {
                                            assocResultEquiv.get(0).setIParentID(assocResultMain.get(0).getIID());
                                            piAssocDao.create(assocResultEquiv.get(0));
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //assocResultMain = piAssocDao.readByNameDQueryInt("Tblproductingredientassoc.findByIIngredientID", prodIngAssocMap);
//                    prodIngQueryMap = new TreeMap<String, String>();
//                    prodIngQueryMap.put("vName", equivIng);
//                    equivResult = piDao.readByNameDQuery("Tblproductingredients.findByVName", prodIngQueryMap);
//                    for (Tblproduct prod : prodResult) {
//                        for (Tblproductingredients equiIng : equivResult) {
//                            prodIngAssocMap.put("iIngredientID", equiIng.getIID());
//                            prodIngAssocMap.put("iProductID", prod.getIID());
//                            assocResultEquiv = piAssocDao.readByNameDQueryInt("Tblproductingredientassoc.findByIIngredientIDIproductID", prodIngAssocMap);
//                            if (assocResultEquiv.size() > 0) {
//                                break;
//                            }
//                        }
//                    }
//                    prodIngAssocMap = new TreeMap<String, Integer>();
//                    prodIngAssocMap.put("iIngredientID", result.get(0).getIID());
//                    assocResultEquiv = piAssocDao.readByNameDQueryInt("Tblproductingredientassoc.findByIIngredientID", prodIngAssocMap);
//                    if (assocResultMain != null && assocResultMain.size() != 0 && assocResultEquiv != null && assocResultEquiv.size() > 0 && assocResultEquiv.get(0).getIParentID() == null) {
//                        assocResultEquiv.get(0).setIParentID(assocResultMain.get(0).getIID());
//                        piAssocDao.create(assocResultEquiv.get(0));
//                    }
                }
            } catch (Exception e) {
                System.out.println("Exception occurred for " + iModel.getEquivalentValue() + "number " + ++i);
            }
        }
        piAssocDao.commitTransaction();
    }

    private String getEquivalentIngredientName(String equiVal) {
        if (equiVal.indexOf(".") > 0 && equiVal.indexOf("|") > 0) {
            if (equiVal.indexOf("to ") > 0) {
                //System.out.println(equiVal.substring(equiVal.indexOf("to ") + 3, equiVal.indexOf("|")).trim());
                //ingList.add(equiVal.substring(equiVal.indexOf("to ") + 3, equiVal.indexOf("|")).trim());
                return equiVal.substring(equiVal.indexOf("to ") + 3, equiVal.indexOf("|")).trim();
            } else {
                //System.out.println(equiVal.substring(equiVal.indexOf(". ") + 2, equiVal.indexOf("|")).trim());
                // ingList.add(equiVal.substring(equiVal.indexOf(". ") + 2, equiVal.indexOf("|")).trim());
                return equiVal.substring(equiVal.indexOf(". ") + 2, equiVal.indexOf("|")).trim();
            }

        } else if (equiVal.startsWith("standardized to")) {
            //System.out.println(equiVal.substring(equiVal.indexOf("standardized to") + "standardized to".length(), equiVal.indexOf("|")).trim());
            //ingList.add(equiVal.substring(equiVal.indexOf("standardized to") + "standardized to".length(), equiVal.indexOf("|")).trim());
            return equiVal.substring(equiVal.indexOf("standardized to") + "standardized to".length(), equiVal.indexOf("|")).trim();
        }
        return null;
    }
}
