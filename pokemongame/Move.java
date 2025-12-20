public class Move {
    private int power;
    private String type;
    private String name;

    public Move(int power, String type, String name){
        
        this.power = power;
        this.name = name;
        this.type = type;

    }


    public String toString(){
        return "power " + this.power + " name: " + this.name + " type " + this.type;
    }

    public int getPower(){
        return this.power;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public String getAnimationPath(){

       switch (this.type) {
        case "fire":
            
            return "images/attacks/fire.png";
           
        case "water":
             
            return "images/attacks/splash.png";
            
        case "grass":
             
             return "images/attacks/green.png";
        
       }

       return "images/attacks/scratch.png";

    }
    
}
