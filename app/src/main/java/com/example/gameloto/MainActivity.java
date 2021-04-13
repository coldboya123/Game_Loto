package com.example.gameloto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    List<Integer> list = new ArrayList<>();
    public static int DEVICE_WIDTH_PX, DEVICE_HEIGHT_PX, DEVICE_HEIGHT_DP, DEVICE_WIDTH_DP;
    Random random;

    AppCompatEditText txtmax, txtmin;
    TextView txtresult, txtrange;
    AppCompatButton btn_addrange, btn_reset, btn_random;
    int max, min, temp;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        float scale = getResources().getDisplayMetrics().density;
//        DEVICE_WIDTH_PX = displayMetrics.widthPixels;
//        DEVICE_HEIGHT_PX = displayMetrics.heightPixels;
//        DEVICE_HEIGHT_DP = (int)(DEVICE_HEIGHT_PX / getResources().getDisplayMetrics().density);
//        DEVICE_WIDTH_DP = (int)(DEVICE_WIDTH_PX / getResources().getDisplayMetrics().density);
//
//        recyclerView = findViewById(R.id.rcv);
//        random = new Random();
//        list = new ArrayList<>();
//        for (int i=1; i<=100; i++){
//            list.add(random.nextInt(100 - 1 +1) + 1);
//        }
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 10, RecyclerView.VERTICAL, false));
//        recyclerView.setAdapter(new Adapter(this, list));

//        txtmax.setOnEditorActionListener((textView, i, keyEvent) -> {
//            if (i == EditorInfo.IME_ACTION_DONE) {
//            }
//            return false;
//        });
        init();

        btn_addrange.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_random.setOnClickListener(this);

    }

    private void init() {
        txtmax = findViewById(R.id.max);
        txtmin = findViewById(R.id.min);
        txtresult = findViewById(R.id.result);
        btn_addrange = findViewById(R.id.btn_addrange);
        btn_reset = findViewById(R.id.btn_reset);
        btn_random = findViewById(R.id.btn_random);
        txtrange = findViewById(R.id.txtrange);

    }

    private boolean Validate() {
        if (txtmax.getText().toString().isEmpty() || txtmin.getText().toString().isEmpty()) {
            Toast.makeText(this, "Khong duoc de trong!!!", Toast.LENGTH_LONG).show();
            return false;
        } else if (Integer.parseInt(txtmax.getText().toString()) <= Integer.parseInt(txtmin.getText().toString())) {
            Toast.makeText(this, "Min khong duoc lon hon Max!!!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
//        else {
//            max = Integer.parseInt(txtmax.getText().toString());
//            min = Integer.parseInt(txtmin.getText().toString());
//            random = new Random();
//            list = new ArrayList<>();
//            result += (random.nextInt(max - min + 1) - min) + " - ";
//            txtresult.setText(result);
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_addrange:
                if (Validate()) {
                    max = Integer.parseInt(txtmax.getText().toString());
                    min = Integer.parseInt(txtmin.getText().toString());
                    DisableView(txtmax);
                    DisableView(txtmin);
                    DisableView(btn_addrange);
                    EnableView(btn_random);
                    for (int i = min; i <= max; i++) {
                        list.add(i);
                    }
                    txtrange.setText(min + "  ~  " + max);
                }
                break;
            case R.id.btn_reset:
                max = 0;
                min = 0;
                list.clear();
                EnableView(txtmax);
                EnableView(txtmin);
                EnableView(btn_addrange);
                DisableView(btn_random);
                txtmin.setText("");
                txtmax.setText("");
                txtresult.setText("");
                list.clear();
                result = "";
                txtrange.setText(0 + "  ~  " + 0);
                break;
            case R.id.btn_random:
                if (list.isEmpty()) {
//                    Toast.makeText(this, "Khong the Random!!!", Toast.LENGTH_LONG).show();
                    DisableView(btn_random);
                } else {
                    random = new Random();
                    temp = random.nextInt(list.size());
                    result += list.get(temp) + "  ";
                    txtresult.setText(result);
                    list.remove(temp);
                }
                break;
        }
    }

    private void EnableView(View v) {
        v.setEnabled(true);
    }

    private void DisableView(View v) {
        v.setEnabled(false);
    }
}


