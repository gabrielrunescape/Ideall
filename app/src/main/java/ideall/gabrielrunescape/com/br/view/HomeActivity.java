package ideall.gabrielrunescape.com.br.view;

import java.util.List;

import android.view.View;
import android.view.Menu;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import ideall.gabrielrunescape.com.br.DAO.ProjectDAO;
import ideall.gabrielrunescape.com.br.objects.Project;
import android.support.design.widget.FloatingActionButton;

/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 23/11/2016
 *
 *      Classe da `activity` Home (inicial). Tem como função carregar todos os recursos pertencentes
 * a tela inicial.
 */
public class HomeActivity extends AppCompatActivity {
    private ProjectDAO dao;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = new ProjectDAO(this);
        dao.open();
    }

    @Override
    protected void onResume() {
        dao.open();
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

        List<Project> projects = new ArrayList<Project>();
        ArrayAdapter<Project> adapter = new ArrayAdapter<Project>(this, android.R.layout.simple_list_item_1, projects);

        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adapter);

    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
