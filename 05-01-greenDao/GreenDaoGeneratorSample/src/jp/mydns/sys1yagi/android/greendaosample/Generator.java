/**
 * 
 */
package jp.mydns.sys1yagi.android.greendaosample;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Generator {
    public static void main(String[] args) {
        try {
            Schema schema = new Schema(1, "jp.mydns.sys1yagi.android.greendaosample");
            
            createTodoSchema(schema);
            
            new DaoGenerator().generateAll(schema, "../GreenDaoSample/src");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void createTodoSchema(Schema schema){
        Entity todo = schema.addEntity("Todo");
        Property id = todo.addIdProperty().getProperty();
        todo.addStringProperty("todo");
        todo.addDateProperty("addDate").notNull();
        todo.addDateProperty("endDate");
        todo.addIntProperty("status").notNull().unique();
    }
}
