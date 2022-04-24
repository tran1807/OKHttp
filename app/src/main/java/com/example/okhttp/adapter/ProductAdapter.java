package com.example.okhttp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.okhttp.R;
import com.example.okhttp.actions.IShopUpdate;
import com.example.okhttp.data.remote.RetrofitClient;
import com.example.okhttp.data.remote.SongService;
import com.example.okhttp.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    public List<Product> productList;
    public Context context;
    IShopUpdate shopUpdate;

    public ProductAdapter(List<Product> productList, Context context, IShopUpdate shopUpdate) {
        this.productList = productList;
        this.context = context;
        this.shopUpdate = shopUpdate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.product,parent,false);
//        return new ViewHolder(view);
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView = inflater.inflate(R.layout.product, parent, false);
        ViewHolder viewHolder = new ViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
         holder.txtname.setText("ten : "+product.name);
//         holder.txtid.setText(product.id);
         holder.txtsoluong.setText("sl: " + product.soluong);
        Picasso.with(context).load(product.img).into(holder.img);
        holder.imgbtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SongService service = RetrofitClient.getInstance().getSongService();
                service.deleteProduct(product.id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        shopUpdate.update();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtname,txtid,txtsoluong;
        ImageButton imgbtUpdate,imgbtDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageViewHinhAnh);
            txtname = itemView.findViewById(R.id.textViewName);
//            txtid = itemView.findViewById(R.id.textViewid);
            txtsoluong = itemView.findViewById(R.id.textViewsl);
            imgbtDelete = itemView.findViewById(R.id.img_button_delete);

        }
    }
}
