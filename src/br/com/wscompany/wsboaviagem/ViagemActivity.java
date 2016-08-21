package br.com.wscompany.wsboaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ViagemActivity extends Activity {

	private int ano, mes, dia, id_data;
	private Button data_partida;
	private Button data_chegada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viagem);

		Calendar calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);
		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);

		ExibeDataListener exibe_data_listener = new ExibeDataListener();

		data_partida = (Button) findViewById(R.id.dataSaida);
		data_partida.setOnClickListener(exibe_data_listener);
		data_partida.setText(dia + "/" + (mes + 1) + "/" + ano);

		data_chegada = (Button) findViewById(R.id.dataChegada);
		data_chegada.setOnClickListener(exibe_data_listener);
		data_chegada.setText(dia + "/" + (mes + 1) + "/" + ano);
	}

	public void selecionarData(int id_view) {

		SelecionaDataListener selecionar_listener = new SelecionaDataListener(id_view);

		DatePickerDialog data_picker = new DatePickerDialog(this,
				selecionar_listener, ano, mes, dia);

		data_picker.show();

	}

	private class ExibeDataListener implements View.OnClickListener,
			View.OnFocusChangeListener {

		@Override
		public void onClick(View v) {

			selecionarData(v.getId());
		}

		@Override
		public void onFocusChange(View v, boolean hasFocus) {

			if (hasFocus) {

				selecionarData(v.getId());
			}

		}

	}

	private class SelecionaDataListener implements
			DatePickerDialog.OnDateSetListener {

		int id_view = 0;

		private SelecionaDataListener(int id_view) {

			this.id_view = id_view;
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			String data_formatada = dayOfMonth + "/" + monthOfYear + "/" + year;

			if (id_view == R.id.dataChegada) {

				data_chegada.setText(data_formatada);
			}

			if (id_view == R.id.dataSaida) {

				data_partida.setText(data_formatada);
			}

		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		this.id_data = id;

		return null;

	}

}
