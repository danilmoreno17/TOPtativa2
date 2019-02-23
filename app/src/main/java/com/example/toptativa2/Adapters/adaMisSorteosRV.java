package com.example.toptativa2.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toptativa2.Models.Juego;
import com.example.toptativa2.R;

import java.util.List;

public class adaMisSorteosRV extends RecyclerView.Adapter<JuegoViewHolder>{

    private Context mContext;
    private List<Juego> mJuegoList;

    public adaMisSorteosRV(Context _pContext, List<Juego> _pJuegoList){
        this.mContext = _pContext;
        this.mJuegoList = _pJuegoList;
    }

    @NonNull
    @Override
    public JuegoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rvmissorteos_item_row,viewGroup,false);
        return new JuegoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull JuegoViewHolder juegoViewHolder, int i) {
        juegoViewHolder.mNombreJuego.setText(mJuegoList.get((i)).getNombre_juego());
        juegoViewHolder.mNumTicket.setText("3 tickets comprados");
        juegoViewHolder.mFechaPremio.setText(mJuegoList.get(i).getFecha_juego());
    }

    @Override
    public int getItemCount() {
        return mJuegoList.size();
    }
}
class JuegoViewHolder extends RecyclerView.ViewHolder{
    TextView mNombreJuego, mNumTicket, mFechaPremio;
    JuegoViewHolder(View itemView) {
        super(itemView);
        mNombreJuego = (TextView)itemView.findViewById(R.id.tvNombreJuego);
        mNumTicket = (TextView)itemView.findViewById(R.id.tvNumTicket);
        mFechaPremio = (TextView)itemView.findViewById(R.id.tvFechaPremio);
    }
}