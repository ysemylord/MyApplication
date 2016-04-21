package houm.com.cameramine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import houm.com.cameramine.util.Util;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        Util.initSystemBar(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        Util.initSystemBar(this);
    }
}
