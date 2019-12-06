package project.app.appbache;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle SavedInstanceState){
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView Registro = findViewById(R.id.tv_Registrar);
        final Button Login = findViewById(R.id.b_Login);
        final EditText UsuarioT = findViewById(R.id.pt_Username);
        final EditText PasswordT = findViewById(R.id.pw_Password);

        Registro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent registro = new Intent(Login.this, Registro.class);
                Login.this.startActivity(registro);
                Login.this.finish();
            }
        });

        Login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String Usuario = UsuarioT.getText().toString();
                final String Password = PasswordT.getText().toString();

                final Response.Listener Respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean ok = jsonObject.getBoolean("success");

                            if(ok == true){
                                String Nombre = jsonObject.getString("nombre");
                                String Apellido = jsonObject.getString("apellido");
                                String Correo = jsonObject.getString("correo");
                                String Password = jsonObject.getString("password");

                                Intent Bienvenido = new Intent(Login.this, mensaje_login.class);
                                Bienvenido.putExtra("nombre", Nombre);
                                Bienvenido.putExtra("apellido", Apellido);
                                Bienvenido.putExtra("correo", Correo);
                                Bienvenido.putExtra("password", Password);

                                Login.this.startActivity(Bienvenido);
                                Login.this.finish();

                            }
                            else{
                                AlertDialog.Builder Alert = new AlertDialog.Builder(Login.this);
                                Alert.setMessage("Fallo en el Login")
                                        .setNegativeButton("Reintentar", null)
                                        .create().show();
                            }

                        }catch(JSONException e){
                            e.getMessage();
                        }
                    }
                };
            }
        });
    }
}
