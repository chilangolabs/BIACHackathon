package chilangolabs.androidbiachack.entitys;

/**
 * Created by Gorro on 21/06/16.
 */
public class ItemFirstAid {

    String name, descrp;

    public ItemFirstAid(String name, String descrp) {
        this.name = name;
        this.descrp = descrp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }
}
