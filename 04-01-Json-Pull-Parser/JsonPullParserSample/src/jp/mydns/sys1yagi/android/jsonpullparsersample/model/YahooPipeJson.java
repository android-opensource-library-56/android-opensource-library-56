/**
 * 
 */
package jp.mydns.sys1yagi.android.jsonpullparsersample.model;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class YahooPipeJson {

    @JsonKey
    int count;

    @JsonKey
    Value value;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
