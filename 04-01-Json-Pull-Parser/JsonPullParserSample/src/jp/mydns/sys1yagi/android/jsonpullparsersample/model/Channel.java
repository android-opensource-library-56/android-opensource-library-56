/**
 * 
 */
package jp.mydns.sys1yagi.android.jsonpullparsersample.model;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Channel {
    @JsonKey
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // 省略
}
