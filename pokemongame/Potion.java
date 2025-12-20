 public class Potion extends ItemBase{
    




    public Potion(ItemBase otherBase) {
        super(otherBase);
        //TODO Auto-generated constructor stub
    }

    public Potion(String name, int value){
        super(name,value);
    }

    @Override
    public void useItem(Pokemon user) {
        
        user.setHp(user.getHp() + this.value);
        
        if(user.getHp() > user.getMaxHP()){
            user.setHp(user.getMaxHP());
        }

    }
    
  

}
