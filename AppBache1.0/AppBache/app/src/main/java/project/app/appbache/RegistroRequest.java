package project.app.appbache;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroRequest extends StringRequest {
    private static final String Ruta = "http://localhost//registro.php";
    private Map<String, String> parametros;

    public RegistroRequest(String Nombre, String Apellido, String Correo, String Password, Response.Listener<String> listener) {
        super(Request.Method.POST, Ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("nombre", Nombre+"");
        parametros.put("apellido", Apellido+"");
        parametros.put("correo", Correo+"");
        parametros.put("password", Password+"");

    }

    public Map<String, String> getParametros() {
        return parametros;
    }
}
