package com.example.administrator.coursetable;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/19.
 */
public class myDBUtil extends SQLiteOpenHelper{
    private String createCourseTable="create table if not exists course(courseId,courseName)";


    public myDBUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createCourseTable);
        for(int i=1;i<=40;i++){
            db.execSQL("insert into course(courseId,courseName)values(?,?)",
                    new String[]{i+""," "});
        }

    }

    public String getCourseById(SQLiteDatabase db,String courseId){//根据id找到课程
        Cursor cursor=db.rawQuery("select * from course where courseId=?",
                new String[]{courseId});
        if(cursor.moveToNext()){
            return cursor.getString(cursor.getColumnIndex("courseName"));
        }
        return " ";

    }
    public void updateCourseName(SQLiteDatabase db,String courseId,String newcourseName){
        db.execSQL("update course set courseName=? where courseId=?",
                new String[]{newcourseName,courseId});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
