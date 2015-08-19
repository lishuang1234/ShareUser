package com.baidu_lishuang10.appb;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        private TextView mTextView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findViewById(R.id.activity_main_btn).setOnClickListener(this);
            mTextView = (TextView) findViewById(R.id.activity_main_tx);
        }

        @Override
        public void onClick(View v) {
            try {
                //获取程序A的context，之后就可以获取各种资源
                Context ctx = createPackageContext("com.baidu_lishuang10.shareuser", CONTEXT_IGNORE_SECURITY);
                String data = readSetting(ctx);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }


        }

        private String readSetting(Context context) {
            FileInputStream fileInputStream = null;
            InputStreamReader reader = null;
            char[] input = new char[255];
            String data = null;
            try {
                fileInputStream = context.openFileInput("setting.bat");
                reader = new InputStreamReader(fileInputStream);
                reader.read(input);
                data = new String(input);
                mTextView.setText(data);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileInputStream != null)
                        fileInputStream.close();
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return data;
        }

        private void showToast(String s) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }
