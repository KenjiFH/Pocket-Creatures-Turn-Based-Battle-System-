public class AtkUp extends ItemBase {

    public AtkUp(ItemBase otherBase) {
        super(otherBase);
        //TODO Auto-generated constructor stub
    }

    public AtkUp(String name, int value){
        super(name,value);
    }

    @Override
    public void useItem(Pokemon user) {
        user.setAtk(user.getAtk() + value);
    }

    

   
    
}
