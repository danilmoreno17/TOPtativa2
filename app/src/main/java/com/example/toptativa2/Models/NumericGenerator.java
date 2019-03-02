package com.example.toptativa2.Models;

import java.util.*;
import java.nio.charset.*;

public class NumericGenerator {

    public static String getNumers(int n){
        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

    public static String getNumericList(ArrayList<String> actual,int longitud){
        String listado ="";

        for(int i=0;i<actual.size();i++){
            String numero = getNumers(longitud);
            if(actual.get(i).equals(numero)){
                i=actual.size();
            }else{
                listado=numero;
            }
        }

        return listado;
    }

}
