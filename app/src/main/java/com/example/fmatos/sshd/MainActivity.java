package com.example.fmatos.sshd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServer();
            }
        });
    }

    private void startServer() {

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                Server server = new Server();

                Log.i("SSHD","Start ssh");
                server.startSSHServer();

                try {
                    Thread.sleep(600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.i("SSHD","End ssh");
            }
        });

        th.start();
    }
}
