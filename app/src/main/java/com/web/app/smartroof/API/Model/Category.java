package com.web.app.smartroof.API.Model;


public class Category  {
    String _id ;
    String title;
    String subCatType;
    String content;
    String updatedAt;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Category(String _id, String title, String subCatType, String content, String updatedAt) {
        this._id = _id;
        this.title = title;
        this.subCatType = subCatType;
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category(String _id, String title, String subCatType, String content) {
        this._id = _id;
        this.title = title;
        this.subCatType = subCatType;
        this.content = content;
    }

    public String getSubCatType() {
        return subCatType;
    }

    public void setSubCatType(String subCatType) {
        this.subCatType = subCatType;
    }

    public Category(String _id, String title, String subCatType) {
        this._id = _id;
        this.title = title;
        this.subCatType = subCatType;
    }

    public Category(String _id, String title) {
        this._id = _id;
        this.title = title;
    }

    public Category() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
