package churski.projektindywidualny3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView statusText;
    TcpClient tcpClient;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        statusText = (TextView) findViewById(R.id.status);
        tcpClient = new TcpClient("192.168.43.55", 2001);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("[APP]: Destroy");
        tcpClient.send("disconnecting");
    }

    public void onClick(View v) {
        statusText.setTextColor(Color.rgb(239,233,57));
        statusText.setText("Connection...");
        System.out.println("[APP]: onClick");
        tcpClient.send((String) v.getTag());
    }
}
