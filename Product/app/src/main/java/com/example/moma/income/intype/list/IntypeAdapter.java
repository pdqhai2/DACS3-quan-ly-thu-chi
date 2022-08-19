package com.example.moma.income.intype.list;

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
import com.example.moma.income.detail.IncomeDetail;
import com.example.moma.income.list.byIntype.IncomeListByIntype;
import com.example.moma.models.Intype;

import java.util.List;

public class IntypeAdapter extends RecyclerView.Adapter<IntypeAdapter.IntypeViewHolder> {

    private Context context;
    private List<Intype> intypeList;

    public IntypeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Intype> intypeList) {
        this.intypeList = intypeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IntypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.intype_list_item,parent,false);
        return new IntypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntypeViewHolder holder, int position) {
        holder.intypeId.setText(String.valueOf(intypeList.get(position).getIntype_id()));
        holder.intypeName.setText(intypeList.get(position).getIntype_name());
    }

    @Override
    public int getItemCount() {
        if (intypeList != null)
            return intypeList.size();
        else return 0;
    }

    public class IntypeViewHolder extends RecyclerView.ViewHolder {

        private TextView intypeId, intypeName, incomeNum;
        private Button view;

        public IntypeViewHolder(@NonNull View itemView) {
            super(itemView);
            intypeId = itemView.findViewById(R.id.intype_id);
            intypeName = itemView.findViewById(R.id.intype_nameTv);
            incomeNum = itemView.findViewById(R.id.intype_incomeNum);
            view = itemView.findViewById(R.id.intype_icon_viewBtn);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent( itemView.getContext(), IncomeListByIntype.class);
                    int intype_id = Integer.parseInt(intypeId.getText().toString());
                    detail.putExtra("intype_id", intype_id);
                    context.startActivity(detail);
                }
            });
        }
    }
}
