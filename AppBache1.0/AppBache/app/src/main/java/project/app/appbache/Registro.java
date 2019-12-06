package project.app.appbache;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final EditText PT_Nombre = (EditText)findViewById(R.id.pt_Nombre);
        final EditText PT_Apellido = (EditText)findViewById(R.id.pt_Apellido);
        final EditText PT_Correo = (EditText)findViewById(R.id.pt_Correo);
        final EditText PT_Password = (EditText)findViewById(R.id.pw_Password);
        Button BT_Registro = (Button)findViewById(R.id.bt_Registro_Registrar);

        BT_Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre = PT_Nombre.getText().toString();
                final String apellido = PT_Apellido.getText().toString();
                final String correo = PT_Correo.getText().toString();
                final String password = PT_Password.getText().toString();


                Response.Listener<String> Respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject JSONRespuesta = new JSONObject(response);
                            boolean ok = JSONRespuesta.getBoolean("success");

                            if ( ok == true ){
                                Intent i = new Intent( Registro.this, Login.class);
                                Registro.this.startActivity(i);
                                Registro.this.finish();
                            }else{
                                AlertDialog.Builder Alert = new AlertDialog.Builder(Registro.this);
                                Alert.setMessage("Fallo en el Registro")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                            }

                        }catch(JSONException e){
                            e.getMessage();
                        }
                    }
                };


                RegistroRequest r = new RegistroRequest( nombre, apellido, correo, password, Respuesta );
                RequestQueue cola = Volley.newRequestQueue(Registro.this);
                cola.add(r);

                //TextView Estado = findViewById(R.id.tv_registrado);
                //Estado.setText(response["success"]);
        }
        });

    }
}
