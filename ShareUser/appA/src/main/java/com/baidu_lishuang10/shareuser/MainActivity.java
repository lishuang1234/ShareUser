package com.baidu_lishuang10.shareuser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        writeSetting(this, "123");
    }

    private void writeSetting(Context context, String data) {
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter writer = null;
        try {
            fileOutputStream = openFileOutput("setting.bat", MODE_PRIVATE);
            writer = new OutputStreamWriter(fileOutputStream);
            writer.write(data);
            writer.flush();
            showToast("saveSuccess");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("saveFail");
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
