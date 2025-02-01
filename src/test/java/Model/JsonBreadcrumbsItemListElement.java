package Model;

public class JsonBreadcrumbsItemListElement {

    private String type;
    private String position;
    private Object item;
    private String id;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // олучили объект item
    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
         this.item = item;
    }

}
