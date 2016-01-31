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
import android.view.View;
import android.widget.TextView;

import com.benext.thibault.appsample.R;

/**
 * Created by Thibault Fighiera on 30/12/2015
 */
public class SampleViewHolder extends RecyclerView.ViewHolder {

    private final TextView name;

    public SampleViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.item_title);
    }

    public void setTitle(int title) {
        name.setText(title);
    }

    public void setTag(Object tag) {
        itemView.setTag(tag);
    }
}
