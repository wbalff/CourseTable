package com.example.administrator.coursetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private int [] ids=new int[] {
            R.id.row11,R.id.row12,R.id.row13,R.id.row14,R.id.row15,
            R.id.row21,R.id.row22,R.id.row23,R.id.row24,R.id.row25,
            R.id.row31,R.id.row32,R.id.row33,R.id.row34,R.id.row35,
            R.id.row41,R.id.row42,R.id.row43,R.id.row44,R.id.row45,
            R.id.row51,R.id.row52,R.id.row53,R.id.row54,R.id.row55,
            R.id.row61,R.id.row62,R.id.row63,R.id.row64,R.id.row65,
            R.id.row71,R.id.row72,R.id.row73,R.id.row74,R.id.row75,
            R.id.row81,R.id.row82,R.id.row83,R.id.row84,R.id.row85,
    };
    private Button[] btns=new Button[ids.length];
    private myDBUtil myDBUtil;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myDBUtil=new myDBUtil(this,"course.db",null,1);
        db=myDBUtil.getWritableDatabase();
        for(int i=0;i<ids.length;i++){
            btns[i]= (Button) findViewById(ids[i]);
            btns[i].setTag(i+1+"");//标记
            String courseName=myDBUtil.getCourseById(db, i + 1 + "");
            btns[i].setText(courseName);

        }
    }
    public void setCourse(final View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("请输入课程名");
        final EditText editText=new EditText(this);
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String course=editText.getText().toString();
                ((Button)view).setText(course);
                myDBUtil.updateCourseName(db,(String)view.getTag(),course);

            }
        });

        builder.create().show();

    }
}
