package com.example.moma.outcome.outtype.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moma.R;
import com.example.moma.income.list.IncomeAdapter;
import com.example.moma.models.Outcome;
import com.example.moma.models.Outtype;

import java.util.List;

public class OuttypeAdapter extends RecyclerView.Adapter<OuttypeAdapter.OuttypeViewHolder> {
    private Context context;
    private List<Outtype> outtypeList;

    public OuttypeAdapter(Context context) {
        this.context = context;
    }

    public void setOuttypeList(List<Outtype> outtypeList) {
        this.outtypeList = outtypeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OuttypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outtype_list_item,parent,false);
        return new OuttypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OuttypeViewHolder holder, int position) {
        holder.outtypeId.setText(String.valueOf(outtypeList.get(position).getOuttype_id()));
        holder.outtypeName.setText(outtypeList.get(position).getOuttype_name());
    }

    @Override
    public int getItemCount() {
        if (outtypeList != null)
            return outtypeList.size();
        else return 0;
    }

    public class OuttypeViewHolder extends RecyclerView.ViewHolder {

        private TextView outtypeId, outtypeName;

        public OuttypeViewHolder(@NonNull View itemView) {
            super(itemView);
            outtypeId = itemView.findViewById(R.id.outtype_id);
            outtypeName = itemView.findViewById(R.id.outtype_nameTv);
        }
    }
}
