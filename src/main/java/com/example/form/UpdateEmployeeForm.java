package com.example.form;

public class UpdateEmployeeForm {
//リクエストパラメータを受け取る属性を定義しました。

    String id; //従業員ID

    String dependentsCount; //扶養人数

	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
}
