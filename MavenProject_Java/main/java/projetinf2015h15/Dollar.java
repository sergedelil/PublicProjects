package projetinf2015h15;

public class Dollar {

    private int totalCents = 0;
    public Dollar (int dollars, int cents)throws IllegalArgumentException  {
       if(dollars < 0 || cents < 0 || cents > 99){
           throw new IllegalArgumentException();
       }else{
           totalCents = dollars * 100 + cents;
       }
    }
    @Override
    public String toString(){
        return getDollars() + "." + getCents() + "$";
    }
    public int getDollars (){
        return this.totalCents / 100;
    }
    public int getCents(){
        return this.totalCents % 100;
    }
    public int getTotalCents(){
        return this.totalCents;
    }
     public void additionner (Dollar autre){
        this.totalCents += autre.totalCents;
    }
    public void soustraire (Dollar autre) throws OperationInvalideException {
        verifierOperandes(autre);
        this.totalCents -= autre.totalCents;
    }
    public void pourcentage(int taux){
        int valeur = this.totalCents * taux;
        totalCents = (valeur / 100) + (valeur % 100);
    }
    
    public void multiplier (Dollar autre){
        
        this.totalCents = this.totalCents * autre.totalCents;
    
    }
    
    private void verifierOperandes(Dollar autre) throws OperationInvalideException {
	if ( this.totalCents < autre.getTotalCents()) {
            throw new OperationInvalideException("Opération impossible : "
		+ this.totalCents + " - " + autre.totalCents);
	}
    }
}