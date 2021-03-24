public class Player {
    int ID;
    String name;

    public void setName(String name){
        this.name = name;
        this.ID = ID++;
    }

    public String getName(){
        return name;
    }
}
