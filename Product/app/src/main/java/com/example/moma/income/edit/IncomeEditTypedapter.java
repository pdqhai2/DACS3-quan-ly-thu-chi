package com.example.moma.income.edit;

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
import com.example.moma.income.add.IncomeAddForm;
import com.example.moma.income.add.IncomeIntypeAdapter;
import com.example.moma.models.Intype;

import java.util.List;

public class IncomeEditTypedapter extends RecyclerView.Adapter<IncomeEditTypedapter.IntypeViewHolder2> {
    private Context context;
    private List<Intype> intypeList;
    int intype_id;
    String intype_name;

    public IncomeEditTypedapter(Context context) {
        this.context = context;
    }

    public void setData(List<Intype> intypeList) {
        this.intypeList = intypeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IncomeEditTypedapter.IntypeViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_edit_type_item,parent,false);
        return new IncomeEditTypedapter.IntypeViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeEditTypedapter.IntypeViewHolder2 holder, int position) {
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
                    Intent intent = new Intent(itemView.getContext(), IncomeEdit.class);
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
