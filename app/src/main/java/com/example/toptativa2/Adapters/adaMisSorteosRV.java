package com.example.toptativa2.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toptativa2.EurekaAppAplication;
import com.example.toptativa2.Models.Premiacion;
import com.example.toptativa2.Models.Publicacion;
import com.example.toptativa2.R;
import com.example.toptativa2.db.PremiacionDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class adaMisSorteosRV extends RecyclerView.Adapter<JuegoViewHolder>{
    private PremiacionDataSource pds;
    private Context mContext;
    private List<Publicacion> mPublicacionList;

    public adaMisSorteosRV(Context _pContext, List<Publicacion> _pPublicacionList){
        this.mContext = _pContext;
        this.mPublicacionList = _pPublicacionList;
        pds = new PremiacionDataSource(mContext);
    }

    @NonNull
    @Override
    public JuegoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rvmissorteos_item_row,viewGroup,false);
        return new JuegoViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull JuegoViewHolder juegoViewHolder, int i) {

        juegoViewHolder.mNombreJuego.setText(mPublicacionList.get((i)).getNombre_juego());
        try {
            List<Premiacion> mPremiacionList = new ArrayList<>();
            pds.open();
            mPremiacionList = pds.premiacionListByPublicacion(mPublicacionList.get(i).getId());
            juegoViewHolder.mNumTicket.setText(mPremiacionList.size()+" tickets comprados");
            File f=new File(((EurekaAppAplication)mContext.getApplicationContext()).UsuarioActual.getUrlImage());
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            juegoViewHolder.ivLogo.setImageBitmap(b);
        }catch (Exception e){

        }
        juegoViewHolder.mFechaPremio.setText(mPublicacionList.get(i).getFecha_tope());
    }

    @Override
    public int getItemCount() {
        return mPublicacionList.size();
    }
}
class JuegoViewHolder extends RecyclerView.ViewHolder{
    TextView mNombreJuego, mNumTicket, mFechaPremio;
    ImageView ivLogo;
    JuegoViewHolder(View itemView) {
        super(itemView);
        mNombreJuego = (TextView)itemView.findViewById(R.id.tvNombreJuego);
        mNumTicket = (TextView)itemView.findViewById(R.id.tvNumTicket);
        mFechaPremio = (TextView)itemView.findViewById(R.id.tvFechaPremio);
        ivLogo = (ImageView)itemView.findViewById(R.id.iv_logo);
    }
}