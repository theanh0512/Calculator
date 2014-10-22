package com.ntu.sce.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by phongnt on 10/22/14.
 */
public class DrawerActivity extends Activity {
    private String[] toolsTitles;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private ListView drawerlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResId){
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer_layout,null);
        frameLayout = (FrameLayout) drawerLayout.findViewById(R.id.content_frame);
        getLayoutInflater().inflate(layoutResId, frameLayout, true);
        setContentView(drawerLayout);

        toolsTitles = getResources().getStringArray(R.array.tool_titles);
        drawerlist = (ListView) findViewById(R.id.left_drawer);

        drawerlist.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item,toolsTitles));
        drawerlist.setOnItemClickListener(new DrawerItemClickListener());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }

    private void selectItem(int position){
        drawerlist.setItemChecked(position, true);
        setTitle(toolsTitles[position]);
        startItemActivity(position);
    }

    private void startItemActivity(int position){
        String activityName = toolsTitles[position];
        Intent intent=null;
        if (activityName.equals("Home"))
            intent = new Intent(this,MainActivity.class);
        else if (activityName.equals("Calculator"))
            intent = new Intent(this,CalculatorActivity.class);
        if(intent!=null)
            startActivity(intent);
    }
}
