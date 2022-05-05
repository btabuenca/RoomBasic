package es.upm.roombasic;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import es.upm.roombasic.utils.CifradoCesar;

public class NuevoUsuarioActivity extends AppCompatActivity {

    public static final String PARAM_NOMBRE = "es.upm.roombasic.nombre";
    public static final String PARAM_PASSWORD = "es.upm.roombasic.password";
    public static final String PARAM_ROL = "es.upm.roombasic.rol";

    private EditText etName, etPass;
    private RatingBar rbRol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        etName = findViewById(R.id.etNombre);
        rbRol = findViewById(R.id.ratingBar);
        etPass = findViewById(R.id.etPassword);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(etName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    CifradoCesar cc = new CifradoCesar();
                    String sNombre = etName.getText().toString();
                    String sPass = etPass.getText().toString();
                    float fRol = rbRol.getRating();
                    replyIntent.putExtra(PARAM_NOMBRE, sNombre);
                    replyIntent.putExtra(PARAM_PASSWORD, cc.cifrar(sPass, CifradoCesar.CAESARCODE_ROT));
                    replyIntent.putExtra(PARAM_ROL, fRol);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    /*
    public String cifrar(String texto, char opcion, int codigo) {
        CifradoCesar cc = new CifradoCesar();

        if (Character.toUpperCase(opcion) == 'C') {
            return cc.cifrar(texto, codigo);
        } else {
            return cc.descifrar(texto, codigo);
        }
    }
    */
}