package ideall.gabrielrunescape.com.br.view;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;
import ideall.gabrielrunescape.com.br.DAO.ProjectDAO;
import ideall.gabrielrunescape.com.br.objects.Project;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private EditText etBy;
    private EditText etName;
    private ProjectDAO project;
    private FloatingActionButton btnSave;
    private FloatingActionButton btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        project = new ProjectDAO(this);
        project.open();
    }

    @Override
    protected void onResume() {
        project.open();
        super.onResume();

        etBy = (EditText) findViewById(R.id.etBy);
        etName = (EditText) findViewById(R.id.etName);

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
                String n = etName.getText().toString();
                String b = etBy.getText().toString();

                if (n.isEmpty() || b.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Existem campos vazios!", Toast.LENGTH_SHORT).show();
                } else {
                    Project p = new Project();
                    p.setName(n);
                    p.setAuthor(b);

                    project.create(p);

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        project.close();
        super.onPause();
    }
}
