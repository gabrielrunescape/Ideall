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
import ideall.gabrielrunescape.com.br.DAO.ContactDAO;
import ideall.gabrielrunescape.com.br.objects.Contact;

public class ContactActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;
    private ContactDAO contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contact = new ContactDAO(this);
        contact.open();
    }

    @Override
    protected void onResume() {
        contact.open();
        super.onResume();

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);

        if (getIntent().getSerializableExtra("Contact") != null) {
            Contact c = (Contact) getIntent().getSerializableExtra("Contact");

            etName.setText(c.getName());
            etEmail.setText(c.getEmail());
        }
    }

    @Override
    protected void onPause() {
        contact.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        int id = item.getItemId();

        switch (id) {
            case R.id.item_undo:
                intent = new Intent(getApplicationContext(), ShowAllActivity.class);
                break;
            case R.id.item_done:
                String n = etName.getText().toString();
                String b = etEmail.getText().toString();

                if (n.isEmpty() || b.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Existem campos vazios!", Toast.LENGTH_SHORT).show();

                    return false;
                } else {
                    Contact c = new Contact();
                    c.setName(n);
                    c.setEmail(b);

                    if (getIntent().getSerializableExtra("Contact") == null) {
                        contact.create(c);
                    } else {
                        c.setID(((Contact) getIntent().getSerializableExtra("Contact")).getID());
                        contact.update(c);
                    }

                    intent = new Intent(getApplicationContext(), ShowAllActivity.class);
                }
                break;
        }

        if (intent != null) {
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
