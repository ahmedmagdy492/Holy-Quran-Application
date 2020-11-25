package com.migo.quran;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.migo.quran.models.Verse;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VerseRecyclerAdapter extends RecyclerView.Adapter<VerseRecyclerAdapter.VerseViewHolder>
{
    private ArrayList<Verse> verses;
    private Context context;
    private MediaPlayer player;

    public VerseRecyclerAdapter(Context context) {
        this.context = context;
        verses = new ArrayList<>();
    }

    @NonNull
    @Override
    public VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verse_layout, parent, false);
        return new VerseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerseViewHolder holder, int position) {
        if(verses.get(position).getText_madani() != null)
        {
            holder.verseContent.setText(new StringBuilder(verses.get(position).getText_madani()).append(" {").append(verses.get(position).getVerse_number()).append("}"));
        }
        else
        {
            holder.verseContent.setText(new StringBuilder(verses.get(position).getText_simple()).append(" {").append(verses.get(position).getVerse_number()).append("}"));
        }
        
        holder.verseContainer.setOnLongClickListener(v -> {
            PopupMenu pop = new PopupMenu(context, v);
            pop.getMenuInflater().inflate(R.menu.context_menu, pop.getMenu());
            pop.getMenu().getItem(0).setOnMenuItemClickListener(item -> {
                if(player == null)
                {
                    player = MediaPlayer.create(VerseRecyclerAdapter.this.context, Uri.parse(verses.get(position).getAudio().getUrl()));

                    if(!player.isPlaying())
                    {
                        v.setBackgroundColor(Color.LTGRAY);
                        player.start();
                        int duration = verses.get(position).getAudio().getDuration();

                        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
                        executorService.schedule(() -> {
                            if(player.isPlaying())
                            {
                                player.stop();
                                player.reset();
                                player.release();
                                player = null;
                                v.setBackgroundColor(Color.TRANSPARENT);
                            }
                        }, duration-1, TimeUnit.SECONDS);
                    }
                }
                return true;
            });
            pop.getMenu().getItem(1).setOnMenuItemClickListener(item -> {
                Intent intent = new Intent(context, TafsirActivity.class);
                intent.putExtra("verse", String.valueOf(position));
                intent.putExtra("chapter", String.valueOf(verses.get(position).getChapter_id()));
                context.startActivity(intent);
                return true;
            });
            pop.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return verses.size();
    }

    public void setVerses(ArrayList<Verse> verses) {
        this.verses = verses;
        //notifyDataSetChanged();
    }

    public class VerseViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout verseContainer;
        private TextView verseContent;

        public VerseViewHolder(@NonNull View itemView) {
            super(itemView);
            verseContainer = itemView.findViewById(R.id.verseContainer);
            verseContent = itemView.findViewById(R.id.verseContent);
        }
    }
}
