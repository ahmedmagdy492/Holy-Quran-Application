package com.migo.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.RenderNode;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migo.quran.models.Chapter;
import com.migo.quran.models.ChapterContainer;
import com.migo.quran.util.StaticStrings;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ListView chapterList;
    private ArrayList<Chapter> chapters;

    private void sendGetHttpRequest(String internalPath)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticStrings.API_URI + internalPath)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    ObjectMapper mapper = new ObjectMapper();
                    ChapterContainer chapterContainer = mapper.readValue(response.body().string(), new TypeReference<ChapterContainer>() {
                    });
                    ArrayAdapter<Chapter> chaptersAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, chapterContainer.getChapters());
                    MainActivity.this.chapters = chapterContainer.getChapters();
                    runOnUiThread(() -> chapterList.setAdapter(chaptersAdapter));
                }
                else
                {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chapterList = findViewById(R.id.chapterList);

        if(!isNetworkConnected())
        {
            Toast.makeText(this, "تحقق من اتصالك بالانترنت ثم اعد المحاوله", Toast.LENGTH_SHORT).show();
            this.finishAffinity();
            return;
        }

        sendGetHttpRequest("chapters");

        chapterList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ChapterActivity.class);
            intent.putExtra("pos", String.valueOf(position));
            intent.putExtra("surah", String.valueOf(chapters.get(position).getName_arabic()));
            startActivity(intent);
        });
    }
}