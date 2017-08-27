package com.hewid.alpheus.Controller;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import com.hewid.alpheus.Model.Game.WorldManager;
import com.hewid.alpheus.R;
import com.hewid.alpheus.View.GameView;

public class GameActivity extends AppCompatActivity {
    Pacemaker pacemaker;
    WorldManager worldManager;
    GameView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        view = (GameView) findViewById(R.id.game_view);
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                worldManager = new WorldManager(view.getHeight(), view.getWidth());
                pacemaker = new Pacemaker(worldManager);
                view.attachWorld(worldManager);
                view.register(pacemaker);
                //view.setOnTouchListener(new TouchListener(worldManager));

                pacemaker.start();
            }
        });
    }
}
