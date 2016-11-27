package ideall.gabrielrunescape.com.br.view;

import java.util.List;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import ideall.gabrielrunescape.com.br.DAO.ContactDAO;
import ideall.gabrielrunescape.com.br.objects.Contact;
import ideall.gabrielrunescape.com.br.models.ContactAdapter;

public class ShowAllActivity extends AppCompatActivity {
    private ContactDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_showAll);
        setSupportActionBar(toolbar);

        dao = new ContactDAO(this);
        dao.open();
    }

    @Override
    protected void onResume() {
        dao.open();
        super.onResume();

        final List<Contact> contacts = dao.getAll();
        ContactAdapter adapter = new ContactAdapter(this, contacts);

        ListView lista = (ListView) findViewById(R.id.listViewShowAll);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int count = parent.getCount();

                for (int i = 0; i < count; i++) {
                    if (i == position) {
                        view.setBackgroundColor(getResources().getColor(R.color.colourDivider));

                        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

                        Contact c = contacts.get(position);
                        intent.putExtra("Contact", c);

                        startActivity(intent);
                    } else {
                        parent.getChildAt(i).setBackgroundColor(getResources().getColor(R.color.colourBackground));
                    }
                }
            }

            /*@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ContactActivity.class);

                Contact c = contacts.get(position);
                intent.putExtra("Contact", c);

                startActivity(intent);
            }*/
        });
    }

    @Override
    protected void onPause() {
        dao.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_add) {
            Intent intent = new Intent(getApplicationContext(), ContactActivity.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
