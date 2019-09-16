package com.example.sqlite_me;

public class Student {
    public static final String TABLE_NAME="Student";

    public static final String COLUMN_NAME="name";
    public static final String COLUMN_ID ="id";
    public static final String COLUMN_BRANCH="branch";
    public static final String COLUMN_SID ="sid";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME  + " TEXT," +

            COLUMN_SID + " TEXT," +
            COLUMN_BRANCH + " TEXT)";
    private int id;
    private  String name,branch,sid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColumnName() {
        return COLUMN_NAME;
    }

    public static String getColumnId() {
        return COLUMN_ID;
    }

    public static String getColumnBranch() {
        return COLUMN_BRANCH;
    }

    public static String getColumnSid() {
        return COLUMN_SID;
    }

    public static String getCreateTable() {
        return CREATE_TABLE;
    }

    public Student() {

    }
}

