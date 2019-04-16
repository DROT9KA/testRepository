package ua.study.awesome.androidlessons.testtask_skysoft.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.study.awesome.androidlessons.testtask_skysoft.R;
import ua.study.awesome.androidlessons.testtask_skysoft.data.response.Bank;
import ua.study.awesome.androidlessons.testtask_skysoft.interfaces.ClickListener;
import ua.study.awesome.androidlessons.testtask_skysoft.ui.view_holders.BankViewHolder;

public class BankAdapter extends RecyclerView.Adapter<BankViewHolder> {

    private ClickListener clickListener;

    private ArrayList<Bank> banks;

    private Context context;

    public BankAdapter(Context context) {
        this.context = context;
        banks = new ArrayList<>();
    }

    @NonNull
    @Override
    public BankViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_bank, viewGroup, false);
        return new BankViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BankViewHolder bankViewHolder, int i) {
        Bank bank = banks.get(i);

        bankViewHolder.onBindBank(bank);
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }

    public void setBanks(ArrayList<Bank> banks) {
        this.banks = banks;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
}