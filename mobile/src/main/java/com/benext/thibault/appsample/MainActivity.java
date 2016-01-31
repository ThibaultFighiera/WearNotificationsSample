/*
 *
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
 */

package com.benext.thibault.appsample;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.benext.thibault.appsample.list.RecyclerItemClickListener;
import com.benext.thibault.appsample.list.SampleListAdapter;
import com.benext.thibault.appsample.notification.orders.Order;
import com.benext.thibault.appsample.notification.orders.SendNotifExtenderActionOrder;
import com.benext.thibault.appsample.notification.orders.SendNotifExtenderOrder;
import com.benext.thibault.appsample.notification.orders.SendNotifExtenderPages;
import com.benext.thibault.appsample.notification.orders.SendSimpleBGNotifOrder;
import com.benext.thibault.appsample.notification.orders.SendSimpleNotifOrder;

import java.util.ArrayList;

/**
 * Created by Thibault Fighiera on 30/12/2015
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Order> mOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mOrders = getOrderList();

        RecyclerView list = (RecyclerView) findViewById(R.id.sample_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(new SampleListAdapter(mOrders));
        list.addOnItemTouchListener(new RecyclerItemClickListener(this, list, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view != null) {
                    Object tag = view.getTag();

                    if (tag instanceof Integer) {
                        Order order = mOrders.get(((Integer) tag));
                        order.execute(view.getContext());

                        String snackSend = getString(R.string.notification_sent);
                        String orderName = getString(order.getName());
                        Snackbar.make(view, String.format(snackSend, orderName), Snackbar.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));
    }

    private ArrayList<Order> getOrderList() {
        ArrayList<Order> list = new ArrayList<>();
        list.add(new SendSimpleNotifOrder());
        list.add(new SendSimpleBGNotifOrder());
        list.add(new SendNotifExtenderOrder());
        list.add(new SendNotifExtenderActionOrder());
        list.add(new SendNotifExtenderPages());
        return list;
    }
}
