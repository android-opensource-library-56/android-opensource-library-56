/**
 * 
 */
package jp.mydns.sys1yagi.android.jsonpullparsersample.model;

import java.util.List;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class Items {
    @JsonKey
    List<RSS> item;

    @JsonKey
    Channel channel;

    @JsonKey
    String description;

    public List<RSS> getItem() {
        return item;
    }

    public void setItem(List<RSS> item) {
        this.item = item;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
