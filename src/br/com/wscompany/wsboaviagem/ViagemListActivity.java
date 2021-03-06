package br.com.wscompany.wsboaviagem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;


public class ViagemListActivity extends ListActivity implements
		OnItemClickListener, OnClickListener, ViewBinder {

	private AlertDialog alertDialog;
	private AlertDialog dialogConfirmacao;

	private int viagemSelecionada;

	private List<Map<String, Object>> viagens;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[] de = { "imagem", "destino", "data", "total", "barraProgresso" };

		int[] para = { R.id.tipoViagem, R.id.destino, R.id.data, R.id.valor, R.id.barraProgresso };

		SimpleAdapter adapter = new SimpleAdapter(this, listarViagens(),
				R.layout.lista_viagem, de, para);
		
		adapter.setViewBinder(this);//deve ficar apos a declara��o do adapter. � usado qdo houver uma progress bar do tipo determinada. ViewBinder deve ser implementada e esta linha que faz executar de fato o metodo setViewValue. 
		
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);

		this.alertDialog = criaAlertDialog();

		this.dialogConfirmacao = criaDialogConfirmacao();

	}

	private List<Map<String, Object>> listarViagens() {

		viagens = new ArrayList<Map<String, Object>>();

		Map<String, Object> item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.negocios);
		item.put("destino", "S�o Paulo");
		item.put("data", "02/02/2012 a 04/02/2012");
		item.put("total", "Gasto total R$ 314,98");
		item.put("barraProgresso", new Double[] { 500.0, 450.0, 314.98 });
		//item.put("barraProgresso", new Double[] { 314.98});
		viagens.add(item);

		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.ferias);
		item.put("destino", "Macei�");
		item.put("data", "14/05/2012 a 22/05/2012");
		item.put("total", "Gasto total R$ 258,67");
		item.put("barraProgresso", new Double[] { 500.0, 450.0, 258.67 });
		viagens.add(item);

		item = new HashMap<String, Object>();
		item.put("imagem", R.drawable.negocios);
		item.put("destino", "Belo Horizonte");
		item.put("data", "15/08/2012 a 22/12/2012");
		item.put("total", "Gasto total R$ 334,69");
		item.put("barraProgresso", new Double[] { 500.0, 450.0, 334.69 });
		viagens.add(item);

		return this.viagens;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		this.viagemSelecionada = position;
		alertDialog.show();

		// startActivity(new Intent(this, GastoListActivity.class));

	}

	@Override
	public void onClick(DialogInterface dialog, int item) {
		switch (item) {
		case 0:
			startActivity(new Intent(this, ViagemActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, GastoActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, GastoListActivity.class));
			break;
		case 3:
			dialogConfirmacao.show();
			break;
		case DialogInterface.BUTTON_POSITIVE:
			viagens.remove(viagemSelecionada);
			getListView().invalidateViews();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			dialogConfirmacao.dismiss();
			break;
		}

	}

	private AlertDialog criaAlertDialog() {
		final CharSequence[] items = { getString(R.string.editar),
				getString(R.string.novo_gasto),
				getString(R.string.gastos_realizados),
				getString(R.string.remover) };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.opcoes);
		builder.setItems(items, this);
		return builder.create();
	}

	private AlertDialog criaDialogConfirmacao() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.confirmacao_exclusao_viagem);
		builder.setPositiveButton(getString(R.string.sim), this);
		builder.setNegativeButton(getString(R.string.nao), this);
		return builder.create();
	}

	@Override
	public boolean setViewValue(View view, Object data,	String textRepresentation) {
		if (view.getId() == R.id.barraProgresso) {
			Double valores[] = (Double[]) data;
			
			ProgressBar progressBar = (ProgressBar) view;
			
			int valor_maximo = valores[0].intValue();
			progressBar.setMax(valor_maximo);
			
			int valor_secundario = valores[1].intValue();
			progressBar.setSecondaryProgress(valor_secundario);
			
			int valor_progresso = valores[2].intValue();
			progressBar.setProgress(valor_progresso);
			return true;

		}
		return false;

	}
}
