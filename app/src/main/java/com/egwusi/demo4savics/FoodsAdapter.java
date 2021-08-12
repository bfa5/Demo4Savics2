package com.egwusi.demo4savics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder> {
    Context context;
    List<Food> mFoodData;

    public FoodsAdapter(Context context, List<Food> foodData) {
        this.mFoodData = foodData;
        this.context = context;
    }

    @Override
    public FoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.foods_list_items, null);
        FoodsViewHolder foodsViewHolder = new FoodsViewHolder(view);
        return foodsViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodsViewHolder holder, final int position) {
        // set the data
        holder.name.setText("Name: " + mFoodData.get(position).getName());
        holder.price.setText("price: " + mFoodData.get(position).getPrice());
        //holder.foodImage.setImageResource(mFoodData.get(position).getId());
        // implement setONCLickListtener on itemView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display foodDetailFragment
                Toast.makeText(context, "Name: " + mFoodData.get(position).getName() +
                        " price: "+ mFoodData.get(position).getPrice(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodData.size(); // size of the list items
    }

    class FoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // init the item view's
        TextView name, price, addToCart;
        ImageView foodImage;

        public FoodsViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = itemView.findViewById(R.id.foodName);
            price =  itemView.findViewById(R.id.foodPrice);
            foodImage =  itemView.findViewById(R.id.foodImage);

            addToCart =  itemView.findViewById(R.id.btnAddCart);
            addToCart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "food name " + name, Toast.LENGTH_SHORT).show();

        }
    }
}
