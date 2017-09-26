package vritika.app.startstuf;

/**
 * Created by akshay on 02-01-2017.
 */

public class Model {

    private String name,phone;
    private boolean selected;

    public Model(String name,String phone) {
        this.name = name;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public String getphone(){ return phone;}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
