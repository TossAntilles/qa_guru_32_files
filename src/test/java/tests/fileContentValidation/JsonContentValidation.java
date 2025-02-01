package tests.fileContentValidation;

import Model.YaMarketBreadcrumbs;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

public class JsonContentValidation {

    @Test
    @DisplayName("Проверяем JSON хлебных крошек товара в Яндекс.Маркете")
    void checkJsonValues() throws Exception {
        try (FileInputStream fis = new FileInputStream("src/test/resources/yaMarketBreadcrumbs.json")){
            ObjectMapper objectMapper = new ObjectMapper();
            YaMarketBreadcrumbs breadcrumbs = objectMapper.readValue(fis, YaMarketBreadcrumbs.class);
            Assertions.assertEquals("BreadcrumbList", breadcrumbs.getType());
            Assertions.assertEquals("https://schema.org", breadcrumbs.getContext());

            Assertions.assertEquals("ListItem", breadcrumbs.getItemListElement().get(0).getType());
            Assertions.assertEquals("1", breadcrumbs.getItemListElement().get(0).getPosition());
            Assertions.assertEquals("{id=/catalog--tovary-dlia-zhivotnykh/54496?hid=90813&track=peaces, name=Товары для животных}", breadcrumbs.getItemListElement().get(0).getItem().toString());

            Assertions.assertEquals("ListItem", breadcrumbs.getItemListElement().get(1).getType());
            Assertions.assertEquals("2", breadcrumbs.getItemListElement().get(1).getPosition());
            Assertions.assertEquals("{id=/catalog--odezhda-i-obuv-dlia-zhivotnykh/55108/list?hid=6077297&track=peaces, name=Одежда и обувь для животных}", breadcrumbs.getItemListElement().get(1).getItem().toString());

        };
    }

}
