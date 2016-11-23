package ideall.gabrielrunescape.com.br.view;

import android.view.View;
import android.view.Menu;
import android.os.Bundle;
import android.content.Intent;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;


/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 23/11/2016
 *
 *      Classe da `activity` Home (inicial). Tem como função carregar todos os recursos pertencentes
 * a tela inicial.
 */
public class HomeActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(getApplicationContext(), EditActivity.class);

                startActivity(itent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
