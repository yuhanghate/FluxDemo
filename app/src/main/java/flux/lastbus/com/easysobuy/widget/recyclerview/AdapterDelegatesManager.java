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
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * 代理管理类
 * Created by yuhang on 16-7-6.
 *
 */
public class AdapterDelegatesManager<T extends AbsAdatperData> {



  /**
   * 自定义Adapter类型集合
   */
  SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat();

  /**
   * 头部Adapter
   */
  private AdapterDelegate headerDelegate;

  /**
   * 底部Adapter
   */
  private AdapterDelegate footerDelegate;

  /**
   * 没数据Adapter
   */
  private EmptyViewAdapter emptyViewDelegate;

  /**
   * 是否增加头部View
   */
  private boolean isHeaderEnable = false;

  /**
   * 是否增加底部View
   */
  private boolean isFooterEnable = false;

  /**
   * 增加Adapter
   * @param delegate
   * @return
     */
  public AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate delegate) {
    int viewType = delegates.size();

    return addDelegate(viewType, false, delegate);
  }

  /**
   * 增加Adapter
   * @param viewType
   * @param delegate
     * @return
     */
  public AdapterDelegatesManager<T> addDelegate(int viewType,
      @NonNull AdapterDelegate delegate) {
    return addDelegate(viewType, false, delegate);
  }

  /**
   * 增加Adapter
   * @param viewType
   * @param allowReplacingDelegate
   * @param delegate
     * @return
     */
  public AdapterDelegatesManager<T> addDelegate(int viewType, boolean allowReplacingDelegate,
      @NonNull AdapterDelegate delegate) {

    if (delegate == null) {
      throw new NullPointerException("AdapterDelegate is null!");
    }



    if (!allowReplacingDelegate && delegates.get(viewType) != null) {
      throw new IllegalArgumentException(
          "An AdapterDelegate is already registered for the viewType = "
              + viewType
              + ". Already registered AdapterDelegate is "
              + delegates.get(viewType));
    }

    delegates.put(viewType, delegate);

    return this;
  }

  /**
   * 增加头部View
   * @param delegate
     */
  public void addHeaderView(@NonNull AdapterDelegate delegate){
    if(delegate == null){
      throw new NullPointerException("Header View AdapterDelegate is null!");
    }

    if(!delegate.isHeaderView()){
      throw new IllegalArgumentException(
              "An AdapterDelegate is already registered for the isHeaderView = "
                      + delegate.isHeaderView()
                      + ". Already registered AdapterDelegate is "
                      + delegate);
    }

    headerDelegate = delegate;
    addDelegate(delegate);
    isHeaderEnable = true;
  }

  /**
   * 加载更多
   * @param delegate
     */
  public void addFooterView(@NonNull AdapterDelegate delegate){
    if(delegate == null){
      throw new NullPointerException("Footer View AdapterDelegate is null!");
    }

    if(!delegate.isFooterView()){
      throw new IllegalArgumentException(
              "An AdapterDelegate is already registered for the isFooterView = "
                      + delegate.isFooterView()
                      + ". Already registered AdapterDelegate is "
                      + delegate);
    }
    footerDelegate = delegate;
    addDelegate(delegate);
    isFooterEnable = true;
  }


  /**
   * 返回底部Adapter对象
   * @return
     */
  public FooterViewAdapter getFooterView(){
    if(footerDelegate instanceof FooterViewAdapter){
      return (FooterViewAdapter) footerDelegate;
    }
    return null;
  }

  /**
   * 返回头部Adapter
   * @return
     */
  public HeaderViewAdapter getHeaderView(){
    if(headerDelegate instanceof HeaderViewAdapter){
      return (HeaderViewAdapter) headerDelegate;
    }
    return null;
  }

  /**
   * 返回所有自定义Adapter
   * @return
     */
  public SparseArrayCompat<AdapterDelegate<T>> getAdapterDelegate(){return delegates;}

  /**
   * 是否增加头部View
   * @return
     */
  public boolean isHeaderEnable(){
    return isHeaderEnable;
  }

  /**
   * 是否增加底部View
   * @return
     */
  public boolean isFooterEnable(){
    return isFooterEnable;
  }


  /**
   * 删除Adapter
   * @param delegate
   * @return
     */
  public AdapterDelegatesManager<T> removeDelegate(@NonNull AdapterDelegate delegate) {

    if (delegate == null) {
      throw new NullPointerException("AdapterDelegate is null");
    }

    int indexToRemove = delegates.indexOfValue(delegate);

    if (indexToRemove >= 0) {
      delegates.removeAt(indexToRemove);
    }
    return this;
  }

  /**
   * 根据id移除Adapter
   *
   * @param viewType The Viewtype
   * @return self
   */
  public AdapterDelegatesManager<T> removeDelegate(int viewType) {
    delegates.remove(viewType);
    return this;
  }

  /**
   * 获取对象对应的类型
   * @param items
   * @param position
   * @return
     */
  public int getItemViewType(@NonNull List<T> items, int position) {

    if (items == null) {
      throw new NullPointerException("Items datasource is null!");
    }

    int delegatesCount = delegates.size();
    for (int i = 0; i < delegatesCount; i++) {
      AdapterDelegate delegate = delegates.valueAt(i);
      if(position >= items.size()){
        throw new IndexOutOfBoundsException("Data resouce size > position");
      }
      if (delegate.isForViewType(items.get(position))) {
        return delegates.keyAt(i);
      }
    }



    throw new NullPointerException(
        "No AdapterDelegate added that matches position=" + position + " in data source");
  }

  /**
   * 创建View
   * @param parent
   * @param viewType
   * @return
     */
  @NonNull
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    AdapterDelegate<T> delegate = getDelegateForViewType(viewType);
    if (delegate == null) {
      throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
    }


    RecyclerView.ViewHolder vh = delegate.onCreateViewHolder(parent);
    if (vh == null) {
      throw new NullPointerException("ViewHolder returned from AdapterDelegate "
          + delegate
          + " for ViewType ="
          + viewType
          + " is null!");
    }
    return vh;
  }

  /**
   * View绑定数据
   * @param items
   * @param position
   * @param viewHolder
     */
  public void onBindViewHolder(@NonNull List<T> items, int position,
                               @NonNull RecyclerView.ViewHolder viewHolder) {

    AdapterDelegate delegate = getDelegateForViewType(viewHolder.getItemViewType());
    if (delegate == null) {
      throw new NullPointerException("No delegate found for item at position = "
          + position
          + " for viewType = "
          + viewHolder.getItemViewType());
    }
    delegate.onBindViewHolder(items, position, viewHolder);
  }


  /**
   * 获取View类型
   * @param delegate
   * @return
     */
  public int getViewType(@NonNull AdapterDelegate delegate) {
    if (delegate == null) {
      throw new NullPointerException("Delegate is null");
    }

    int index = delegates.indexOfValue(delegate);
    if (index == -1) {
      return -1;
    }
    return delegates.keyAt(index);
  }

  /**
   * 获取类型对应的Adapter
   * @param viewType
   * @return
     */
  @Nullable
  public AdapterDelegate getDelegateForViewType(int viewType) {
    AdapterDelegate delegate = delegates.get(viewType);

    return delegate;
  }


  /**
   * 滑动停止
   */
  public void onScrollPause(){
    for(int i=0; i<delegates.size(); i++){
      AdapterDelegate<T> delegate = delegates.get(i);
      delegate.onScrollPause();
    }
  }

  /**
   * 滑动重新开始
   */
  public void onScroollResume(){
    for(int i=0; i<delegates.size(); i++){
      AdapterDelegate<T> delegate = delegates.get(i);
      delegate.onScrollPause();
    }
  }

  /**
   * 增加没有数据时显示的Adapter
   * @param delegate
     */
  public void addEmptyView(@NonNull EmptyViewAdapter delegate){
    if(delegate == null)
      throw new NullPointerException("No AdapterDelegate added is Null! ");

    emptyViewDelegate = delegate;
    addDelegate(delegate);
  }

  /**
   * 获取空数据Adapter
   * @return
     */
  public EmptyViewAdapter getEmptyView(){
    return emptyViewDelegate;
  }

}
