package failmenzhumys;
public class Derekter{

    private String aty,fam,adres,nomer,id; // koldanyluy kerek tipter
    
    public Derekter(){
        
    }
    public String getid(){ //manderdi kaitaru
        return id;
    }
    public String getaty(){
        return aty;
    }
    public String getfam(){
        return fam;
    }
    public String getadres(){
        return adres;
    }
    public String getnomer(){
        return nomer;
    }
    
    public Derekter(String id, String fam, String aty, String nomer, String adres){
        this.id = id; 
        this.fam = fam;
        this.aty = aty;
        this.nomer = nomer;
        this.adres = adres;
    }
    
       public String toString()
    {
        return "\n" + id + " " +
                fam + " " +
                aty  + " " +
                nomer  + " " +
                adres;
    }  
      
}
