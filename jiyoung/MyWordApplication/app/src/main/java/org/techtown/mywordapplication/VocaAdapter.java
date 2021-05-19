package org.techtown.mywordapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class VocaAdapter extends RecyclerView.Adapter<VocaAdapter.ViewHolder> {
    ArrayList<Voca> items = new ArrayList<Voca>();
    DatabaseHelper dbHelper;
    SQLiteDatabase database;
    Context context;

    String english = "";
    String korean = "";
    String memo = "";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.voca, viewGroup, false);

        context = viewGroup.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Voca voca = items.get(position);
        viewHolder.setItem(voca);

//        viewHolder.modifyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                //LayoutInflater inflater = (LayoutInflater)itemView.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//                View view = LayoutInflater.from(context)
//                            .inflate(R.layout.modify, null, false);
//                builder.setView(view);
//
//                EditText modifyENGLISHText = view.findViewById(R.id.modifyENGLISHText);
//                EditText modifyKOREANText = view.findViewById(R.id.modifyKOREANText);
//                EditText modifyMEMOText = view.findViewById(R.id.modifyMEMOText);
//                Button modifycompleteButton = view.findViewById(R.id.modifycompleteButton);
//
//                english = items.get(position).getENGLISH();
//                korean = items.get(position).getKOREAN();
//                memo = items.get(position).getMEMO();
//
//                //Log.d("MYTAG", "english: " + english + " korean: " + korean +" memo: " + memo);
//
//                modifyENGLISHText.setText(english +"");
//                modifyKOREANText.setText(korean+"");
//                modifyMEMOText.setText(memo+"");
//
//                AlertDialog dialog = builder.create();
//                modifycompleteButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String ENG = modifyENGLISHText.getText().toString();
//                        String KOR = modifyKOREANText.getText().toString();
//                        String MEM = modifyMEMOText.getText().toString();
//
//                        database.execSQL("UPDATE VOCA SET english=" + ENG +
//                                        ", korean=" + KOR +
//                                        ", memo=" + MEM +
//                                        " WHERE english=" + ENG);
//
//                        Voca voca = new Voca(ENG, KOR, MEM);
//                        items.set(position, voca);
//                        notifyItemChanged(position);
//
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        });

//        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                english = items.get(position).getENGLISH();
//                items.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, items.size());
//                database.execSQL("DELETE FROM VOCA WHERE english ='" + english + "'");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Voca voca) {
        items.add(voca);
        //notifyDataSetChanged();
    }

    public void setItems(ArrayList<Voca> voca){
        this.items = items;
    }

    public Voca getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, Voca voca){
        items.set(position, voca);
    }

    public void removeItem() {
        items.clear();
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ENGLISHText;
        TextView KOREANText;
        TextView MEMOText;
        Button modifyButton;
        Button deleteButton;


        public ViewHolder(View itemView) {
            super(itemView);

            dbHelper = new DatabaseHelper(itemView.getContext());
            database = dbHelper.getWritableDatabase();

            ENGLISHText = itemView.findViewById(R.id.ENGLISHText);
            KOREANText = itemView.findViewById(R.id.KOREANText);
            MEMOText = itemView.findViewById(R.id.MEMOText);
            modifyButton = itemView.findViewById(R.id.modifyButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            modifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    //LayoutInflater inflater = (LayoutInflater)itemView.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View view = LayoutInflater.from(itemView.getContext())
                            .inflate(R.layout.modify, null, false);
                    builder.setView(view);

                    EditText modifyENGLISHText = view.findViewById(R.id.modifyENGLISHText);
                    EditText modifyKOREANText = view.findViewById(R.id.modifyKOREANText);
                    EditText modifyMEMOText = view.findViewById(R.id.modifyMEMOText);
                    Button modifycompleteButton = view.findViewById(R.id.modifycompleteButton);

                    modifyENGLISHText.setText(items.get(getAdapterPosition()).getENGLISH());
                    modifyKOREANText.setText(items.get(getAdapterPosition()).getKOREAN());
                    modifyMEMOText.setText(items.get(getAdapterPosition()).getMEMO());

                    final AlertDialog dialog = builder.create();
                    modifycompleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String ENG = modifyENGLISHText.getText().toString();
                            String KOR = modifyKOREANText.getText().toString();
                            String MEM = modifyMEMOText.getText().toString();

                            database.execSQL("UPDATE VOCA SET english ='" + ENG
                                    +"', korean ='" + KOR
                                    +"', memo ='" + MEM
                                    +"'");

                            Voca voca = new Voca(ENG, KOR, MEM);
                            items.set(getAdapterPosition(), voca);
                            notifyItemChanged(getAdapterPosition());

                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    english = items.get(getAdapterPosition()).getENGLISH();
                    items.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), items.size());
                    database.execSQL("DELETE FROM VOCA WHERE english ='" + english + "'");
                }
            });

        }

        public void setItem(Voca voca){
            ENGLISHText.setText(voca.getENGLISH());
            KOREANText.setText(voca.getKOREAN());
            MEMOText.setText(voca.getMEMO());
        }
    }
}
