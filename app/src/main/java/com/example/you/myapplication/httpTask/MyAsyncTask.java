package com.example.you.myapplication.httpTask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.you.myapplication.zipcode.ResultEntity;
import com.example.you.myapplication.zipcode.ZipcodeEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by you on 2017/11/24.
 */

public class MyAsyncTask extends AsyncTask<Integer, Integer, ZipcodeEntity> {

    private TextView textView;
    private EditText editText;

    public MyAsyncTask(EditText edittext, TextView textView) {
        this.editText = edittext;
        this.textView = textView;
    }

    @Override
    protected ZipcodeEntity doInBackground(Integer... integers) {

        String zipcode = this.editText.getText().toString();


        Request request = new Request.Builder()
                .url("http://zipcloud.ibsnet.co.jp/api/search?zipcode=" + zipcode)
                .build();
        OkHttpClient client = new OkHttpClient();
        ZipcodeEntity json = null;
        try {
            Response response = client.newCall(request).execute();

            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.readValue(response.body().string(),ZipcodeEntity.class);

            response.body().close();

        } catch (IOException e) {
            Log.e("MyApp", "IOExceptionが発生しました。");
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPostExecute(ZipcodeEntity json) {
        try {
            if (json == null) return;

            StringBuilder builder = new StringBuilder();

            if (json.getResults() != null) {
                for (ResultEntity result : json.getResults()) {

                    builder.append("都道府県：").append(result.getAddress1()).append("\n");
                    builder.append("市区町村名：").append(result.getAddress2()).append("\n");
                    builder.append("町域名：").append(result.getAddress3()).append("\n");
                }
            } else {
                builder.append("該当なし");
            }

            textView.setText(builder.toString());
        } catch (Exception e) {
        }
    }
}