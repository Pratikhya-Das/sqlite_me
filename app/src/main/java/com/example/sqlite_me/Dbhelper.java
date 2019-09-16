package com.example.sqlite_me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.sqlite_me.Student.COLUMN_BRANCH;
import static com.example.sqlite_me.Student.COLUMN_ID;
import static com.example.sqlite_me.Student.COLUMN_NAME;
import static com.example.sqlite_me.Student.COLUMN_SID;
import static com.example.sqlite_me.Student.TABLE_NAME;

public class Dbhelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Student.db";
    private Context context;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Student.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);
              onCreate(sqLiteDatabase);

    }

    public long insertStudent(String name, String sid, String branch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Student.COLUMN_NAME, name);
        cv.put(Student.COLUMN_SID, sid);
        cv.put(Student.COLUMN_BRANCH, branch);



        long id = db.insert(Student.TABLE_NAME, null, cv);
        db.close();

        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        return id;

    }

    public int getStudentCount() {
        String CountQuery = "SELECT * FROM " + Student.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(CountQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public List<Student> getallstudent()
    {
        List<Student> students = new ArrayList<>();
        String Query = "SELECT * FROM " + Student.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex(Student.COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)));
                student.setSid(cursor.getString(cursor.getColumnIndex(Student.COLUMN_SID)));
                student.setBranch(cursor.getString(cursor.getColumnIndex(Student.COLUMN_BRANCH)));
                students.add(student);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return students;
    }
    public int  updateStudent(Student student)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Student.COLUMN_NAME,student.getName());
        values.put(Student.COLUMN_SID,student.getSid());
        values.put(Student.COLUMN_BRANCH,student.getBranch());
        return db.update(TABLE_NAME,values, COLUMN_ID + " = ?",new String[]{String.valueOf(student.getId())});
    }
    public void deleteStudent(Student student)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Student.TABLE_NAME, COLUMN_ID + " = ?",new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public Dbhelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
}
