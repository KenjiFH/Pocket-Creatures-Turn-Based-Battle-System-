
//Item is an abstract class that is overriden by specific items to perform a task.

public abstract class ItemBase {
    
    protected String name;
    protected int value;

    public ItemBase(String name, int value){
        this.name = name;
        this.value = value;
    }

    public ItemBase(ItemBase otherBase){
        this.name = otherBase.name;
        this.value = otherBase.value;
    }


    //useItems functionality depends on the item, for example you pass the pokemon in, and if the item is HP, the 
    //hp +++ or if it is damage related dmg ++
   
    public abstract void useItem(Pokemon user);
        
    //use map for key (item) and amount(value)
     

    public String toString(){
        return this.name;
    }
    
}


