package com.example.moma.outcome.add;

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
import com.example.moma.models.Outtype;

import java.util.List;

public class OutcomeOuttypeAdater extends RecyclerView.Adapter<OutcomeOuttypeAdater.OutcomeOuttypeViewHolder> {

    private Context context;
    private List<Outtype> outtypeList;
    int outtype_id;
    String outtype_name;

    public OutcomeOuttypeAdater(Context context) {
        this.context = context;
    }

    public void setOuttypeList(List<Outtype> outtypeList) {
        this.outtypeList = outtypeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OutcomeOuttypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outcome_add_type_item,parent,false);
        return new OutcomeOuttypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutcomeOuttypeViewHolder holder, int position) {
        holder.userid.setText(String.valueOf(outtypeList.get(position).getUser_id()));
        holder.outtypeId.setText(String.valueOf(outtypeList.get(position).getOuttype_id()));
        holder.outtypeName.setText(outtypeList.get(position).getOuttype_name());
    }

    @Override
    public int getItemCount() {
        if (outtypeList != null)
            return outtypeList.size();
        else return 0;
    }

    public class OutcomeOuttypeViewHolder extends RecyclerView.ViewHolder {

        private TextView outtypeId, outtypeName, userid;
        private Button chose;

        public OutcomeOuttypeViewHolder(@NonNull View itemView) {
            super(itemView);
            userid = itemView.findViewById(R.id.outcome_add_user_id);
            outtypeId = itemView.findViewById(R.id.outcomeaddintype_id);
            outtypeName = itemView.findViewById(R.id.outcomeaddintype_nameTv);
            chose = itemView.findViewById(R.id.outcome_add_btnType);

            chose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), OutcomeAddForm.class);
                    outtype_id = Integer.parseInt(outtypeId.getText().toString());
                    outtype_name = outtypeName.getText().toString();
                    intent.putExtra("outtype_id", outtype_id);
                    intent.putExtra("outtype_name",outtype_name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
