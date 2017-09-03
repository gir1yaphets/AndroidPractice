package com.example.a01_viewdraw;

/**
 * Created by pengxiaolve on 17/7/23.
 */

public class CategoryView {

    private String title;
    private int layoutResId;
    private String viewClass;

    public CategoryView() {
    }

    public CategoryView(String title, int layoutResId, String viewClass) {
        this.title = title;
        this.layoutResId = layoutResId;
        this.viewClass = viewClass;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLayoutResId() {
        return layoutResId;
    }

    public void setLayoutResId(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    public String getViewClass() {
        return viewClass;
    }

    public void setViewClass(String viewClass) {
        this.viewClass = viewClass;
    }
}
