package br.com.aeso.projeto;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		TabHost tabHost = getTabHost();
		
		LayoutInflater.from(this).inflate(R.layout.abas,
				tabHost.getTabContentView(), true);
		
		tabHost.addTab(tabHost.newTabSpec("Higiene")
				.setIndicator("Aba 1").setContent(R.id.tab1));
		tabHost.addTab(tabHost.newTabSpec("Documentos")
				.setIndicator("Aba 2").setContent(R.id.tab2));
		tabHost.addTab(tabHost.newTabSpec("Vestuário")
				.setIndicator("Aba 3").setContent(R.id.tab3));
		
		setContentView(tabHost);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
