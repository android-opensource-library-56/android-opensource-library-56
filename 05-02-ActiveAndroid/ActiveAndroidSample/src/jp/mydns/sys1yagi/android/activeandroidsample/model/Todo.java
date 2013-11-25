package jp.mydns.sys1yagi.android.activeandroidsample.model;

import java.util.Date;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Todo")
public class Todo extends Model {
    public static final String TABLENAME = "Todo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TODO = "todo";
    public static final String COLUMN_ADD_DATE = "addDate";
    public static final String COLUMN_END_DATE = "endDate";
    public static final String COLUMN_STATUS = "status";

    @Column(name = COLUMN_ID)
    public long id;

    @Column(name = COLUMN_TODO)
    public String todo;

    @Column(name = COLUMN_ADD_DATE, notNull = true)
    public Date addDate;

    @Column(name = COLUMN_END_DATE)
    public Date endDate;

    @Column(name = COLUMN_STATUS, notNull = true)
    public int status;

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
