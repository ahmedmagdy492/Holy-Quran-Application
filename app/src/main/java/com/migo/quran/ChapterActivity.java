package com.migo.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migo.quran.models.VerseContainer;
import com.migo.quran.util.StaticStrings;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChapterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView txtCurPage;
    private TextView txtSurahName;
    private int chapterTotalPages;
    private int currentPage;

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void sendGetHttpRequest(String internalPath)
    {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(StaticStrings.API_URI + internalPath)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(ChapterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    ObjectMapper mapper = new ObjectMapper();
                    VerseContainer verseContainer = mapper.readValue(response.body().string(), new TypeReference<VerseContainer>() {
                    });

                    // getting the total number of pages of the current chapter
                    chapterTotalPages = verseContainer.getMeta().getTotal_pages();
                    currentPage = verseContainer.getMeta().getCurrent_page();

                    VerseRecyclerAdapter adapter = new VerseRecyclerAdapter(ChapterActivity.this);
                    adapter.setVerses(verseContainer.getVerses());
                    runOnUiThread(() -> {
                        recyclerView.setAdapter(adapter);
                        txtCurPage.setText(new StringBuilder(String.valueOf(currentPage)).append("/").append(chapterTotalPages));
                    });
                }
                else
                {
                    Toast.makeText(ChapterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        if(!isNetworkConnected())
        {
            Toast.makeText(this, "تحقق من اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
            return;
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        recyclerView = findViewById(R.id.chapterContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        txtSurahName = findViewById(R.id.txtSurahName);
        String surahName = getIntent().getStringExtra("surah");
        txtSurahName.setText(surahName);

        int position = Integer.parseInt(getIntent().getStringExtra("pos"));
        String str = "chapters/" + (position + 1) + "/verses?recitation=1&translations=21&language=ar&page=" + currentPage + "&text_type=words";
        sendGetHttpRequest(str);

        txtCurPage = findViewById(R.id.txtPageNumber);
    }

    public void btnNextClicked(View view)
    {
        if(currentPage < chapterTotalPages)
        {
            int position = Integer.parseInt(getIntent().getStringExtra("pos"));
            currentPage++;
            String str = "chapters/" + (position + 1) + "/verses?recitation=1&translations=21&language=ar&page=" + currentPage + "&text_type=words";
            sendGetHttpRequest(str);
        }
    }

    public void btnPrevClicked(View view)
    {
        if(currentPage > 1)
        {
            int position = Integer.parseInt(getIntent().getStringExtra("pos"));
            currentPage--;
            String str = "chapters/" + (position + 1) + "/verses?recitation=1&translations=21&language=ar&page=" + currentPage + "&text_type=words";
            sendGetHttpRequest(str);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}