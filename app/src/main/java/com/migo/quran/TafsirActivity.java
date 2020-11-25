package com.migo.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migo.quran.models.TafsirAdapter;
import com.migo.quran.models.TafsirContainer;
import com.migo.quran.util.StaticStrings;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TafsirActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private void sendGetHttpRequest(String str)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticStrings.API_URI + str)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(TafsirActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    ObjectMapper mapper = new ObjectMapper();
                    TafsirContainer tafsirContainer =  mapper.readValue(response.body().string(), new TypeReference<TafsirContainer>() {});
                    TafsirAdapter tafsirAdapter = new TafsirAdapter(TafsirActivity.this);
                    tafsirAdapter.setTafsirs(tafsirContainer.getTafsirs());

                    runOnUiThread(() -> {
                        recyclerView.setAdapter(tafsirAdapter);
                    });
                }
                else
                {
                    Toast.makeText(TafsirActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tafsir);

        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        try
        {
            int verseNumber = Integer.parseInt(getIntent().getStringExtra("verse"));
            int chapterId = Integer.parseInt(getIntent().getStringExtra("chapter"));
            String str = "chapters/" + chapterId + "/verses/" + (verseNumber+1) + "/tafsirs";
            sendGetHttpRequest(str);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}