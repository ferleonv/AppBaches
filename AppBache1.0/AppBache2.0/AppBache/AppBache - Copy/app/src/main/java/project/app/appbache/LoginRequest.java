package project.app.appbache;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String Ruta = "http://localhost/login.php";
    private Map<String, String> parametros;

    public LoginRequest(String Usuario, String Password, Response.Listener<String> listener) {
        super(Request.Method.POST, Ruta, listener, null);
        parametros = new HashMap<>();
        parametros.put("usuario", Usuario+"");
        parametros.put("password", Password+"");

    }

    public Map<String, String> getParametros() {
        return parametros;
    }
}
