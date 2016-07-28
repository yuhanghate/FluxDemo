/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyvlerView控件的Adapter适配器
 *
 */
public abstract class AbsDelegationAdapter extends RecyclerView.Adapter {

  protected AdapterDelegatesManager<AbsAdatperData> delegatesManager = new AdapterDelegatesManager<>();
  protected List<AbsAdatperData> items;




  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return delegatesManager.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    delegatesManager.onBindViewHolder(items, position, holder);
  }

  @Override public int getItemViewType(int position) {
    return delegatesManager.getItemViewType(items, position);
  }


  /**
   * 获取Adatper数据源
   *
   * @return The items / data source
   */
  public List<AbsAdatperData> getItems() {
    return items;
  }

  /**
   * 设置Adapter数据源
   *
   * @param items The items / data source
   */
  public void setItems(List<AbsAdatperData> items) {
    this.items = items;
  }

}
