package ideall.gabrielrunescape.com.br.models;

import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import ideall.gabrielrunescape.com.br.R;
import ideall.gabrielrunescape.com.br.objects.Contact;

/**
 * Created by gabriel on 27/11/16.
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, List<Contact> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact, parent, false);
        }

        Contact contact = getItem(position);

        TextView name = (TextView) itemView.findViewById(R.id.txtContact);
        TextView email = (TextView) itemView.findViewById(R.id.txtEmail);

        name.setText(contact.getName());
        email.setText(contact.getEmail());

        return itemView;
    }
}
