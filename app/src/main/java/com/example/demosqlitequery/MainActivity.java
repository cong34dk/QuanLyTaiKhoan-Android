package com.example.demosqlitequery;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Khai báo biến
    DataBaseHandler db;
    EditText txtdn, txtmk;
    Button btnThem, btnSua, btnXoa;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //PT khỏi tạo
        Init();

        // Khởi tạo DataBaseHandler
        db = new DataBaseHandler(this);

        // Hiển thị dữ liệu từ cơ sở dữ liệu vào ListView
        DataListView();

        // Sự kiện cho các button thêm, sửa, xóa
        HandleButton();

    }

    private void HandleButton() {
        //Xử lý sự kiện nút thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDangNhap = txtdn.getText().toString();
                String strMatKhau = txtmk.getText().toString();

                if (strDangNhap.isEmpty() || strMatKhau.isEmpty()) {
                    // Kiểm tra xem đã nhập đủ thông tin chưa
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Thêm dữ liệu vào cơ sở dữ liệu
                    db.insertData(strDangNhap, strMatKhau);

                    // Hiển thị lại dữ liệu trong ListView
                    DataListView();

                    // Xóa nội dung trong EditText
                    txtdn.setText("");
                    txtmk.setText("");

                    // Focus vào EditText tên đăng nhập
                    txtdn.requestFocus();

                }
            }
        });

    }

    //Ánh xạ
    private void Init() {
        // Ánh xạ
        txtdn = findViewById(R.id.txtUser);
        txtmk = findViewById(R.id.txtPassword);
        btnThem = findViewById(R.id.btnAdd);
        btnSua = findViewById(R.id.btnEdit);
        btnXoa = findViewById(R.id.btnDelete);
        lv = findViewById(R.id.lstView);
    }


    //Phương thức hiển thị dữ liệu lên ListView
    private void DataListView() {
        // Lấy dữ liệu từ cơ sở dữ liệu
        Cursor cursor = db.getAllData();

        if (cursor.getCount() == 0) {
            // Không có dữ liệu
            return;
        }

        // Chuẩn bị dữ liệu để hiển thị
        ArrayList<String> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            data.add(cursor.getString(1) + " - " + cursor.getString(2));
        }

        // Hiển thị dữ liệu lên ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
    }
}
