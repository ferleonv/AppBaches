package project.app.appbache;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText PT_Nombre;
    EditText PT_Apellido;
    EditText PT_Correo;
    EditText PT_Password;
    TextView TV_Regresar;
    Button BT_Registro;
    ProgressDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        PT_Nombre = (EditText)findViewById(R.id.pt_Nombre);
        PT_Apellido = (EditText)findViewById(R.id.pt_Apellido);
        PT_Correo = (EditText)findViewById(R.id.pt_Correo);
        PT_Password = (EditText)findViewById(R.id.pw_Password);
        BT_Registro = (Button)findViewById(R.id.bt_Registro_Registrar);
        TV_Regresar = (TextView)findViewById(R.id.tv_Regresar);
        Dialog = new ProgressDialog(this);

        BT_Registro.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        
    }

    private void RegisterUser(){
        final String Correo = PT_Correo.getText().toString().trim();
        final String Nombre = PT_Nombre.getText().toString().trim();
        final String Apellido = PT_Apellido.getText().toString().trim();
        final String Password = PT_Password.getText().toString().trim();

        Dialog.setMessage("Registrando...");
        Dialog.show();

        StringRequest StringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTRO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Dialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Dialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> Params = new HashMap<>();
                Params.put( "Correo", Correo );
                Params.put( "Nombre", Nombre );
                Params.put( "Apellido", Apellido );
                Params.put( "Password", Password );

                return Params;

            }
        };

        RequestQueue Request = Volley.newRequestQueue(this);
        Request.add(StringRequest);

    }

    @Override
    public void onClick(View v) {
        if( v == BT_Registro ){
            RegisterUser();
        }
        else if ( v == TV_Regresar){
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }
    }
}
