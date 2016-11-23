package ideall.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;

public class EditActivity extends AppCompatActivity {
    private FloatingActionButton btnSave;
    private FloatingActionButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnSave = (FloatingActionButton) findViewById(R.id.btnSave);
        btnClose = (FloatingActionButton) findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                startActivity(intent);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }
}
