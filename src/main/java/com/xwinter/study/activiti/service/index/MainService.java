package com.xwinter.study.activiti.service.index;

import com.xwinter.study.activiti.entity.Test;
import com.xwinter.study.activiti.service.BaseService;
import com.xwinter.study.activiti.service.WfBaseService;

public interface MainService extends BaseService<Test, String>,WfBaseService<Test, String> {
}
