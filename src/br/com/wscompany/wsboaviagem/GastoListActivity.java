package br.com.wscompany.wsboaviagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class GastoListActivity extends ListActivity implements
		OnItemClickListener {

	private List<Map<String, Object>> gastos;
	private String dataAnterior = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		String[] de = { "data", "descricao", "valor", "categoria" };
		int[] para = { R.id.data, R.id.descricao, R.id.valor, R.id.categoria };

		SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(),	R.layout.lista_gasto, de, para);
		adapter.setViewBinder(new GastoViewBinder());
		
		setListAdapter(adapter);

		getListView().setOnItemClickListener(this);

	}

	private List<Map<String, Object>> listarGastos() {
		gastos = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("data", "04/02/2012");
		item.put("descricao", "Diária Hotel");
		item.put("valor", "R$ 260,00");
		item.put("categoria", R.color.categoria_hospedagem);
		gastos.add(item);
		
		
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("data", "05/08/2012");
		item2.put("descricao", "Academia");
		item2.put("valor", "R$ 70,00");
		item2.put("categoria", R.color.categoria_outros);
		gastos.add(item2);
		
		Map<String, Object> item3 = new HashMap<String, Object>();
		item3.put("data", "04/02/2012");
		item3.put("descricao", "Pousada");
		item3.put("valor", "R$ 95,00");
		item3.put("categoria", R.color.categoria_hospedagem);
		gastos.add(item3);
		
		Map<String, Object> item4 = new HashMap<String, Object>();
		item4.put("data", "18/08/2012");
		item4.put("descricao", "Restaurante");
		item4.put("valor", "R$ 20,00");
		item4.put("categoria", R.color.categoria_alimentacao);
		gastos.add(item4);
		
		
		return gastos;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Map<String, Object> map = gastos.get(position);
		String descricao = (String) map.get("descricao");
		String mensagem = "Gasto selecionado: " + descricao;
		Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
	}

	private class GastoViewBinder implements ViewBinder {
		@Override
		public boolean setViewValue(View view, Object data,
				String textRepresentation) {

			if (view.getId() == R.id.data) {
				
				if (!dataAnterior.equals(data)) {
					
					TextView textView = (TextView) view;
					textView.setText(textRepresentation);
					dataAnterior = textRepresentation;
					view.setVisibility(View.VISIBLE);
				} else {
					view.setVisibility(View.GONE);
				}
				return true;
			}
			if (view.getId() == R.id.categoria) {
				Integer id = (Integer) data;
				view.setBackgroundColor(getResources().getColor(id));
				return true;
			}
			return false;

		}
	}
}
