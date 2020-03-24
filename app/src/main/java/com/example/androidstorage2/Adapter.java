package com.example.androidstorage2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<EmployeeInformation> list;
    Context context;
    public Adapter(Context context,List<EmployeeInformation> list){
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.employee_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.textViewname.setText(list.get(position).getName());
        holder.textViewaddress.setText(list.get(position).getAddress());
        holder.textViewnumber.setText(list.get(position).getMobilenumber());
        holder.textViewmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.textViewmenu);
                popupMenu.inflate(R.menu.option_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.update:

                                Intent intent = new Intent(context, DataInsertActivity.class);

                                context.startActivity(intent);
                                break;

                            case R.id.delete:
                                deleteRecord(list.get(position), position);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void deleteRecord(final EmployeeInformation employeeInformation,final int position){
        class DeleteRecord extends AsyncTask<Void,Void,Void>{


            @Override
            protected Void doInBackground(Void... voids) {

                MyAppDatabase.getDatabase(context).dataAccessObject().deleteEmployee(employeeInformation);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                try {
                    list.remove(position);
                    notifyItemChanged(position);
                    Toast.makeText(context, "Data Deleted....", Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }
            }
        }
        DeleteRecord deleteRecord=new DeleteRecord();
        deleteRecord.execute();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewname;
        private TextView textViewaddress;
        private TextView textViewnumber;
        private TextView textViewmenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewname=itemView.findViewById(R.id.textviewname);
            textViewaddress=itemView.findViewById(R.id.textviewaddress);
            textViewnumber=itemView.findViewById(R.id.textviewnumber);
            textViewmenu=itemView.findViewById(R.id.textviewmenu);
        }
    }
}
