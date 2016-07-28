package com.example;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * 数据库表生成工具
 */
public class GreenDaoGenerator {
    /**
     * Dao和module生成路径
     */
    public static final String PATH = System.getProperty("user.dir")+ File.separator+"app/src/main/java";

    public static void main(String[] args) throws Exception {
        //我们创建了一个Schema,第一个参数是数据库的版本号，第二个参数是我们要生成的数据模型所在的包名。
        //生成的实体类所存放的位置
        Schema schema = new Schema(5, "flux.lastbus.com.easysobuy.database.module");
        //生成的dao类所存放的位置
        schema.setDefaultJavaPackageDao("flux.lastbus.com.easysobuy.database.dao");
        schema.enableKeepSectionsByDefault();
        schema.enableActiveEntitiesByDefault();
//        addEntity(schema);

        addEntitySearch(schema);
        addEntityUsers(schema);
        addEntitySearchNetKey(schema);

        //第一个参数是“Schema”，第二个参数为将生成的数据模型输出到指定的项目下的src文件夹下。
        //设置生成的文件输出到那个项目的java目录下
        new DaoGenerator().generateAll(schema, PATH);
    }

    private static void addEntity(Schema schema) {

        Entity patient = schema.addEntity("Patient");
        patient.addIdProperty().primaryKey();
        patient.addStringProperty("PATIENT_ID");
        patient.addStringProperty("PARENT_DOCTOR_ID");
        patient.addStringProperty("SUPER_DOCTOR_ID");
        // 指定自增长主键
        Property patientPK = patient.addLongProperty("primary_id").getProperty();
        patient.addToOne(patient, patientPK);

        patient.implementsSerializable();
        patient.setActive(true);
    }

    /**
     * 搜索表
     * @param schema
     */
    private static Entity addEntitySearch(Schema schema){
        Entity search = schema.addEntity("SearchDB");

        search.addIdProperty().getProperty();
        //用户搜索记录
        search.addStringProperty("searchKeyWord");
        search.implementsSerializable();
        return search;
    }

    /**
     * 网络热门搜索
     * @param schema
     * @return
     */
    private static Entity addEntitySearchNetKey(Schema schema){
        Entity searchNetKey = schema.addEntity("SearchNetKeyDB");
        searchNetKey.implementsSerializable();

        searchNetKey.addIdProperty();
        searchNetKey.addStringProperty("key");
        return searchNetKey;
    }

    /**
     * 用户表&IM用户信息
     * @param schema
     */
    public static void addEntityUsers(Schema schema){
        Entity users = schema.addEntity("UsersDB");
        users.implementsSerializable();

        users.addStringProperty("key").notNull();
        users.addStringProperty("userName").notNull();
        users.addStringProperty("uid").primaryKey();
        users.addDateProperty("lastTime");

        Entity imInfo = schema.addEntity("UserInfoDB");
        imInfo.implementsSerializable();

        imInfo.addStringProperty("member_id").primaryKey();
        imInfo.addStringProperty("member_name");
        imInfo.addStringProperty("member_avatar");
        imInfo.addStringProperty("store_name");
        imInfo.addStringProperty("grade_id");
        imInfo.addStringProperty("store_id");
        imInfo.addStringProperty("seller_name");

        Property property = imInfo.addStringProperty("uid").getProperty();
        imInfo.addToOne(users, property);

        Property member_id = users.addStringProperty("member_id").getProperty();
        users.addToOne(imInfo, member_id).setName("info");
    }

}
