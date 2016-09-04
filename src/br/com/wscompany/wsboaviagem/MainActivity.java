package br.com.wscompany.wsboaviagem;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText usuario;
	private EditText senha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		usuario = (EditText) findViewById(R.id.usuario);
		senha = (EditText) findViewById(R.id.senha);
	}

	public void entrarOnClick(View v) {	
		
		

		String usuarioInformado = usuario.getText().toString();
		String senhaInformada = senha.getText().toString();
		
		if ( usuarioInformado.equals("ws") && senhaInformada.equals("123")) {
			
			startActivity(new Intent(this,DashboardActivity.class));
		} else {

			Toast toast = Toast.makeText(this,
					getString(R.string.erro_autenticao), Toast.LENGTH_LONG);
			toast.show();

		}

	}
}
