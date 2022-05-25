package com.preflight.seleniumAutohealPlugin;

import org.openqa.selenium.WebElement;

import java.util.Map;

public class SearchElementResult {
    public WebElement webElement;
    public String selector;
    public String elementSimplePath;
    public Boolean isFoundByAutoheal;

    SearchElementResult(Map<String, Object> input) {
        if(input == null){
            return;
        }
        selector = (String)input.get("selector");
        var simplePathObj = input.get("elementSimplePath");
        if(simplePathObj != null){
            elementSimplePath = simplePathObj.toString();
        }
        isFoundByAutoheal = (boolean)input.get("isFoundByAutoheal");
        webElement = (WebElement)input.get("element");
    }
}
