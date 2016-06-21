package chilangolabs.androidbiachack.entitys;

/**
 * Created by Gorro on 21/06/16.
 */
public class ItemPerfiles {

    String name, type;
    int progress;

    public ItemPerfiles(String name, String type, int progress) {
        this.name = name;
        this.type = type;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
