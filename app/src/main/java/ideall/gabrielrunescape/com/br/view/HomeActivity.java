package ideall.gabrielrunescape.com.br.view;

import android.os.Bundle;
import ideall.gabrielrunescape.com.br.R;
import android.support.v7.app.AppCompatActivity;


/**
 *      Criado por GabrielRuneScape <gabrielfilipe@mail.ru> em 23/11/2016
 *
 *      Classe da `activity` Home (inicial). Tem como função carregar todos os recursos pertencentes
 * a tela inicial.
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
