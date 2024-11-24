/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.co.unad.tallervehicular.util;

import java.util.Collection;

/**
 *
 * @author Charlie
 */
public class ObjectsUtil {
    public static boolean vacio(Object obj){
        if(obj instanceof String){
            return vacio((String)obj);
        }else if(obj instanceof Collection){
            return vacio((Collection)obj);
        }
        return false;
    }
    public static boolean vacio(String texto){
        return texto == null || texto.trim().equals("");
    }
    public static boolean vacio(Collection collect){
        return collect == null || collect.isEmpty();
    }
    public static boolean todosConValor(Object... campos){
        for(Object campo : campos){
            if(vacio(campo)) return false;
        }
        return true;
    }
}
