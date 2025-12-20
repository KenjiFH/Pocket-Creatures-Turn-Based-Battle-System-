//A pokemon Class is a Pokemon that can battle other Pokemon, the class contains variables about the stats of a pokemon, and also
//methods that it uses



//damage = atk + power of move

import java.io.IOException;


public class Pokemon {
    private int hp;
    private int spe;
    private int atk;
    private String name;
    private String type;
    private String imagePath;
    private int maxHP;
    //fnt is fainted or if it is able to battle
    private boolean fnt = false;

    protected Move [] moveset;
   

    //TODO) remember to change copy constructor when you add a new variable
    public Pokemon(int hp, int spe, int atk, String name, String type, String imagePath, Move [] moveset) throws IOException { 
    
    this.maxHP = hp;
    
    this.hp = hp;
    this.spe = spe;
    this.atk = atk;
    this.name = name;
    this.imagePath = imagePath;
    this.type = type;
    this.moveset = moveset;
    }
   //copy contructor
    public Pokemon(Pokemon otherPokemon){
      this.maxHP = otherPokemon.maxHP;
      this.hp = otherPokemon.hp;
      this.spe = otherPokemon.spe;
      this.atk = otherPokemon.atk;
      this.name = otherPokemon.name;
      this.imagePath = otherPokemon.imagePath;
      this.type = otherPokemon.type;
      this.fnt = otherPokemon.fnt;
      this.moveset = otherPokemon.moveset;
    }

       

 

    public String toString(){
      return this.name + " max hp: " + this.maxHP + " hp:" + this.hp + " atk: " + this.atk + " spd: " + this.spe + " fnt status is:" + this.fnt + "moveset " + this.moveset;
    }

    public boolean equals(Pokemon otherMon){
      if(this.hp == otherMon.hp && this.spe == otherMon.spe && this.atk == otherMon.atk && this.name.equals(otherMon.name)){
        return true;
      }

      return false;
    }

    public int getMaxHP(){
      System.out.println("max hp is " + this.maxHP);
      return this.maxHP;
    }


    // Getter and Setter for hp
    public int getHp() {
      return this.hp;
  }

  public void setHp(int hp) {
      this.hp = hp;
  }

  // Getter and Setter for spe
  public int getSpe() {
      return this.spe;
  }

  public void setSpe(int spe) {
      this.spe = spe;
  }

  // Getter and Setter for atk
  public int getAtk() {
      return this.atk;
  }

  public void setAtk(int atk) {
      this.atk = atk;
  }

  // Getter and Setter for name
  public String getName() {
      return this.name;
  }

  public void setName(String name) {
      this.name = name;
  }

  // Getter and Setter for type
  public String getType() {
      return type;
  }

  public void setType(String type) {
      this.type = type;
  }

  public String getFilePath(){
    return this.imagePath;
  }

  


  public void Attack(Pokemon attacker){
    this.hp -= attacker.getAtk();
    
  }

  public boolean getFntStatus(){
    return this.fnt;
  }

  public void setFntStatus(boolean newStatus){
    this.fnt = newStatus;
  }

  







}

