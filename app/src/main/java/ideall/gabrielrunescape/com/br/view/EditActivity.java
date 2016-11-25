package ideall.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import ideall.gabrielrunescape.com.br.DAO.ProjectDAO;
import ideall.gabrielrunescape.com.br.objects.Project;

public class EditActivity extends AppCompatActivity {
    private MenuItem undo;
    private MenuItem done;
    private EditText etBy;
    private EditText etName;
    private ProjectDAO project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);

        project = new ProjectDAO(this);
        project.open();
    }

    @Override
    protected void onResume() {
        project.open();
        super.onResume();

        etBy = (EditText) findViewById(R.id.etBy);
        etName = (EditText) findViewById(R.id.etName);

        if (getIntent().getSerializableExtra("Project") != null) {
            Project p = (Project) getIntent().getSerializableExtra("Project");

            etBy.setText(p.getAuthor());
            etName.setText(p.getName());
        }
    }

    @Override
    protected void onPause() {
        project.close();
        super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);

        undo = menu.findItem(R.id.item_undo);
        done = menu.findItem(R.id.item_done);

        undo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                startActivity(intent);
                finish();

                return true;
            }
        });

        done.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String n = etName.getText().toString();
                String b = etBy.getText().toString();

                if (n.isEmpty() || b.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Existem campos vazios!", Toast.LENGTH_SHORT).show();

                    return false;
                } else {
                    Project p = new Project();
                    p.setName(n);
                    p.setAuthor(b);

                    if (getIntent().getSerializableExtra("Project") == null) {
                        project.create(p);
                    } else {
                        p.setID(((Project) getIntent().getSerializableExtra("Project")).getID());
                        project.update(p);
                    }

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                    startActivity(intent);
                    finish();

                    return true;
                }
            }
        });

        return true;
    }
}
