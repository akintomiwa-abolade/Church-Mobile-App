package jboss.kisconsult.com.championsassembly.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import jboss.kisconsult.com.championsassembly.App.MyApplication;
import jboss.kisconsult.com.championsassembly.Helper.Image;
import jboss.kisconsult.com.championsassembly.R;

/**
 * Created by JBoss on 7/20/2016.
 */
public class Member extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //let set the initial content to diagnosis fragement
        News frag = new News();
         replaceFragment(frag);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.drawer_member_menu);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        CircleImageView imageView = (CircleImageView) header.findViewById(R.id.imageView);
        TextView fullname = (TextView) header.findViewById(R.id.fullname);
        //TextView department = (TextView) header.findViewById(R.id.department);

        fullname.setText(MyApplication.getInstance().getPref().getUser().getFullname());
       // department.setText(MyApplication.getInstance().getPref().getUser().getMobile());
        new Image(imageView, MyApplication.getInstance().getPref().getUser().getImage());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //this method is used in add the menu to our page.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    //to respond to item click on the menu we use this menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_logout) {
            MyApplication.getInstance().getPref().clear();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            this.finish();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    //to respond to drawer item click we use this method
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            News fragment = new News();
            replaceFragment(fragment);
            //ive replaced d content to diagnosis fragement.
        } else if (id == R.id.nav_update_profile) {
            //UpdateProfile frag = new UpdateProfile();
            //replaceFragment(frag);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//all the four methods are generated auto. by the ide for choosing drawer layout as our root layout.

    private void replaceFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(fragment.getTag());
        transaction.replace(R.id.fragment_container, fragment).commit();

        //the first param, is what we wanna replace, whicj is d framelayout we include earlier
        //the secod param, is d fragement wwe wanna replace with.
    }
}
