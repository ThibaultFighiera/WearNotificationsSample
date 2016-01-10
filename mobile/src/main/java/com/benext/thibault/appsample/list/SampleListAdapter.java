/*
 *  Copyright (C) 2016 Thibault Fighiera
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.benext.thibault.appsample.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benext.thibault.appsample.R;
import com.benext.thibault.appsample.notification.orders.Order;

import java.util.ArrayList;
import java.util.List;

public class SampleListAdapter extends RecyclerView.Adapter<SampleViewHolder> {
    private final List<Order> mOrders;

    public SampleListAdapter(ArrayList<Order> orderList) {
        mOrders = orderList;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new SampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        int title = mOrders.get(position).getName();
        holder.setTitle(title);
        holder.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }
}
