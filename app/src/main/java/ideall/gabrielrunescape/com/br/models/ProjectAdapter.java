package ideall.gabrielrunescape.com.br.models;

import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import ideall.gabrielrunescape.com.br.R;
import ideall.gabrielrunescape.com.br.objects.Project;

/**
 * Created by gabriel on 23/11/16.
 */

public class ProjectAdapter extends ArrayAdapter<Project> {
    public ProjectAdapter(Context context, List<Project> lista) {
        super(context, 0, lista);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.project_menu, parent, false);
        }

        Project project = getItem(position);

        TextView name = (TextView) itemView.findViewById(R.id.txtTitle);
        TextView author = (TextView) itemView.findViewById(R.id.txtAuthor);

        name.setText(project.getName());
        author.setText(project.getAuthor());

        return itemView;
    }
}
