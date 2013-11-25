/**
 * 
 */
package jp.mydns.sys1yagi.android.jsonpullparsersample.model;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

@JsonModel
public class RSS {

    @JsonKey(value = "content:encoded")
    String content;

    @JsonKey(value = "dc:date")
    String date;

    @JsonKey(value = "dc:subject")
    String subject;

    @JsonKey
    String description;

    @JsonKey(value = "hatena:bookmarkcount")
    String bookmarkcount;

    @JsonKey
    String link;
    @JsonKey(value = "rdf:about")
    String about;

    @JsonKey
    String title;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookmarkcount() {
        return bookmarkcount;
    }

    public void setBookmarkcount(String bookmarkcount) {
        this.bookmarkcount = bookmarkcount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
        // return "RSS [content=" + content + ", date=" + date + ", subject=" +
        // subject + ", description=" + description + ", bookmarkcount=" +
        // bookmarkcount + ", link=" + link + ", about=" + about + ", title=" +
        // title + "]";
    }

}