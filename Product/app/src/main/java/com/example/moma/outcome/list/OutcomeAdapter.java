package com.example.moma.outcome.list;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moma.R;
import com.example.moma.income.detail.IncomeDetail;
import com.example.moma.models.Outcome;
import com.example.moma.outcome.detail.OutcomeDetail;

import java.util.List;

public class OutcomeAdapter extends RecyclerView.Adapter<OutcomeAdapter.OutcomeViewHolder> {
    Context context;
    List<Outcome> outcomeList;

    public OutcomeAdapter(Context context) {
        this.context = context;
    }

    public void setOutcomeList(List<Outcome> outcomeList) {
        this.outcomeList = outcomeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OutcomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.outcome_list_item,parent,false);
        return new OutcomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OutcomeViewHolder holder, int position) {
        holder.outcomeId.setText(String.valueOf(outcomeList.get(position).getOutcome_id()));
        holder.outcomeName.setText(outcomeList.get(position).getOutcome_name());
        holder.outcomeVal.setText(outcomeList.get(position).getOutcome_valstring());
        holder.outcomeDay.setText(outcomeList.get(position).getOutcome_day());
        holder.outcomeMonth.setText(outcomeList.get(position).getOutcome_month());
        holder.outcomeYear.setText(outcomeList.get(position).getOutcome_year());
    }

    @Override
    public int getItemCount() {
        if (outcomeList != null)
            return outcomeList.size();
        else return 0;
    }

    public class OutcomeViewHolder extends RecyclerView.ViewHolder {

        private TextView outcomeId, outcomeName, outcomeVal, outcomeDay, outcomeMonth, outcomeYear;

        public OutcomeViewHolder(@NonNull View itemView) {
            super(itemView);

            outcomeId = itemView.findViewById(R.id.outcome_id);
            outcomeName = itemView.findViewById(R.id.outcome_nameTv);
            outcomeVal = itemView.findViewById(R.id.outcome_valTv);
            outcomeDay = itemView.findViewById(R.id.outcome_dayTv);
            outcomeMonth = itemView.findViewById(R.id.outcome_monthTv);
            outcomeYear = itemView.findViewById(R.id.outcome_yearTv);

            outcomeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent( itemView.getContext(), OutcomeDetail.class);
                    int income_id = Integer.parseInt(outcomeId.getText().toString());
                    detail.putExtra("outcome_id", income_id);
                    context.startActivity(detail);
                }
            });
        }
    }
}
