package com.hewid.alpheus.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hewid.alpheus.Model.Game.World;
import com.hewid.alpheus.R;
import com.hewid.alpheus.View.GameView;

public class GameActivity extends AppCompatActivity {
    Pacemaker pacemaker;
    World world;
    GameView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        world = new World();
        pacemaker = new Pacemaker(world);
        view = (GameView) findViewById(R.id.game_view);
        view.attachWorld(world);
        view.register(pacemaker);
        //view.setOnTouchListener(new TouchListener(world));

        pacemaker.start();
    }
}
