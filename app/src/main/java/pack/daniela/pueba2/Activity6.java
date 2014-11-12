package pack.daniela.pueba2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Activity6 extends Activity {
    private String[] titulos;
    private DrawerLayout NavDrawerLayout;
    private ListView NavList;
    private ArrayList<Item_objct> NavItms;
    private TypedArray NavIcons;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    NavigationAdapter NavAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity6);

        NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavList = (ListView) findViewById(R.id.lista);

        //Declaramos el header el caul sera el layout de header.xml
        View header = getLayoutInflater().inflate(R.layout.header, null);
        //Establecemos header
        NavList.addHeaderView(header);
        //Tomamos listado  de imgs desde drawable
        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);
        //Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
        titulos = getResources().getStringArray(R.array.nav_options);
        //Listado de titulos de barra de navegacion
        NavItms = new ArrayList<Item_objct>();
        //Agregamos objetos Item_objct al array
        //Perfil
        NavItms.add(new Item_objct(titulos[0], NavIcons.getResourceId(0, -1)));
        //Favoritos
        NavItms.add(new Item_objct(titulos[1], NavIcons.getResourceId(1, -1)));
        //Eventos
        NavItms.add(new Item_objct(titulos[2], NavIcons.getResourceId(2, -1)));
        //Lugares
        NavItms.add(new Item_objct(titulos[3], NavIcons.getResourceId(3, -1)));
        //Etiquetas
        NavItms.add(new Item_objct(titulos[4], NavIcons.getResourceId(4, -1)));
        //Configuracion
        NavItms.add(new Item_objct(titulos[5], NavIcons.getResourceId(5, -1)));
        //Share
        NavItms.add(new Item_objct(titulos[6], NavIcons.getResourceId(6, -1)));
        //Declaramos y seteamos nuestrp adaptador al cual le pasamos el array con los titulos

        NavAdapter= new NavigationAdapter(this,NavItms);
        NavList.setAdapter(NavAdapter);


        //Siempre vamos a mostrar el mismo titulo
        mTitle = mDrawerTitle = getTitle();

        //Declaramos el mDrawerToggle y las imgs a utilizar
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                NavDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* Icono de navegacion*/
                R.string.app_name,  /* "open drawer" description */
                R.string.hello_world  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        // Set the drawer toggle as the DrawerListener
        NavDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(Activity6.this, MiTareaAsincrona.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Activity6.this, Activity8.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override //permite cabia el icono de abrir y cerrar menu
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = NavDrawerLayout.isDrawerOpen(NavList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity6, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

}
