package aritmetik;  

import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.Scanner;  

public class Aritmetik {  
  
    static int AX=0,BX=0,CX=0,DX=0;  

    public static int HRK(String op,Integer a) {  
        if (a != null) {  
            switch (op) {  
                case "AX":  
                    AX = a;  
                    break;  
                case "BX":  
                    BX = a;  
                    break;  
                case "CX":  
                    CX = a;  
                    break;  
                case "DX":  
                    DX = a;  
                    break;  
                default:  
                    System.out.println("Gecersiz kayitci: "+op);  
                    return 0;  
            }  
            return a;  
        }  
    
        switch (op) {  
            case "AX":  
                return AX;  
            case "BX":  
                return BX;  
            case "CX":  
                return CX;  
            case "DX":  
                return DX;  
            case "HRK":  
                return 1;  
            default:  
                try {  
                    return Integer.parseInt(op);  
                } catch (NumberFormatException e) {  
                    System.out.println("Operant hatasi: "+op);  
                    return 0;  
                }  
        }  
    }  

    public static int Komutlar(String satir) {  
         
        for (int i=0;i<satir.length();i++) {  
            if (satir.charAt(i)==','&&satir.charAt(i+1)==' ') {  
                System.out.println("Virgulden sonra bosluk hatasi");  
                return 0;  
            }  
        }  

        String[] dizi = satir.split(" ");  
        String komut = dizi[0];  
        String[] operandlar = dizi[1].split(",");  

      
        if (operandlar.length!=2) {  
            System.out.println("Operand sayisi hatali");  
            return 1;  
        }  

        String op1 = operandlar[0].trim();  
        String op2 = operandlar[1].trim();  

      
        if (!(op1.equals("AX") || op1.equals("BX") || op1.equals("CX") || op1.equals("DX"))) {  
            System.out.println(" Operand1 gecersiz: " + op1);  
            return 1;  
        }  

        int operant = HRK(op1, null);  
        int deger = HRK(op2, null);  

      
        if (deger<0) {  
            System.out.println(" Negatif operand hatasi: "+op2);  
            return 1;  
        }  

        switch (komut) {  
            case "BOL":  
                if (deger==0) {  
                    System.out.println("Bolme isleminde sifira bolme hatasi");  
                    yazdir();  
                    return 1;  
                }  
                int sonuc1=operant/deger;  
                if (sonuc1>255) {  
                    System.out.println("Bolme isleminde bit hatasi");  
                    return 1;  
                }  
                HRK(op1,sonuc1);  
                System.out.println("Bolme islemi yapildi");  
                System.out.println("islem: "+operant+" /  "+deger);   
                yazdir();  
                break;  

            case "HRK":  
                HRK(op1,deger);  
                System.out.println("Atama islemi yapildi op="+op1+" deger="+op2);  
                yazdir();  
                break;  

            case "CRP":  
                int sonuc2=operant*deger;  
                if (sonuc2>255) {  
                    System.out.println("Carpma isleminde bit hatasi");  
                    return 1;  
                }  
                HRK(op1,sonuc2);  
                System.out.println("Carpma islemi yapildi");  
                System.out.println("Islem: "+operant+" *  "+deger);   
                yazdir();  
                break;   
        
            case "TOP":  
                int sonuc3=operant+deger;  
                if (sonuc3>255) {  
                    System.out.println("Toplama isleminde bit hatasi");  
                    return 1;  
                }  
                HRK(op1,sonuc3);  
                System.out.println("Toplama islemi yapildi");  
                System.out.println("islem:"+operant+" +  "+deger);   
                yazdir();  
                break;  
        
            case "CIK":  
                int sonuc4=operant-deger;  
                if (sonuc4<0) {  
                    System.out.println("Cikarma isleminde bit hatasi");  
                    return 1;  
                }  
                HRK(op1,sonuc4);  
                System.out.println("Cikarma islemi yapildi");  
                System.out.println("islem:"+operant+" -  "+deger);   
                yazdir();  
                break;  
        }  
        return 0;  
    }  

    public static void yazdir() {  
        System.out.println("Operantlar:AX="+AX+", BX="+BX+", CX="+CX+", DX="+DX);  
    }  

    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
        System.out.print("Dosya adini giriniz:");  
        String dosyaAdi=scanner.next();  
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {  
            String satir;  
            while ((satir=br.readLine())!=null) {  
                int a=-1;  
                a=Komutlar(satir);  
                if (a!=0) {  
                    System.out.println("islem sonlandiriliyor");  
                    break;  
                }  
            }  
            
        } catch (IOException e) {  
            System.out.println("Dosya okuma hatasi: " + e.getMessage());  
        }  
    }  
}