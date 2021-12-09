package failmenzhumys;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FailmenZhumys {
private static final List<Derekter> der = new ArrayList<>(); // Derekter klasyn turakty der atauymen private modif. arkyly 
// turaktu ArrayLiat retinde shakyru

    public static void main(String[] args)throws IOException {

            loadDerekter(); // faildan derekterdi oku
            
            Scanner sc = new Scanner(System.in); // engizu operatoru
                
                System.out.println(" *********************** - > Телефон анықтамалығы <- ************************ ");
                System.out.println(" Әрекетті таңдаңыз: (add) деректерді қосу,(find) деректерді іздеу, " //menu
                + "(del)деректерді жою, (derekter) толық тізімді көрсету,\n (save)сақтау, (exit)шығу, (sort) сұрыптау, (filter) филтрмен іздеу ");
                
                String act;
                act = sc.next();
                while(!"exit".equals(act)){ //while operatoryn koldany arkyly (menu) zhasay
            
                        if(act.equals("add")){
                        System.out.print("ID: ");
                        String id; 
                        id = sc.next();
                        while(!tekseru0(id)){
                        System.out.println(" ?Kate engizu kaita engiziniz... ");
                        id = sc.next();
                        }

                        System.out.print("Famiasyn zhazynyz: ");
                        String fam;
                        fam = sc.next();
                        while(!tekseru1(fam)){
                        System.out.println(" Kate engizu Axxx... turinde kaita engiziniz... ");
                        fam = sc.next();
                        }
                        
                        System.out.print("Atyn zhazynyz: ");
                        String aty; 
                        aty = sc.next();
                         while(!tekseru1(aty)){
                            System.out.println(" Kate engizu Axxx... turinde kaita engiziniz... ");
                            aty = sc.next();
                        }
                        
                        System.out.print("Nomer zhazynyz: ");
                        String nomer; 
                        nomer = sc.next();
                        while(!tekseru(nomer)){
                            System.out.println(" Nomer kate!!! +7 7XX XXX XX XX turinde kaita engiziniz... ");
                            nomer = sc.next();
                        }
                        
                        System.out.print("Adres zhazynyz: ");
                        String adres; 
                        adres = sc.next();
                        while(!tekseru2(adres)){
                            System.out.println("Kate engizildi ----> Qala/koshe/ui_nomeri turinde zhaziniz");
                            adres = sc.next();
                        }
                        addDerekter(id,fam,aty,nomer,adres); //addPB funksiasyna alyngan derekterdi ArrayList-ka kosu 
                    }
                        else { 
                            if ("find".equals(act)){
                                System.out.println("Derekterdi izdeu: ");
                                System.out.println("1. ID arkyly");
                                System.out.println("2. Aty arkyly");
                                System.out.println("3. Nomeri arkyly");
                                
                                String a = sc.next();
                                    switch(a){ // tandau operatory arkyly derekterdi birneshe zholmen izdeu
                                        case "1": System.out.print("ID: ");
                                                 String id = sc.next();
                                                 System.out.println(findDerekter(id)); // findDerekter funksiasy arkyly engizilgen derek boiynsha izdeu
                                                 break; // kelesi case otip ketpeui ushin
                                        case "2": System.out.print("Atyn zhazynyz: ");
                                                String aty = sc.next();         
                                                System.out.println(findDerekter(aty));
                                                break;
                                        case "3": System.out.print("Nomer zhazynyz: ");
                                                String nomer = sc.next();        
                                                System.out.println(findDerekter(nomer));
                                                break;
                                        default : System.out.println(" Kate engizildi " );
                                                  break;  

                                    }
                            } 
                            else{
                                if(act.equals("del")){
                                    System.out.println("Nomer zhazynyz:");
                                    String nomer = sc.next();
                                    delDerekter(nomer); // delDerekter funksiasy arkyly nomer boinsha izdep tolyk ArrayListten oshiru
                                }
                                else{
                                     if(act.equals("save")){
                                        saveDerekter(); // Engizilgen, kosylgan barlyk tizimdi failga saktau
                                     }
                                     else{
                                         if(act.equals("derekter")){ // ArrayListtegi baklyk tizimdi shygaru
                                             System.out.println(der);
                                         }else{
                                             if(act.equals("sort")){
                                                 Comparator fam = new Suruptau();
                                                 Collections.sort(der, fam);
                                                 System.out.println(der);
                                             }else{
                                                 if(act.equals("filter")){
                                                     System.out.print(" Bir arip zhaz :");
                                                     String s = sc.next();
                                                     List<String> filter = new ArrayList<>();
                                                     der.stream().filter((k) -> (k.getnomer() == null ? s == null : k.getaty().contains(s))
                                                     ).forEachOrdered((k) -> {
                                                         filter.add(k.toString());
                                                     });
                                                    if (filter.isEmpty()) System.out.println("Korsetilgen malimet boiynsha -------------> DEREKTER TABYLMADY!!! ");// tabylmasa
                                                    else System.out.println(filter.toString());
                                                 }
                                             }
                                         }
                                     }
                                }
                            }
                        }
                        // kate engizilu baikalsa menu kaita shygaru
        System.out.println("әрекетті таңдау: (add) деректерді қосу, (del)деректерді жою, (find) деректерді іздеу, (derekter) толық тізімді көрсету,\n "
                                + "(save)сақтау, (exit)шығу, (sort) сұрыптау, (filter) филтрмен іздеу");
                    act = sc.next();       
                }
    }
 
    public static boolean tekseru(String s){ 
        Pattern p = Pattern.compile("[+ .][7]{1}[7]{1}[0-9]{9}"); // +77xx xxx xx xx turinde zhazyluyn tekseru
        //[+ .][7]{1}[7]{1}[0-9]{9} 
      // [8]{1}[7]{1}[0-9]{9}     
        Matcher m = p.matcher(s);
        return(m.matches());
    }
    public static boolean tekseru1(String d){
     Pattern pattern = Pattern.compile("[A-Z].{3,}"); // atyn zhane familiasyn durys zhazyluyn tekseru
     Matcher matcher = pattern.matcher(d);
     return(matcher.matches());
    }
    public static boolean tekseru0(String z){
     Pattern pattern = Pattern.compile("[1-9].{0,}"); // Id tekseru funksiasy
     Matcher matcher = pattern.matcher(z);
     return(matcher.matches());
    }
    public static boolean tekseru2(String g){
     Pattern pattern = Pattern.compile("[A-Z].{4,}/[A-Z].{3,}/[1-9]{1,}"); //Adresti tekseru
     Matcher matcher = pattern.matcher(g); // Aaaaa/Aaaa/00
     return(matcher.matches());
    }

    private static void addDerekter(String id, String fam, String aty, String nomer, String adres) { // kosu kaitymsyz funksiasy
        der.add(new Derekter(id,fam,aty,nomer,adres)); // engizilgen malimetterdi tolyktai (der) ArrayListke kosu
        System.out.println(" Derekter kosyldy!!! \n");
    }
        
    private static String findDerekter(String d) { // izdeu kaitymdy funksiasy
        List <String> result = new ArrayList<>(); // izdeudi zhyrgizu ushin zhana ArrayList (result) ashu
            der.stream().filter((k) -> (k.getnomer() == null ? d == null : k.getnomer().equals(d)) || //( stream filter) derekter suzuden otkizu zhane shartka 
                    //saikes kelse otkizu
               (k.getid() == null ? d == null : k.getid().equals(d)) ||
               (k.getaty() == null ? d == null : k.getaty().equalsIgnoreCase(d))  
               ).forEachOrdered((k) -> { // agynnyn kez-kelgen elementi usin oryndau kerek kezde
           result.add(k.toString()); // Arraylist tabylsa ony (result)-ka kosu
          });
    if (result.isEmpty()) result.add("Korsetilgen malimet boiynsha -------------> DEREKTER TABYLMADY!!! ");// tabylmasa
        return result.toString(); // tabylsa
    }
    
    public static void loadDerekter() throws IOException { // faildan ferekter oku zhane ony (der) Arraylist kosu
        File file = new File("C:\\Users\\user\\OneDrive\\Рабочий стол\\Fail-1.txt");
        if(!file.exists()) // eger fail tabylmasa -> zhanasyn ashy
            file.createNewFile();
        
        if (file.exists()){
                try (BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Users\\user\\OneDrive\\Рабочий стол\\Fail-1.txt")))) {
                    // try erekshelik nemese katelik boluy mumkin kody ushin
                    // failmen zhumys zhasau barysynda (reader) faildagy derekterdi oku
                    String line;
                    while ((line = reader.readLine()) != null) { //derekterdi tolyk oku
                        
                        String[] dat = line.split(" "); // ar probel aldyndagy elementti oku
                        der.add(new Derekter(dat[0],dat[1],dat[2],dat[3],dat[4])); // ArrayList-ke okulgan derekterdi kosu
                        
                    }
                }
             
            catch(IOException e){
                // catch erekshelikti ondeu nemese catch IOException zhoiu kodyn zhasay
            System.out.println("Error: " + e);
        }
        }
    }
    
   private static void delDerekter(String nomer) { // engizilgen derek boiynsha derekterdi (der)-der oshiru
       int n = 0;
    Iterator<Derekter> derIterator = der.iterator(); // iterator() osy tizimde iteratordy durys retpen aly ushin koldanylady
    // iterator kollecsiadagy barlyk elementterdi suryptaushy
    while(derIterator.hasNext()) {
// hasnext() logikalyk funksia bolyp tabylady ol kelesi bar n/e zhogyn anyktau kollecsia sonyna zhetkenin bilu ushin
       Derekter nextDer = derIterator.next(); // kelesi elementke otu ushin next() funksiasy
       if (nextDer.getnomer().equals(nomer)){
           derIterator.remove(); // tizimdegi katardy zhoiu
           n++;
       }
       
    }
        if(n > 0){ // oryndalsa
            System.out.println(" ------> Derekter oshirildi!!! ");
        }
        else{ // orundalmasa
            System.out.println(" ------> Derekter kate engizildi nemese abonent tabylmady ??!?? ");
        }
        
    }
   
    private static void saveDerekter() throws IOException{ // derekterdi failga saktau
         
            try (PrintWriter writer = new PrintWriter(new File("C:\\Users\\user\\OneDrive\\Рабочий стол\\Fail-1.txt"))) {
                // writer failga zhazu
                der.forEach((o) -> {
                    writer.write(o.getid()+" "+o.getfam()+" "+o.getaty()+" "+o.getnomer()+" "+o.getadres()+System.lineSeparator());
                    // line.Separator kelesi zholdy bolu uhin
                });
                 System.out.println("Derekter saktaldy!!!\n");
            }
          
        catch(IOException e){
            System.out.println("Error: " + e);
        }
    }
}

