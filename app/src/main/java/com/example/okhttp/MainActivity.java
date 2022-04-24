package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.okhttp.actions.IShopUpdate;
import com.example.okhttp.adapter.ProductAdapter;
import com.example.okhttp.data.remote.RetrofitClient;
import com.example.okhttp.data.remote.SongService;
import com.example.okhttp.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements IShopUpdate {
    Button btnClick;
    RecyclerView rcv;
    ProductAdapter adapter;
    ImageButton imgbtUpdate,imgbtDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = findViewById(R.id.rcv_product);
        imgbtUpdate =(ImageButton) findViewById(R.id.img_button_update);
        imgbtDelete = findViewById(R.id.img_button_delete);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        rcv.setLayoutManager(linearLayoutManager);


//        btnClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                (new HTTPReqTask()).execute();
//            }
//        });
        update();

    }

    @Override
    public void update() {
        SongService service = RetrofitClient.getInstance().getSongService();
        Call<List<Product>> repos = service.listProducts();
        repos.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    adapter = new ProductAdapter(response.body(),getApplicationContext(), MainActivity.this);
                    rcv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("App error", t.toString());
            }
        });
    }

}