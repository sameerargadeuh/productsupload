/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unityhealth;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sameer
 */
public class TestTry {

    public static void main(String args[]) {
        List<String> strQtyLst = new ArrayList<String>();
        List<String> strQtyLst2 = new ArrayList<String>();
        String strQty1 = "153g";
        String strQty2 = "200mg";
        String strQty3 = "2500mcg";
        //String strQty = "g";
        //String qty = strQty3;
        strQtyLst.add(strQty1);
        strQtyLst.add(strQty2);
        strQtyLst.add(strQty3);
        strQtyLst2.add("g");
        strQtyLst2.add("mg");
        strQtyLst2.add("mcg");
        System.out.println("strQty1 index of g " + strQty1.indexOf("g") + " length of strQty " + strQty1.length());
        System.out.println("strQty2 index of g " + strQty2.indexOf("g") + " length of strQty2 " + strQty2.length());
        System.out.println("strQty3 index of g " + strQty3.indexOf("g") + " length of strQty3 " + strQty3.length());
        for (String strQty : strQtyLst2) {
            for (String qty : strQtyLst) {
                if (strQty != null && !strQty.equals("") && ((qty.indexOf(strQty) > 0 && qty.indexOf(strQty) < qty.length() - 1))) {
                    StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(strQty)));
                    stringBuilder.append("|");
                    stringBuilder.append(strQty);
                    System.out.println(stringBuilder.toString());
                }
                if (strQty != null && !strQty.equals("") && ((qty.indexOf(strQty) > 0 && qty.indexOf(strQty) == qty.length() - 1 )&& (!qty.contains("mg") && !qty.contains("mcg") && !qty.contains("ng")))) {
                    StringBuilder stringBuilder = new StringBuilder(qty.substring(0, qty.indexOf(strQty)));
                    stringBuilder.append("|");
                    stringBuilder.append(strQty);
                    System.out.println(stringBuilder.toString());
                }
            }

        }

    }
}
