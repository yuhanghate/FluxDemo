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

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 代理Adapter基类
 * Created by yuhang on 16-7-6.
 * @param <T> 代理每个View布局ViewHolder对应的数据绑定对象
 */
public interface AdapterDelegate<T extends BaseAdapterData> {

  /**
   * 匹配View 类型
   * @param item
     * @return
     */
  boolean isForViewType(@NonNull BaseAdapterData item);

  /**
   * 创建View类型
   * @param parent
   * @return
     */
  @NonNull
  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);


  /**
   * View绑定数据
   * @param items
   * @param position
   * @param holder
     */
  void onBindViewHolder(@NonNull List<T> items, int position, @NonNull RecyclerView.ViewHolder holder);

  /**
   * Itme点击事件
   * @param listener
     */
  void setOnItemClickListener(BaseViewHolder.OnItemClickListener listener);

  /**
   * Item点击事件
   * @return
     */
  BaseViewHolder.OnItemClickListener getOnItemclickListener();






  /**
   * 是否是头部View
   * @return
     */
  boolean isHeaderView();


    /**
     * 是否是底部View
     * @return
     */
  boolean isFooterView();

  /**
   * RecyclerView滑动停止
   */
  void onScrollPause();

  /**
   * RecyclerView滑动重启
   */
  void onScrollResume();



}
