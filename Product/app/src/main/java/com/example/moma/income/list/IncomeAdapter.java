package com.example.moma.income.list;

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
import com.example.moma.models.Income;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    private Context context;
    private List<Income> incomeList;

    public IncomeAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Income> incomeList) {
        this.incomeList = incomeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_list_item,parent,false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        holder.incomeId.setText(String.valueOf(incomeList.get(position).getIncome_id()));
        holder.incomeName.setText(incomeList.get(position).getIncome_name());
        holder.incomeVal.setText(incomeList.get(position).getIncome_valstring());
        holder.incomeDay.setText(incomeList.get(position).getIncome_day());
        holder.incomeMonth.setText(incomeList.get(position).getIncome_month());
        holder.incomeYear.setText(incomeList.get(position).getIncome_year());
    }

    @Override
    public int getItemCount() {
        if (incomeList != null)
            return incomeList.size();
        else return 0;
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {

        private TextView incomeId, incomeName, incomeVal, incomeDay, incomeMonth, incomeYear;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            incomeId = itemView.findViewById(R.id.income_id);
            incomeName = itemView.findViewById(R.id.income_nameTv);
            incomeVal = itemView.findViewById(R.id.income_valTv);
            incomeDay = itemView.findViewById(R.id.income_dayTv);
            incomeMonth = itemView.findViewById(R.id.income_monthTv);
            incomeYear = itemView.findViewById(R.id.income_yearTv);

            incomeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent( itemView.getContext(), IncomeDetail.class);
                    int income_id = Integer.parseInt(incomeId.getText().toString());
                    detail.putExtra("income_id", income_id);
                    context.startActivity(detail);
                }
            });
        }
    }
}
