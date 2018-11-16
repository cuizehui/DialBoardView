package cn.nela.dialboardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.dialView)
    DialView mdialView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mdialView.setDialViewListener(new DialView.DialViewListener() {
            @Override
            public void inputChange() {
                Toast.makeText(MainActivity.this, mdialView.getNumber(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCall() {
                Toast.makeText(MainActivity.this, "onCall", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSetting() {
                Toast.makeText(MainActivity.this, "onSetting", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongEvent(String number) {
                Toast.makeText(MainActivity.this, "onLongEvent:" + number, Toast.LENGTH_SHORT).show();
                if (TextUtils.equals("1", number)) {
                    mdialView.setDialViewInput("123456");
                } else if (TextUtils.equals("2", number)) {
                    mdialView.cleanDialViewInput();
                }
            }
        });
    }
}
