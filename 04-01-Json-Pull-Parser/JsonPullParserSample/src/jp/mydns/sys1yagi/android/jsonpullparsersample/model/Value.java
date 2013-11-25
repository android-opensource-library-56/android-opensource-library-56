/**
 * 
 */
package jp.mydns.sys1yagi.android.jsonpullparsersample.model;

import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Value {

    @JsonKey
    String callback;

    @JsonKey
    String description;

    @JsonKey
    String generator;

    @JsonKey
    List<Items> items;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Value [callback=" + callback + ", description=" + description
                + ", generator=" + generator + ", items=" + items + "]";
    }
}
