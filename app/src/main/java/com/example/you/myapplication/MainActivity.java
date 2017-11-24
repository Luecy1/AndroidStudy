package com.example.you.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.you.myapplication.httpTask.MyAsyncTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ボタン
        Button button = findViewById(R.id.button);

        // 入力値
        final EditText editText = findViewById(R.id.editText);

        // テキストビュー
        final TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateZipcode(editText.getText().toString())) {

                    // 非同期処理で実行
                    MyAsyncTask task = new MyAsyncTask(editText, textView);
                    task.execute();

                } else {
                    Toast.makeText(MainActivity.this, "郵便番号を7桁で入力してください。",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * 郵便番号の検証を行います
     * @param zipcode
     * @return true OK
     * false NG
     */
    private static boolean validateZipcode(String zipcode){

        if (zipcode == null) {
            return false;
        }

        if (zipcode.matches("[0-9]{7}")) {
            return true;
        } else {
            return false;
        }

    }
}
