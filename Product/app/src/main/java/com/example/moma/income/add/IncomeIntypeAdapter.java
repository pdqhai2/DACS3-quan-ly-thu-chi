package com.example.moma.income.add;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moma.R;
import com.example.moma.models.Intype;

import java.util.List;

public class IncomeIntypeAdapter extends RecyclerView.Adapter<IncomeIntypeAdapter.IntypeViewHolder2> {

    private Context context;
    private List<Intype> intypeList;
    int intype_id;
    String intype_name;

    public IncomeIntypeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Intype> intypeList) {
        this.intypeList = intypeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IntypeViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_add_type_item,parent,false);
        return new IntypeViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntypeViewHolder2 holder, int position) {
        holder.userid.setText(String.valueOf(intypeList.get(position).getUser_id()));
        holder.intypeId.setText(String.valueOf(intypeList.get(position).getIntype_id()));
        holder.intypeName.setText(intypeList.get(position).getIntype_name());
    }

    @Override
    public int getItemCount() {
        if (intypeList != null)
            return intypeList.size();
        else return 0;
    }


    public class IntypeViewHolder2 extends RecyclerView.ViewHolder {

        private TextView intypeId, intypeName, userid;
        private Button chose;

        public IntypeViewHolder2(@NonNull View itemView) {
            super(itemView);
            userid = itemView.findViewById(R.id.income_add_user_id);
            intypeId = itemView.findViewById(R.id.incomeaddintype_id);
            intypeName = itemView.findViewById(R.id.incomeaddintype_nameTv);
            chose = itemView.findViewById(R.id.income_add_btnType);

            chose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), IncomeAddForm.class);
                    intype_id = Integer.parseInt(intypeId.getText().toString());
                    intype_name = intypeName.getText().toString();
                    intent.putExtra("intype_id", intype_id);
                    intent.putExtra("intype_name",intype_name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
