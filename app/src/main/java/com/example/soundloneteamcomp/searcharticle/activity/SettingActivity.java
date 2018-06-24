package com.example.soundloneteamcomp.searcharticle.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soundloneteamcomp.searcharticle.Fragment_Activity.DatePickerFragment;
import com.example.soundloneteamcomp.searcharticle.listSearchArticle.view.MainActivity;
import com.example.soundloneteamcomp.searcharticle.R;

import java.util.Calendar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.date) EditText editText;

    @BindView(R.id.tool_save) Toolbar toolbar;

    @BindView(R.id.spinner) Spinner spinner;

    @BindView(R.id.save) Button btn;

    @BindView(R.id.cancel) Button cancelBtn;

    @BindView(R.id.art) CheckBox boxArt;

    @BindView(R.id.fashion) CheckBox boxFashion;

    @BindView(R.id.sport) CheckBox boxSport;

    @BindString(R.string.error) String Error;


    private String dates,sort,searchQuerry;
    boolean art=false,fashion=false,sport=false;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        editText.setFocusableInTouchMode(false);
        setArrayAdapter();
        getData();
        setData();
    }

    @OnClick(R.id.date)
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance

        final Calendar c = Calendar.getInstance();
        int nowDay = c.get(Calendar.DAY_OF_MONTH);
        int nowMonth = c.get(Calendar.MONTH);
        int nowyear = c.get(Calendar.YEAR);
        boolean isReturn = false;

        if(nowDay < dayOfMonth && nowMonth <= monthOfYear && nowyear <= year)
            isReturn = true;

        if (isReturn){
            Toast.makeText(this , Error,
                    Toast.LENGTH_LONG).show();
            return;
        }

        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,monthOfYear);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        String day = String.valueOf(dayOfMonth);
        int mon = monthOfYear + 1;
        String month = String.valueOf(mon);
        if(dayOfMonth < 10)
            day = "0" + dayOfMonth;
        if (mon < 10)
            month = "0" + mon;
        dates = day+"/"+month+"/"+year;
        editText.setText(dates);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.icon_save:
                saveFilter();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setArrayAdapter(){
        adapter = ArrayAdapter.createFromResource(this,R.array.sortArray,R.layout.spinner_item);
        spinner.setAdapter(adapter);
    }

    @OnClick(R.id.save)
    void saveFilter(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dates",dates);
        sort = spinner.getSelectedItem().toString();
        intent.putExtra("sort",sort);
        intent.putExtra("querry",searchQuerry);
        intent.putExtra("art",boxArt.isChecked());
        intent.putExtra("fashion",boxFashion.isChecked());
        intent.putExtra("sport",boxSport.isChecked());
        setResult(112,intent);
        finish();

    }

    private void getData(){
        dates = getIntent().getStringExtra("dates");
        sort = getIntent().getStringExtra("sort");
        art = getIntent().getBooleanExtra("art",false);
        fashion = getIntent().getBooleanExtra("fashion",false);
        sport = getIntent().getBooleanExtra("sport",false);
        searchQuerry = getIntent().getStringExtra("querry");
    }

    private void setData(){
        editText.setText(dates);
        int pos = ((ArrayAdapter<String>)spinner.getAdapter()).getPosition(sort);
        spinner.setSelection(pos);
        boxArt.setChecked(art);
        boxFashion.setChecked(fashion);
        boxSport.setChecked(sport);
    }

    @OnClick(R.id.cancel)
    void cancelFilter(){
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("dates",dates);
        outState.putString("sort",sort);
        outState.putString("querry",searchQuerry);
//        outState.putString();
    }
}
