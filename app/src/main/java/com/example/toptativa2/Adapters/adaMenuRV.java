package com.example.toptativa2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toptativa2.LoteriaNacionalActivity;
import com.example.toptativa2.Models.Option;
import com.example.toptativa2.R;

import java.util.List;

public class adaMenuRV extends RecyclerView.Adapter<OptionViewHolder> {

    private Context mContext;
    private List<Option> mOptionList;

    public adaMenuRV(Context _pContext, List<Option> _pOptionList){
        this.mContext = _pContext;
        this.mOptionList = _pOptionList;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rvmenu_item_row,viewGroup,false);
        return new OptionViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, final int i) {
        optionViewHolder.mImage.setImageResource(mOptionList.get(i).getImage());
        optionViewHolder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i){
                    case 3:
                        Intent intent = new Intent(mContext.getApplicationContext(),LoteriaNacionalActivity.class);
                        mContext.startActivity(intent);
                    break;
                    default:
                    break;
                }
            }
        });
        optionViewHolder.mTitle.setText(mOptionList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mOptionList.size();
    }
}
class OptionViewHolder extends RecyclerView.ViewHolder{
    ImageView mImage;
    TextView mTitle;
    OptionViewHolder(View itemView){
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
    }
}