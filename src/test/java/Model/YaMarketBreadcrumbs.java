package Model;

import java.util.List;

public class YaMarketBreadcrumbs {

    private String type;
    private String context;
    private List<JsonBreadcrumbsItemListElement> itemListElement;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<JsonBreadcrumbsItemListElement> getItemListElement() {
        return itemListElement;
    }

    public void setItemListElement(List<JsonBreadcrumbsItemListElement> itemListElement) {
        this.itemListElement = itemListElement;
    }

}
